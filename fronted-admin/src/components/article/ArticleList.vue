<template>
<div>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="title" label="文章标题" width="180" />
        <el-table-column prop="tag" label="文章标签" width="180" />
        <el-table-column prop="date" label="创建时间" />
        <el-table-column prop="date" label="创建时间" />
    </el-table>
</div>
</template>

<script setup>
import {get} from "@/net";
import {reactive} from "vue";

const tableData = reactive([1,2,12321]
)

// get('/api/article/list',(msg)=>{
//     console.log(msg)
// })

</script>

<style scoped>

</style>
    
