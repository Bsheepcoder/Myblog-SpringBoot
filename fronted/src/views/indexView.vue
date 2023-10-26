<template>
    <div class="common-layout">
        <el-container >
            <el-affix :offset="0">
                <el-menu
                    :default-active="activeIndex"
                    class="el-menu-demo"
                    mode="horizontal"
                    @select="handleSelect"
                >
                    <el-menu-item class="menu-item" @click="router.push('/')">
                        <div style="">Q's blog</div>
                    </el-menu-item>
                    <el-menu-item  class="menu-item"  @click="router.push('/new')">实验室</el-menu-item>
                </el-menu>
            </el-affix>

            <el-header class="el-page-header" style="display: block;background-color: #0e0e0e;
            background-image: linear-gradient(130deg,#155899,#15945c);text-align: center;height: 300px">
                <div style="font-family: monospace;margin-top: 120px;font-size: 36px">
                    share coding thoughts
                </div>
            </el-header>
            <el-main>
                <el-container>
                    <div style="margin-right: auto;margin-left: auto;text-align: center;width:1400px;border-radius: 6px;align-items: center;">
                        <el-row>
                            <el-col   class="col-block"  :xs="24" :sm="24" :md="24" :lg="0" :xl="0" >

                            </el-col>
                            <el-col   class="col-block"  :xs="0" :sm="0" :md="0" :lg="4" :xl="4"  >

                            </el-col>
                            <el-col   class="col-block"  :xs="24" :sm="24" :md="24" :lg="16" :xl="16"  >
                                <router-view v-slot="{ Component }">
                                    <transition name="el-fade-in-linear">
                                        <component :is="Component" />
                                    </transition>
                                </router-view>
                            </el-col>
                            <el-col   class="col-block"  :xs="24" :sm="24" :md="24" :lg="4" :xl="4"  >

                            </el-col>
                        </el-row>
                    </div>
                </el-container>
                <el-backtop :right="50" :bottom="100" style="background-color: lightsalmon;color: white" />
                <el-footer style="text-align: center;bottom: 0;">
                    <div style="height: 100%;width: 100%;text-align: center;margin-top: 50px">
                        <div class="footer-text"> Powered by 腾讯云 </div>
                        <div class="footer-text">Copyright © 2023 代码研习社 - All rights reserved 蒙ICP备2023003393号</div>
                    </div>
                </el-footer>
            </el-main>
        </el-container>
    </div>

</template>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
const value = ref(true)
import { useDark, useToggle } from '@vueuse/core'
import {get} from "@/net";
import router from "@/router";

const isDark = useDark()
const toggleDark = useToggle(isDark)
const ops = ref('')
const loading = ref(true)
const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
    console.log(key, keyPath)
}


</script>

<style scoped>
el-container{
    display: flex;
    flex-direction: column;
    min-height: 100%;
}

img {
    width: 220px;
    height: 174px;
    margin: 0 24px 24px 0;
}
img:nth-of-type(3) {
    margin-right: 0;
}

.footer-text{
    color: black;
    font-size: small;
}
.menu-item{
    font-weight: bolder;
}
.el-menu-demo{
    justify-content: space-between;
}

</style>
    
