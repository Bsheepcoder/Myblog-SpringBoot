<script setup>
import myheader from "./header.vue"
import myfooter from "./footer.vue"
import articlelist from "./article/articlelist.vue"
</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <myheader></myheader>
            </el-header>
            <el-main>
                <el-row>
                    <el-col :span="16"><div class="grid-content ep-bg-purple" /> <articlelist></articlelist></el-col>
                    <el-col :span="8"><div class="grid-content ep-bg-purple" />  </el-col>
                </el-row>

            </el-main>
            <el-footer>
                <myfooter></myfooter>
            </el-footer>
        </el-container>
    </div>
</template>

<style scoped>
    .el-header{
        padding: 0 0;
    }
    .common-layout{
      margin: 0 auto;
      background-color: lightyellow;
      width: 1200px;

    }
    .el-footer{
        padding: 0 0;
        height: auto;
    }
</style>