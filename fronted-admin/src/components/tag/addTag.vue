<template>
    <div>
        <el-button text @click="dialogVisible = true" style="background-color: #409eff;color: white" >
            新建标签
        </el-button>
        <div style="margin-top: 10px">
            <el-dialog
                    v-model="dialogVisible"
                    title="请输入新的标签名"
                    width="30%"
            >
                <el-form v-model="form">
                    <el-form-item label="名称">
                        <el-input v-model="form.tagName" />
                    </el-form-item>
                </el-form>

                <template #footer>
              <span class="dialog-footer">
                <el-button @click="dialogVisible = false">取消</el-button>
                <el-button type="primary" @click="onSubmitTag()">
                    确认
                </el-button>
              </span>
                </template>
            </el-dialog>
        </div>
    </div>

</template>

<script setup>
import {inject, reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import {post} from "@/net";

const form = reactive({
    tagName: '',
})
const dialogVisible = ref(false)
//刷新引入
const reload = inject('reload')
const onSubmitTag = () => {
    if(form.tagName === ''){
        ElMessage.warning('请输入名称！')
    }else{
        post('/api/tag/add',{
            tagName:form.tagName
        }, (message)=>{
            ElMessage.success(message)
            reload()
        })
        dialogVisible.value = false

    }

}

</script>

<style scoped>

</style>
    
