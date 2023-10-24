<template>
<div>
    <el-table :data="tableData" style="">
        <el-table-column label="文章ID" prop="aid" />
        <el-table-column label="文章标题" prop="title" />
        <el-table-column label="创建时间" prop="createTime" />
        <el-table-column label="更新时间" prop="updateTime" />
        <el-table-column label="文章概览" prop="overview" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="updateArticle(scope.row.aid)"
                >编辑文章</el-button>
                <el-button
                    size="small"
                    type="danger"
                    @click="deletePage(scope.row.aid)"
                >删除文章</el-button
                >
            </template>
        </el-table-column>
    </el-table>
</div>
</template>

<script lang="ts" setup>
import {get,post} from "@/net";
import {computed, createApp, onMounted, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
const search = ref('')
const tableData = ref([]);
import router from "@/router";
const fetchData = () => {
    get('/api/article/list', (msg) => {
        console.log(msg);
        console.log(typeof msg);
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
}

const deletePage = (e) =>{
    post('/api/article/delete?aid=' + e, (msg) => {
        console.log(msg)
    });
}

const updateArticle = (e) =>{
    router.push({
        path: `/updateArticle/${e}`,
        aid: e,
    })
}


onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});



</script>

<style scoped>

</style>
    
