<template>

<!--    <div style="display:flex;justify-content: space-between;margin-bottom: 10px">-->
<!--        <el-select v-model="ops" filterable placeholder="Select">-->
<!--            <el-option-->
<!--                v-for="item in TagData"-->
<!--                :key="item.tid"-->
<!--                :label="item.tname"-->
<!--                :value="item.tid"-->
<!--            />-->
<!--        </el-select>-->
<!--    </div>-->
    <div class="article-tag">
        <el-button class="tag"     color="#626aef" :dark="isDark"  @click="fetchData">全部</el-button>
        <div v-for="o in TagData">
            <el-button class="tag"  style="background-color: #444654" :dark="isDark"  @click="getTagList(o.tid)">{{o.tname}}</el-button>
        </div>
    </div>
    <div v-loading="loading" >
        <el-card  v-for="o in tableData" :key="o"  :data="tableData" class="box-card"   shadow="hover"  @click="getin(o.aid)" >
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
import {get,post} from "@/net";
import router from "@/router";
const loading = ref(true)
const TagData = ref([]);
const getin = (e) =>{
    router.push({
        path: `/page/${e}`,
        aid: e,
    })
}

const tableData = ref([])
let count = 0
const tagName = ref([''])

//获取文章列表
const fetchData = () => {
    get('/api/article/list', (msg) => {
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
    loading.value = false
}

//获取标签列表
const fetchTagData = () => {
    get('/api/tag/list', (msg) => {
        TagData.value = msg; // 使用ref的.value属性来更新数据
    });
    loading.value = false
}

//根据标签选项获取文章列表
const getTagList= (tid) => {
    post('/api/article/tag-list',{
        tid:tid
    } ,(msg) => {
        console.log(msg)
        tableData.value = msg; // 使用ref的.value属性来更新数据
    });
    loading.value = false
}

const isDark = ref(true); // 根据需要设置是否为暗色模式


onMounted(() => {
    fetchData(); // 在组件挂载后调用获取数据的函数
    fetchTagData(); // 在组件挂载后调用获取数据的函数
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
    font-size: 16px;
}
.box-card {
    border-radius: 5px;
    padding: 10px;
    font-family: 微软雅黑,serif;
    height: 100%;
    margin-bottom: 10px;
}
.article-tag{
    width: 100%;
    display:inline-block;
    border-radius: 5px;
    font-family: 微软雅黑,serif;
    padding: 0;

}

.tag{
    float: left;
    border-radius:5px;
    margin: 5px;
    color: white;
}

</style>
    
