<template>
    <add-tag></add-tag>
    <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签ID" prop="tid" />
        <el-table-column label="标签名称" prop="tname" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="updateTag(scope.row.aid)"
                >Edit</el-button>
                <el-button
                        size="small"
                        type="danger"
                        @click="deleteTag(scope.row.tid)"
                >Delete</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script setup>
import {inject, onMounted, reactive, ref} from "vue";
import {get, post} from "@/net";
import {ElMessage} from "element-plus";
import AddTag from "@/components/tag/addTag.vue";


const search = ref('')
const tableData = ref([]);
const reload = inject('reload')
get('/api/tag/list', (msg) => {
    tableData.value = msg; // 使用ref的.value属性来更新数据
});
const deleteTag = (e) =>{
    post('/api/tag/del',{
        tagId:e
    },(message)=>{
        ElMessage.success(message)
        reload()
    });
}

const updateArticle = (e) =>{

}
</script>

<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>
    
