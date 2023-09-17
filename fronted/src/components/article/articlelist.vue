<script setup>

</script>

<template>
    <div v-for="o in 10" :key="o"  class="card">
        <el-card  class="box-card">
            <template #header>
                <div class="card-header">
                    <span>Card name11111111111111111111111111111111</span>
                    <el-button class="button" text>查看文章</el-button>
                </div>
            </template>
        </el-card>
    </div>
</template>

<style scoped>
.card{
    margin-top: 12px;
    box-shadow: 1px 2px 5px gray;
}
.card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
}
.box-card {
}
</style>