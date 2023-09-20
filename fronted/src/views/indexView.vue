<template>
    <div class="common-layout">
        <el-container >
            <el-header class="el-page-header" style="display: block;background-color: #0e0e0e;
            background-image: linear-gradient(130deg,#155899,#15945c);text-align: center;height: 300px">
              <div>

              </div>
            </el-header>
            <el-main>
                    <div style="margin-right: auto;margin-left: auto;text-align: center;width:1000px;padding: 10px;border-radius: 6px">
                        <el-card v-for="o in tableData" :key="o"  :data="tableData" class="box-card"   shadow="hover" @click.native="getResearch()">
                                <div class="card-header"  >
                                    {{o.title}}
                                </div>
                                <div  class="card-overview">
                                    {{ o.overview }}
                                </div>
                        </el-card>
                    </div>
            </el-main>
            <el-footer>

            </el-footer>
        </el-container>
    </div>
</template>

<script lang="ts" setup>
import { isDark } from '~/composables/dark'
import {onMounted, ref} from "vue";
import {get} from "@/net";





const tableData = ref([]);


const fetchData = () => {
    get('/api/article/list', (msg) => {
        console.log(msg);
        console.log(typeof msg);
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
}

onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});

</script>

<style scoped>
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 50px;
    font-size: 31px;
    font-weight: bolder;

}
.card-overview{
    display: flex;
    margin-top: 20px;
    font-size: 18px;
}
.text {
    font-size: 14px;
}

.item {
    margin-bottom: 18px;
}

.box-card {
    width: 100%;
    margin-top: 20px;
    border-radius: 10px;
    height: 200px;
    font-family: 微软雅黑,serif;
    padding: 10px;
}

img {
    width: 220px;
    height: 174px;
    margin: 0 24px 24px 0;
}
img:nth-of-type(3) {
    margin-right: 0;
}
</style>
    
