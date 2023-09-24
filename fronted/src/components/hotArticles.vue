<template>
    <el-card  class="box-card">
        <div style="height: 100%;width: 100%;">
            <div>
                标签
            </div>
            <div  v-for="o in 10" :key="o"  style="text-align: left">
                <div style="background-color: grey;margin-top: 10px">
                   <el-text  >{{o}} 标签</el-text>
                </div>
            </div>
        </div>
    </el-card>
</template>

<script setup>

</script>

<style scoped>
.box-card{
    margin: 5px;
    text-align: center;
    font-size: larger;
    padding: 10px;
}
</style>
    
