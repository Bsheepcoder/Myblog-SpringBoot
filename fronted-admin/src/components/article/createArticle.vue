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
                <el-option v-for="tag in taglist"  :label="tag.tname" :value="tag.tid" />
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
import {onMounted, reactive} from 'vue'
import {defineComponent, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {ElMessage} from "element-plus";
import {get, post} from "@/net";
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


const taglist = ref([])
const fetchData = () => {
    get('/api/tag/list', (msg) => {
        taglist.value = msg; // 使用ref的.value属性来更新数据
    });
}
onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});
</script>

<style scoped>

</style>
    
