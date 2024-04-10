<template>
    <add-tag></add-tag>

    <el-dialog   v-model="dialogVisible"
                 title="请输入新的标签名"
                 width="30%">

      <el-form :model="tagForm"  label-width="100px">
        <el-form-item label="标签名称" prop="tagname">
          <el-input v-model="tagForm.tagname"></el-input>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
                  <el-button @click="dialogVisible = false">取消</el-button>
                  <el-button type="primary" @click="updateTag">确认</el-button>
      </span>
    </el-dialog>

    <el-table :data="tableData" style="width: 100%">
        <el-table-column label="标签ID" prop="tagid" />
      <el-table-column label="父级标签" prop="parent" />
      <el-table-column label="级别" prop="levels" />
      <el-table-column label="标签名称" prop="tagname" />
      <el-table-column label="创建时间" prop="createTime" />
        <el-table-column align="right">
            <template #header>
                <el-input v-model="search" size="small" placeholder="Type to search" />
            </template>
            <template #default="scope">
                <el-button size="small" @click="showUpdateDialog(scope.row.tagid)"
                >编辑</el-button>
                <el-button
                        size="small"
                        type="danger"
                        @click="confirmDelete(scope.row.tagid)"
                >删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script setup>
import {inject, onMounted, reactive, ref} from "vue";
import {get, post} from "@/net";
import {ElMessage, ElMessageBox} from "element-plus";
import AddTag from "@/views/tag/addTag.vue";
import axios from "axios";


const search = ref('')
const tableData = ref([]);
const reload = inject('reload')
const tagForm = reactive({
  tagid: null,
  tagname: '',
})
// 在组件挂载后发送请求获取数据
onMounted(() => {
  fetchData();
});


const fetchData = () => {
  axios.get('/api/tag/list')
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
// get('/api/tag/list', (res) => {
//     console.log(res.data.result)
//     tableData.value = res.data.result; // 使用ref的.value属性来更新数据
// });
// const deleteTag = (e) =>{
//     post('/api/tag/delete',{
//         ids:e
//     },(message)=>{
//         ElMessage.success(message)
//         reload()
//     });
// }


const confirmDelete = (ids) => {
  ElMessageBox.confirm('确定要删除标签吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    // 用户点击了确认按钮，执行删除操作
    deleteTag(ids);
  }).catch(() => {
    // 用户点击了取消按钮，取消删除操作
    console.log('用户取消删除');
  });
};

const deleteTag = (ids) => {
  axios.post('/api/tag/delete', null, {
    params: {
      ids: ids
    }
  }).then(response => {
        ElMessage.success("删除成功")
        fetchData();
      })
      .catch(error => {
        // 处理错误响应
      });
}

const dialogVisible = ref(false);



const showUpdateDialog = (tagId) => {
  // 根据tagId获取标签信息，这里假设tag信息是从外部传入的
  // 实际中可能需要发送请求获取标签信息
  tagForm.tagid = tagId;
  // 设置其他标签信息，例如标签名称等
  console.log(tagId)
  // 然后显示弹窗
  dialogVisible.value = true;
};


const updateTag = () => {
  // 提交更新标签的请求
  const saveParam = {tagid:tagForm.tagid,tagname:tagForm.tagname,parent:1,levels:1}
  axios.post('/api/tag/save', [saveParam])
      .then(response => {
        // 更新成功处理
        dialogVisible.value = false;
        ElMessage.success('更新成功');
        fetchData();
      })
      .catch(error => {
        // 更新失败处理
        console.error('更新失败:', error);
        // 在这里处理请求失败的情况，例如提示用户或者进行其他处理
      });
};
</script>

<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>

