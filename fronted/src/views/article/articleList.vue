<template>

<!--    <div style="display:flex;justify-content: space-between;margin-bottom: 10px">-->
<!--        <el-select v-model="ops" filterable placeholder="Select">-->
<!--            <el-option-->
<!--                v-for="item in TagData"-->
<!--                :key="item.tid"-->
<!--                :label="item.tname"-->
<!--                :value="item.tid"-->
<!--            />-->
<!--        </el-select>-->
<!--    </div>-->
    <div class="baseArticleEntity-tag">
        <el-button class="tag"     color="#626aef" :dark="isDark"  @click="fetchData">全部</el-button>
        <div v-for="o in TagData">
            <el-button class="tag"  style="background-color: #444654" :dark="isDark"  @click="getTagList(o.tagid)">{{o.tagname}}</el-button>
        </div>
    </div>
    <div v-loading="loading" >
        <el-card  v-for="o in tableData" :key="o"  :data="tableData" class="box-card"   shadow="hover"  @click="getin(o.articleid)" >
            <div style="height: 100%;width: 100%;">
                <div class="card-header">
                    <div>{{o.title}}</div>
                </div>
                <div  class="card-overview">
                    <span style="font-weight: bolder">文章概要：</span>
                        {{o.overview}}
                </div>
            </div>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {get,post} from "@/net";
import router from "@/router";
import axios from "axios";
const loading = ref(true)
const TagData = ref([]);
const getin = (e) =>{
    router.push({
        path: `/page/${e}`,
        aid: e,
    })
}

const tableData = ref([])
let count = 0
const tagName = ref([''])

//获取文章列表
const fetchData = () => {
  axios.get('/api/article/list')
      .then(response => {
        console.log(response)
        // 请求成功处理
        tableData.value = response.data.result;// 将获取到的数据赋值给 tableData
        loading.value = false
      })
      .catch(error => {
        // 请求失败处理
        console.error('Error:', error);
        // 在这里处理请求失败的情况，例如提示用户或者进行其他处理
      });
}

//获取标签列表
const fetchTagData = () => {
  axios.get('/api/tag/list')
      .then(response => {
        console.log(response)
        // 请求成功处理
        TagData.value = response.data.result;// 将获取到的数据赋值给 tableData
      })
      .catch(error => {
        // 请求失败处理
        console.error('Error:', error);
        // 在这里处理请求失败的情况，例如提示用户或者进行其他处理
      });
}

//根据标签选项获取文章列表
const getTagList= (tid) => {
    axios.post('/api/article/taglist', null, {
      params: {
        tagid: tid // 将tagid作为URL参数传递
      }
    }).then(res => {
      tableData.value = res.data.result;
    });
    loading.value = false
}

const isDark = ref(true); // 根据需要设置是否为暗色模式


onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
    fetchTagData(); // 在组件挂载后调用获取数据的函数
});
</script>

<style scoped>
.card-header {
    justify-content: space-between;
    align-items: center;
    font-size: 31px;
    font-family: 'Trebuchet MS', sans-serif;

}
.card-overview{
    font-family: Tahoma, sans-serif;
    text-align: left;
    margin-top: 20px;
    font-size: 16px;
}
.box-card {
    border-radius: 5px;
    padding: 10px;
    font-family: 微软雅黑,serif;
    height: 100%;
    margin-bottom: 10px;
}
.baseArticleEntity-tag{
    width: 100%;
    display:inline-block;
    border-radius: 5px;
    font-family: 微软雅黑,serif;
    padding: 0;

}

.tag{
    float: left;
    border-radius:5px;
    margin: 5px;
    color: white;
}

</style>

