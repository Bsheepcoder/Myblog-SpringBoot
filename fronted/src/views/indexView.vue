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
                    <div style="margin-right: auto;margin-left: auto;text-align: center;width:1400px;border-radius: 6px;align-items: center;">
                        <el-row>
                            <el-col   class="col-block"  :xs="24" :sm="24" :md="24" :lg="0" :xl="0"  >
                                <div style="display:flex;justify-content: space-between;margin-bottom: 10px">
                                    <el-select v-model="ops" filterable placeholder="Select">
                                        <el-option
                                            v-for="item in TagData"
                                            :key="item.tid"
                                            :label="item.tname"
                                            :value="item.tid"
                                        />
                                    </el-select>
                                </div>
                            </el-col>
                            <el-col   class="col-block"  :xs="0" :sm="0" :md="0" :lg="4" :xl="4"  >
                                <el-card  class="article-tag" >
                                    <div>标签列表</div>
                                    <div v-for="o in TagData">
                                        <div>
                                            <el-button class="tag"     color="#626aef" :dark="isDark">{{o.tname}}</el-button>
                                        </div>
                                    </div>
                                </el-card >
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

import {onMounted, ref} from "vue";
const value = ref(true)
import { useDark, useToggle } from '@vueuse/core'
import {get} from "@/net";

const isDark = useDark()
const toggleDark = useToggle(isDark)


const ops = ref('')
const TagData = ref([]);
const loading = ref(true)

const fetchTagData = () => {
    get('/api/tag/list', (msg) => {
        TagData.value = msg; // 使用ref的.value属性来更新数据
    });
    loading.value = false
}

onMounted(() => {
    fetchTagData(); // 在组件挂载后调用获取数据的函数
});
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

.footer-text{
    color: grey;
    font-size: small;
}

.article-tag{
    margin-left: auto;
    margin-right: auto;
    background-color: white;
    border-radius: 5px;
    font-family: 微软雅黑,serif;
    padding: 10px;
    width: 170px;
}

.tag{
    border-radius:40px;
    height: 25px;
    margin-top: 10px;
}
</style>
    
