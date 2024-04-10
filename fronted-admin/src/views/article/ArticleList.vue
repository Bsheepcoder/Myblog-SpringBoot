<template>
<div>
    <el-table :data="tableData" style="">
        <el-table-column label="文章ID" prop="articleid" />
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
                    @click="confirmDelete(scope.row.articleid)"
                >删除文章</el-button
                >
            </template>
        </el-table-column>
    </el-table>
</div>
</template>

<script lang="ts" setup>
import {get,post} from "@/net";
import {computed, createApp, onMounted, reactive, ref,inject} from "vue";
import {ElMessage, ElMessageBox} from "element-plus";
const search = ref('')
const tableData = ref([]);
import router from "@/router";
import axios from "axios";
const reload = inject('reload')


// get('/api/article/list', (msg) => {
//   console.log(msg.data.result)
//     tableData.value = msg.data.result; // 使用ref的.value属性来更新数据
// });
//
//
const deletePage = (e) =>{
    post('/api/article/delete?aids=' + e, (msg) => {
        console.log(msg)
    });
    fetchData();
}

// 定义一个函数，用于发送删除文章的请求
// const updateArticle = (e) =>{
//     router.push({
//         path: `/updateArticle/${e}`,
//         aid: e,
//     })
//     reload()
// }


// 在组件挂载后发送请求获取数据
onMounted(() => {
  fetchData();
});


const fetchData = () => {
  axios.get('/api/article/list')
      .then(response => {
        console.log(response)
        // 请求成功处理
        tableData.value = response.data.result;// 将获取到的数据赋值给 tableData
      })
      .catch(error => {
        // 请求失败处理
        console.error('Error:', error);
        // 在这里处理请求失败的情况，例如提示用户或者进行其他处理
      });
}

const confirmDelete = (ids) => {
  ElMessageBox.confirm('确定要删除文章吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // 用户点击了确认按钮，执行删除操作
    deleteArticle(ids);
  }).catch(() => {
    // 用户点击了取消按钮，取消删除操作
    console.log('用户取消删除');
  });
};

const deleteArticle = (aids) => {
  // 构造请求参数，将文章的ids作为参数传递给后端
  const params = new URLSearchParams();
  params.append('aids', aids);

  // 发送POST请求
  axios.post('/api/article/delete', params)
      .then(response => {
        // 请求成功处理
        ElMessage.success("删除成功！")
        // 在这里可以进行一些成功后的操作，比如重新加载文章列表等
        fetchData();
      })
      .catch(error => {
        // 请求失败处理
        console.error('删除失败:', error);
        // 在这里可以进行一些失败后的操作，比如提示用户删除失败等
      });
};


</script>

<style scoped>

</style>

