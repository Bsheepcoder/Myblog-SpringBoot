<template>
<div>
    <el-form :model="form" >
        <el-form-item >
            <div style="margin-left:auto;margin-right:0;justify-items: center ;align-items: center">
                <el-button type="primary" @click="">存入草稿</el-button>
                <el-button type="primary" @click="onSubmitArticle">发表文章</el-button>
            </div>
        </el-form-item>
        <el-form-item label="文章标题">
            <el-input v-model="form.title"  style="width: 600px" />
        </el-form-item>
        <el-form-item label="文章标签">
            <el-select v-model="form.tagid" placeholder="选择标签">
                <el-option v-for="tag in taglist" :label="tag.tagname" :value="tag.tagid" />
            </el-select>
        </el-form-item>
        <el-form-item label="文章概述" style="width: 1000px">
            <el-input v-model="form.overview" type="textarea" size="medium" />
        </el-form-item>
    </el-form>
    <MdEditor v-model="content"/>
</div>

</template>

<script setup>
import {onMounted, reactive} from 'vue'
import {defineComponent, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {ElMessage} from "element-plus";
import {get, jsonpost, post} from "@/net";
import router from "@/router";
import axios from "axios";

const form = reactive({
    title: '请输入标题..',
    overview:'',
    tagid:''
})

const content = ref('.....请输入')


const onSubmit = () => {
    if(!form.title || !content){
        ElMessage.warning('请完善文章内容再提交！')
    }else{
        post('/api/article/save',{
            title:form.title,
            overview:form.overview,
            content: content.value,
        }, (message)=>{
            ElMessage.success(message)

        })
    }
    console.log('submit!')
}

const onSubmitArticle = () => {
  if(!form.title || !content){
    ElMessage.warning('请完善文章内容再提交！')
  }else{
    const saveParam = {
      title: form.title,
      overview: form.overview,
      content: content.value,
      tagid: form.tagid
    }
    jsonpost('/api/article/save',saveParam, (message)=>{
      ElMessage.success(message)
      router.push('/articleList')
    })
  }
}


const addTagWithArticle = (articleid) =>{

}

const taglist = ref([])
const fetchData = () => {
  axios.get('/api/tag/list')
      .then(response => {
        console.log(response)
        // 请求成功处理
        taglist.value = response.data.result;// 将获取到的数据赋值给 tableData
      })
      .catch(error => {
        // 请求失败处理
        console.error('Error:', error);
        // 在这里处理请求失败的情况，例如提示用户或者进行其他处理
  });
}

onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});


</script>

<style scoped>

</style>

