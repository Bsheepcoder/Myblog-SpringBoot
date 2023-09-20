<template>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签ID" prop="tid" />
        <el-table-column label="标签名称" prop="tname" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click=""
                >Edit</el-button
                >
                <el-button
                        size="small"
                        type="danger"
                        @click=""
                >Delete</el-button
                >
            </template>
        </el-table-column>
    </el-table>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {get} from "@/net";

const search = ref('')


const tableData = ref([]);


const fetchData = () => {
    get('/api/tag/list', (msg) => {
        console.log(msg);
        console.log(typeof msg);
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
}

onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});
</script>

<style scoped>

</style>
    
