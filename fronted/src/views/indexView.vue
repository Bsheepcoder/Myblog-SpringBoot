<template>
    <div class="common-layout">
        <el-container >
            <el-header class="el-page-header" style="display: block;background-color: #0e0e0e;
            background-image: linear-gradient(130deg,#155899,#15945c);text-align: center;height: 300px">
                <div style="font-family: monospace;margin-top: 120px;font-size: 36px">
                    share coding thoughts
                </div>
            </el-header>
            <el-main>
                <el-container>
                    <div style="margin-right: auto;margin-left: auto;text-align: center;width:1000px;border-radius: 6px;align-items: center;">
                        <el-row>
                            <el-col   class="col-block"  :xs="24" :sm="24" :md="24" :lg="24" :xl="24"  >
                                <router-view v-slot="{ Component }">
                                    <transition name="el-fade-in-linear">
                                        <component :is="Component" />
                                    </transition>
                                </router-view>
                            </el-col>
                        </el-row>
                    </div>
                </el-container>
                <el-backtop :right="50" :bottom="100" style="background-color: lightsalmon;color: white" />
                <el-footer style="text-align: center;bottom: 0;">
                    <div style="height: 100%;width: 100%; ">
                        <div class="footer-text"> Powered by 腾讯云 </div>
                        <div class="footer-text">Copyright © 2023 代码思想社 - All rights reserved 蒙ICP备xxxxx号</div>
                    </div>
                </el-footer>
            </el-main>
        </el-container>

    </div>

</template>

<script lang="ts" setup>


import AboutMeCard from "../components/aboutMeCard.vue";
import {ref} from "vue";
import {MdCatalog} from "md-editor-v3";




const value = ref(true)



import { useDark, useToggle } from '@vueuse/core'
import HotArticles from "../components/hotArticles.vue";

const isDark = useDark()
const toggleDark = useToggle(isDark)

</script>

<style scoped>


img {
    width: 220px;
    height: 174px;
    margin: 0 24px 24px 0;
}
img:nth-of-type(3) {
    margin-right: 0;
}
.box-card{
    margin: 5px;
    text-align: center;
    padding: 10px;
}
.footer-text{
    color: grey;
    font-size: small;
}

</style>
    
