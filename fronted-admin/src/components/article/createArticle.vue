<template>
<div>
    <el-form :model="form" >
        <el-form-item >
            <div style="margin-left:auto;margin-right:0;justify-items: center ;align-items: center">
                <el-button type="primary" @click="">存入草稿</el-button>
                <el-button type="primary" @click="onSubmit">发表文章</el-button>
            </div>
        </el-form-item>
        <el-form-item label="文章标题">
            <el-input v-model="form.title" />
        </el-form-item>
        <el-form-item label="文章标签">
            <el-select v-model="form.tag" placeholder="选择标签">
                <el-option label="默认" value="2" />
            </el-select>
        </el-form-item>
        <el-form-item label="文章概述">
            <el-input v-model="form.overview" type="textarea" />
        </el-form-item>
    </el-form>
    <MdEditor v-model="text"/>
</div>

</template>

<script setup>
import { reactive } from 'vue'
import {defineComponent, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {ElMessage} from "element-plus";
import {post} from "@/net";
import router from "@/router";

const form = reactive({
    title: '',
    tag:'',
    overview:'',
    currentTime:new Date(),
})

const text = ref('.....请输入')


const onSubmit = () => {
    if(!form.title || !text){
        ElMessage.warning('请完善文章内容再提交！')
    }else{
        post('/api/article/add',{
            title:form.title,
            tag:form.tag,
            overview:form.overview,
            text:text.value,
        }, (message)=>{
            ElMessage.success(message)
            router.push('/articleList')
        })
    }
    console.log('submit!')
}
</script>

<style scoped>

</style>
    
