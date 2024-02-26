<template>
    <el-container class="common_layout">
        <el-container style="height: auto">
            <el-header style="padding: 0;height: 80px">
                <div id="logo">
                    <span id="title">博客管理</span>
                </div>
                <div class="flex flex-wrap items-center">
                    <el-button type="primary" @click="logout">退出登录</el-button>
                </div>
            </el-header>
            <el-container>
                <el-aside width="200px">
                    <el-menu
                            active-text-color="#409eff"
                            background-color="#545c64"
                            class="el-menu-vertical-demo"
                            default-active="2"
                            text-color="#fff"
                            @open=""
                            @close=""
                    >
                        <el-sub-menu index="1">
                            <template #title>
                                <el-icon><location /></el-icon>
                                <span>文章管理</span>
                            </template>
                                <el-menu-item index="1-1" @click="router.push('/ArticleList')">文章列表</el-menu-item>
                                <el-menu-item index="1-2"
                                              @click="router.push('/createArticle')"
                                >创建文章</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="2">
                            <template #title>
                                <el-icon><icon-menu /></el-icon>
                                <span>标签管理</span>
                            </template>
                            <el-menu-item index="3-1" @click="router.push('/taglist')">标签列表</el-menu-item>
                        </el-sub-menu>
                        <el-sub-menu index="3">
                            <template #title>
                                <el-icon><icon-menu /></el-icon>
                                <span>用户管理</span>
                            </template>
                            <el-menu-item index="2-1">用户列表</el-menu-item>
                        </el-sub-menu>
                    </el-menu>
                </el-aside>

                <el-main>
                  <div>
                    <div v-if="mapState(['isAuthenticated'])">Welcome, {{ username }}</div>
                    <div v-else>Please log in</div>
                    <button @click="checkLogin">Check Login Status</button>
                  </div>
                    <div style="background-color: white;border-radius: 7px;padding: 20px;box-shadow: 1px 1px 10px #babcbe">
                    <show-view></show-view>
                    </div>
                    <div style="background-color: white;border-radius: 7px;padding: 20px;margin-top: 10px;box-shadow: 1px 1px 10px #babcbe">
                        <router-view v-slot="{ Component }">
                            <transition name="el-fade-in-linear">
                                <component :is="Component" />
                            </transition>
                        </router-view>
                    </div>

                </el-main>
            </el-container>
        </el-container>
    </el-container>
</template>

<style>
.common_layout {
    height: 100vh;
    background-color: aqua;
}
.submenu {
    background-color: #181e32;
}
.flex-grow {
    flex-grow: 1;
}
.el-aside {
    background-color: #313743;
}
.el-aside .el-menu {
    border-right: none;
}

.el-main {
    background-color: #e9edf1;
}
.el-header {
    background-color: #313743;
    display: flex;
    justify-content: space-between;
    padding-left: 0;
    align-items: center;
    color: #409EFF;
    font-size: 25px;
    font-family: "微软雅黑";
    font-weight: bolder;
}
#logo {
    display: flex;
    align-items: center;
}
#title {
    margin-left: 25px;
}
#img1 {
    width: 10vh;
    height: 10vh;
    margin-left: 1vh;
}
.el-header .el-menu {
    background-color: #363d40;
    color: #fff;
}
.el-aside {
    background-color: #313743;
}
.flex {
    margin-right: 5vh;
}
.example-showcase .el-dropdown + .el-dropdown {
    margin-left: 10px;
    font-size: larger;
}
.example-showcase .el-dropdown-link {
    cursor: pointer;
    color: var(--el-color-primary);
    display: flex;
    align-items: center;
}
</style>

<script setup>
import { ElMessage } from "element-plus";
import { get } from "@/net";
import router from "@/router";
import {
    Document,
    Menu as IconMenu,
    Location,
    Setting,
} from "@element-plus/icons-vue";
import { ref } from "vue";
import ShowView from "@/components/show/showView.vue";
import axios from "axios";
import {mapActions, mapState, useStore} from 'vuex';
const loggedIn = ref(false);
const username = ref('');

const checkLogin = () => {
  axios.get('/api/auth/user')
      .then(response => {
        loggedIn.value = true; // 如果请求成功，说明用户已登录
        username.value = response.data
      })
      .catch(error => {
        if (error.response && error.response.status === 401) {
          loggedIn.value = false; // 如果返回 401 状态码，说明用户未登录
        } else {
          console.error('Failed to check login status:', error);
        }
      });
};
const store = useStore();
const logout = () => {
    get("/api/auth/logout", (message) => {
        ElMessage.success("注销成功！");
        store.dispatch('logout')
        console.log(store.state.isAuthenticated)
        router.push("/");
    });
};

const activeIndex = ref("1");


const handleClick = () => {
    // eslint-disable-next-line no-alert
    alert('button click')
}
</script>