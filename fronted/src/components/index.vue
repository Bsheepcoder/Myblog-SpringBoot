<script setup>
import myheader from "./header.vue"
import myfooter from "./footer.vue"
import articlelist from "./article/articleList.vue"
</script>

<template>
    <div class="common-layout">
        <el-container>
            <el-header>
                <myheader></myheader>
            </el-header>
            <el-main>
                <el-row >
<!--                    <el-col :span="16"><div class="grid-content ep-bg-purple" /> <articlelist></articlelist></el-col>-->
<!--                    <el-col :span="8"><div class="grid-content ep-bg-purple" />  </el-col>-->


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
    }
    .common-layout{
      background-color: white;
    }
    .el-footer{
    }

</style>