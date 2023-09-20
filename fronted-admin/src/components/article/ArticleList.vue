<template>
<div>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="title" label="文章标题" width="180" />
        <el-table-column prop="tag" label="文章标签" width="180" />
        <el-table-column prop="datetime" label="创建时间" />
        <el-table-column prop="overview" label="文章概览" />
    </el-table>
</div>
</template>

<script setup>
import {get} from "@/net";
import {onMounted, reactive, ref} from "vue";


let tableData = ref([]);

const fetchData = () =>{
    get('/api/article/list',(msg)=>{
        console.log(msg)
        tableData = msg
    })
}


onMounted(() => {
    fetchData(); // 在组件挂载后调用 fetchData 方法
});

</script>

<style scoped>

</style>
    
