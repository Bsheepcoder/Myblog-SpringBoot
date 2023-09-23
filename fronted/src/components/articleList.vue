<template>


    <div>
        <el-card v-for="o in tableData" :key="o"  :data="tableData" class="box-card"   shadow="hover"  @click="getin(o.aid)" >
            <div style="height: 100%;width: 100%;">
                <div class="card-header">
                    <div>{{o.title}}</div>
                </div>
                <div  class="card-overview">
                    <span style="font-weight: bolder">文章概要：</span>
                        {{o.overview}}
                </div>
            </div>
        </el-card>
    </div>
</template>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import {get} from "@/net";
import router from "@/router";

const getin = (e) =>{
    router.push({
        path: `/page/${e}`,
        aid: e,
    })
}
const tableData = ref([]);
const fetchData = () => {
    get('/api/article/list', (msg) => {
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
}

onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
});
</script>

<style scoped>
.card-header {
    justify-content: space-between;
    align-items: center;
    font-size: 31px;
    font-family: 'Trebuchet MS', sans-serif;

}
.card-overview{
    font-family: Tahoma, sans-serif;
    text-align: left;
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

    margin-top: 20px;
    border-radius: 10px;
    padding: 10px;
    font-family: 微软雅黑,serif;
    height: 100%;
}


</style>
    
