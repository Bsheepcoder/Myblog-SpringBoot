<template>
    <div>
        编辑文章
        <el-form :model="form" >
            <el-form-item >
                <div style="margin-left:auto;margin-right:0;justify-items: center ;align-items: center">
                    <el-button type="primary" @click="onSubmit">更新文章</el-button>
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

<script lang="ts" setup>
import {onMounted, reactive} from 'vue'
import {defineComponent, ref} from 'vue';
import {MdEditor} from 'md-editor-v3';
import 'md-editor-v3/lib/style.css';
import {ElMessage} from "element-plus";
import {get, post} from "@/net";
import router from "@/router";
import {useRoute} from "vue-router";

const loading = ref(true)
const form = reactive({
    title: '',
    tag:'',
    overview:'',
    currentTime:new Date(),
})

const text = ref('')
const route = useRoute();
const aid = ref('');
const pageinfo  = ref([]);
const taglist = ref([])
const onSubmit = () => {
    if(!form.title || !text){
        ElMessage.warning('请完善文章内容再提交！')
    }else{
        post('/api/article/update',{
            aid:aid.value,
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



const fetchData = () => {
    get('/api/tag/list', (msg) => {
        taglist.value = msg; // 使用ref的.value属性来更新数据
    });
}


onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
    aid.value = <string>route.params.aid;
    console.log('Aid:', aid.value);
    get("/api/article/page?aid=" + aid.value, (message) => {
        console.log(message)
        pageinfo.value = message
        form.title = pageinfo.value.title
        form.overview = pageinfo.value.overview
        text.value =  pageinfo.value.content
    });
    loading.value = false
});


</script>

<style scoped>

</style>


