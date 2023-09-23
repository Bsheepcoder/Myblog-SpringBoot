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
                    <div style="margin-right: auto;margin-left: auto;text-align: center;width:1000px;padding: 10px;border-radius: 6px;align-items: center">
                        <router-view v-slot="{ Component }">
                            <transition name="el-fade-in-linear">
                                <component :is="Component" />
                            </transition>
                        </router-view>
                    </div>
                </el-container>

            </el-main>
            <el-footer>
            </el-footer>
        </el-container>
    </div>
</template>

<script lang="ts" setup>


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
</style>
    
