<script setup>

</script>

<template>
    <div class="allFont">
        <el-menu
            default-active="2"
            class="el-menu-vertical-demo"
            @open="handleOpen"
            @close="handleClose"
        >
            <el-sub-menu index="1">
                <template #title>
                    <el-icon><el-icon><User /></el-icon> </el-icon>
                    <span>账号管理</span>
                </template>
                    <el-menu-item index="1-1">账号列表</el-menu-item>
                    <el-menu-item index="1-2">账号授权</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="2">
                <template #title>
                    <el-icon><el-icon><User /></el-icon> </el-icon>
                    <span>文章管理</span>
                </template>
                <el-menu-item index="2-1">文章列表</el-menu-item>
                <el-menu-item index="2-2">账号授权</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="3">
                <template #title>
                    <el-icon><el-icon><User /></el-icon> </el-icon>
                    <span>账号管理</span>
                </template>
                <el-menu-item index="3-1">账号列表</el-menu-item>
                <el-menu-item index="3-2">账号授权</el-menu-item>
            </el-sub-menu>
            <el-sub-menu index="4">
                <template #title>
                    <el-icon><el-icon><User /></el-icon> </el-icon>
                    <span>账号管理</span>
                </template>
                <el-menu-item index="4-1">账号列表</el-menu-item>
                <el-menu-item index="4-2">账号授权</el-menu-item>
            </el-sub-menu>
        </el-menu>
    </div>

</template>

<style scoped>
    el-menu{
        height: 100%;
    }
</style>