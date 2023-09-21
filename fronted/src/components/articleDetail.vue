<template>
  <div>

      <el-card class="box-card">
          <div style="display:flex;justify-content: space-between;align-items: center;height: 40px;margin: 2px" >
              <el-button type="primary" @click="router.back()">返回</el-button>
          </div>

          <h1 class="page-header">{{pageinfo.title}}</h1>
          <p  class="page-time">最后更新：{{pageinfo.datetime}}</p>
          <p  class="page-overview">{{pageinfo.overview}}</p>

          <el-container  class="page-container">
              <el-row>
                  <el-col :span="18">   <MdPreview :editorId="id" :modelValue="text1" /></el-col>
                  <el-col :span="6">
                      <div class="app">
                      <div class="movable-div" :style="{ top: top + 'px' }">
                          <MdCatalog :editorId="id" :scrollElement="scrollElement" />
                      </div>
                      </div>
                  </el-col>
              </el-row>
          </el-container>
          <div class="page-footer">
          </div>
      </el-card>
  </div>
</template>

<script lang="ts" setup>
import {ElMessage} from "element-plus";
import {get} from "@/net";
import {defineComponent, onBeforeUnmount, onMounted, ref} from "vue";
import {useRoute} from "vue-router";
import router from "@/router";
import {MdEditor} from 'md-editor-v3';
import 'md-editor-v3/lib/preview.css';
import { MdPreview, MdCatalog } from 'md-editor-v3';

const id = 'preview-only';
const text1 = ref('# Hello Editor');
const scrollElement = document.documentElement;

const pageinfo  = ref([]);
const route = useRoute();
const aid = ref('');
let text = ref("");


const top = ref(0);

const handleWindowScroll = () => {
    if(window.scrollY > 500) top.value = window.scrollY - 500;
};

onMounted(() => {
    aid.value = <string>route.params.aid;
    console.log('Aid:', aid.value);
    get("/api/article/page?aid=" + aid.value, (message) => {
        console.log(message)
        pageinfo.value = message
        text1.value = pageinfo.value.text
    });
    top.value = window.scrollY;
    window.addEventListener('scroll', handleWindowScroll);
});

//
onBeforeUnmount(() => {
    window.removeEventListener('scroll', handleWindowScroll);
});



</script>

<style scoped>

.page-header{
    font-family: 微软雅黑;
    font-size: 30px;
}
.page-overview{
    font-size: 20px;
}

.page-container{
    display: flex;
    text-align: left;
}

.page-footer{
    height: 200px;
}
.app {
    display: flex;
}
.movable-div {
    position: absolute;
    cursor: move;
    user-select: none;
}
</style>
    
