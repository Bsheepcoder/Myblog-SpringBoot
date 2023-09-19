<script setup>

</script>

<template>
    <div style="width: 100vw;height: 100vh;overflow: hidden;background-color: dodgerblue;">
<!--        <div style="flex: 2;background-color: dodgerblue">-->
<!--            <el-image style="width: 100%;height: 100%;"-->
<!--                      src="https://ts1.cn.mm.bing.net/th/id/R-C.765c10656bdb92ea1af463233beea47e?rik=q2%2buru5ljnLwkA&riu=http%3a%2f%2f4k.znds.com%2f20140314%2f4kznds3.jpg&ehk=A%2fjdaY7%2b9QMUdN4jcAMvwnnwDJnH0%2fkg1U5g4cDee4c%3d&risl=&pid=ImgRaw&r=0"/>-->
<!--        </div>-->
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