<script setup>

</script>

<template>
<div class="footer">
    <div style="">
        <p>这里是页尾</p>


    </div>
    <el-row :gutter="10">
        <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"><div class="grid-content bg-purple">123</div></el-col>
        <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11"><div class="grid-content bg-purple-light">123</div></el-col>
        <el-col :xs="4" :sm="6" :md="8" :lg="9" :xl="11"><div class="grid-content bg-purple">123</div></el-col>
        <el-col :xs="8" :sm="6" :md="4" :lg="3" :xl="1"><div class="grid-content bg-purple-light">123</div></el-col>
    </el-row>
    <el-row :gutter="10">
        <el-col :xs="{span:24,offset:0}" :sm="{span:16,offset:4}" :md="{span:12,offset:6}">123
        </el-col>
    </el-row>
</div>
</template>

<style scoped>

    .footer{
        background-color: white;
        height: 100px;
        text-align: center;
    }
    .grid-content{
        background-color: dodgerblue;
    }

</style>