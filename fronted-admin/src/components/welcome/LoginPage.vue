<template>
    <div style="text-align: center;margin: 0 20px;">
        <div style="padding: 20px">
            <div style="font-size: 30px;padding: 20px">登录</div>
        </div>
        <div style="">
            <el-input v-model="form.username" type="text" placeholder="用户名">
                <template #prefix>
                    <el-icon><User /></el-icon>
                </template>
            </el-input>
            <el-input v-model="form.password" type="password" style="margin-top: 10px" placeholder="密码">
                <template #prefix>
                    <el-icon><Lock /></el-icon>
                </template>
            </el-input>
        </div>
        <div style="margin-top: 10px">
            <div  style="display:flex;justify-items: center ;align-items: center;margin-left: 100px">
              <div class="code" @click="refreshCode" title="刷新验证码" style="margin-left: 10px">
                <img class="verification-code"  v-if="verificationUrl" :src="verificationUrl"  alt="验证码"/>
              </div>
              <el-input v-model="code.imageCode" type="text" placeholder="请输入验证码"></el-input>
            </div>
        </div>
        <div style="margin-top: 10px">
            <el-row style="display: flex;justify-items: center ;align-items: center">
                <el-col :span="12">
                    <el-checkbox v-model="form.remember" label="记住我"/>
                    <span style="display: inline" ></span>
                </el-col>
                <el-col :span="12">
                    <el-link @click="router.push('forget')">忘记密码?</el-link>
                </el-col>
            </el-row>
        </div>
        <div style="margin-top: 30px">
            <el-button @click="login()"  style="width: 270px"  type="success"  plain>立即登录</el-button>
        </div>

        <el-divider>
            <span style="color:fuchsia">没有账户</span>
        </el-divider>
        <div style="margin-top: 30px">
            <el-button @click="router.push('register')" style="width: 270px" type="warning"  plain>注册账户</el-button>
        </div>
    </div>
</template>


<style>
/*alert 成功弹出框样式*/
.verification-code {
    vertical-align: middle;
    cursor: pointer;
}
</style>

<script setup>
import {Lock, User} from "@element-plus/icons-vue";
import {reactive, ref} from "vue";
import {ElMessage} from "element-plus";
import router from "@/router";
import {get, post} from "@/net";
import axios from "axios";
//import { Service } from "@basic-library";


const form = reactive({
    username:'',
    password:'',
    remember:false,
    imageCode:'',
})

const code = reactive(
    {
        nowCode:'',
    }
)

const login = () =>{
    if(!form.username || !form.password){
        ElMessage.warning('请填写用户名和密码！')
    }else{
        post('/api/auth/login',{
            username:form.username,
            password:form.password,
            remember:form.remember,
            imageCode:form.imageCode,
        }, (message)=>{
            ElMessage.success(message)
            router.push('/index')
        },(message)=>{
          ElMessage.error(message)
        })
    }
}


// 验证码请求地址
const verificationUrl = ref('')

/**
 * 点击刷新
 */
const refreshCode = async () => {
  axios.get('/code/image',{ responseType: 'blob' })
  .then(response => {
    const blob = new Blob([response.data], { type: 'image/jpeg' }); // 假设图片类型为JPEG
    verificationUrl.value = URL.createObjectURL(blob);
  })
  .catch(error => {
    console.error('Error fetching captcha:', error);
  });
}

refreshCode();


</script>