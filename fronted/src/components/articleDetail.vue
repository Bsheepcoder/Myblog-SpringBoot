<template>
  <div>
      <el-card v-loading="loading" class="box-card">
          <div style="display:flex;justify-content: space-between;align-items: center;height: 40px;margin: 2px" >
              <el-button type="primary" @click="router.back()">返回</el-button>
          </div>

          <h1 class="page-header">{{pageinfo.title}}</h1>
          <p  class="page-time">更新时间：{{pageinfo.updateTime}} / 创建时间：{{pageinfo.createTime}} / 访问人数：{{pageinfo.visitCount}} </p>


          <el-container  class="page-container">
              <el-row>
                  <el-col  class="col-block" :xs="24" :sm="24" :md="24" :lg="24" :xl="24"  >
                      <MdPreview    :editorId="id" :modelValue="text1"/>
                  </el-col>
<!--              目录    -->
<!--                  <el-col   class="col-block"  :xs="0" :sm="0" :md="6" :lg="6" :xl="6"  >-->
<!--                      <div class="app" style="background-color: #155899">-->
<!--                          <div class="movable-div" :style="{ top: top + 'px' }">-->
<!--                              <el-card style="margin: 10px;width: 240px">-->
<!--                                  <el-text style="font-size: larger;;font-weight: 700">目录</el-text>-->
<!--                                  <MdCatalog style="margin-top: 10px" :editorId="id" :scrollElement="scrollElement" />-->
<!--                              </el-card>-->
<!--                          </div>-->
<!--                      </div>-->
<!--                  </el-col>-->


<!--                  <el-col :span="18" class="content">   <MdPreview :editorId="id" :modelValue="text1" /></el-col>-->
<!--                  <el-col :span="6">-->
<!--                      <div class="app" style="background-color: #155899">-->
<!--                          <div class="movable-div" :style="{ top: top + 'px' }">-->
<!--                              <MdCatalog :editorId="id" :scrollElement="scrollElement" />-->
<!--                          </div>-->
<!--                      </div>-->
<!--                  </el-col>-->
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
import AboutMeCard from "./aboutMeCard.vue";
const loading = ref(true)

const id = 'preview-only';
const text1 = ref('');
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
        text1.value = pageinfo.value.content

    });
    loading.value = false
    top.value = window.scrollY;
    window.addEventListener('scroll', handleWindowScroll);
});

//
onBeforeUnmount(() => {
    window.removeEventListener('scroll', handleWindowScroll);
});



</script>

<style scoped>
.box-card{

}
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
    padding: 0;
    margin: 0;
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


@media screen and (max-width: 467px) {
    .movable-div {
        width: 50%;
    }
}

@media screen and (min-width: 168px) {
    .movable-div {
        width: 50%;
    }
}
</style>
    
