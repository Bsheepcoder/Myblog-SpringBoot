<script setup>

</script>

<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;background-color: dodgerblue;">
        <div class="loginbox">
            <router-view v-slot="{ Component }">
                <transition name="el-fade-in-linear" mode="out-in">
                    <component :is="Component" />
                </transition>
            </router-view>
        </div>
    </div>
</template>

<style>

.loginbox{
    width: 450px;
    background-color: white;
    z-index: 1;
    border-radius: 5px;
    margin: 0 auto;
}

el-button{
    height: 200px;
}

</style>