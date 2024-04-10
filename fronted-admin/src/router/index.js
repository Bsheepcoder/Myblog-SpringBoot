import { createRouter, createWebHistory } from 'vue-router'
import {mapState, useStore} from "vuex";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path:'/',
            name:'welcome',
            component:() => import('@/views/WelcomeView.vue'),
            children:[
                {
                    path:'',
                    name:'welcome-login',
                    component:()=>import('@/views/welcome/LoginPage.vue')
                },
                {
                    path:'register',
                    name:'welcome-register',
                    component:()=>import('@/views/welcome/RegisterPage.vue')
                },
                {
                    path:'forget',
                    name:'welcome-forget',
                    component:()=>import('@/views/welcome/ForgetPage.vue')
                }
            ]
        },{
            path:'/index',
            name:'index',
            component:() => import('@/views/IndexView.vue'),
            meta: { requiresAuth: true }, // 需要登录才能访问
            children:[
                {
                    path:'/ArticleList',
                    name:'ArticleList',
                    component:()=>import('@/views/article/ArticleList.vue'),
                    meta: { requiresAuth: true }, // 需要登录才能访问
                },
                {
                    path:'/createArticle',
                    name:'createArticle',
                    component:()=>import('@/views/article/createArticle.vue'),
                    meta: { requiresAuth: true }, // 需要登录才能访问
                },
                {
                    path:'/taglist',
                    name:'taglist',
                    component:()=>import('@/views/tag/taglist.vue'),
                    meta: { requiresAuth: true }, // 需要登录才能访问
                },
                {
                    path:'/addtag',
                    name:'addtag',
                    component:()=>import('@/views/tag/addtag.vue'),
                    meta: { requiresAuth: true }, // 需要登录才能访问
                },
                {
                    path:'/updateArticle/:aid',
                    name:'updateArticle',
                    component:()=>import('@/views/article/updateArticle.vue'),
                    meta: { requiresAuth: true }, // 需要登录才能访问
                },
            ]
        }
    ]
})

router.beforeEach((to, from, next) => {
    // 检查路由是否需要登录权限
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // 检查用户是否已登录，这里假设有一个名为 isAuthenticated 的变量用于表示用户的登录状态
        const store = useStore();
        if (!store.state.isAuthenticated) {
            // 如果用户未登录，则重定向到登录页面
            next('/'); // 重定向到欢迎页面
        } else {
            // 如果用户已登录，则允许访问
            next();
        }
    } else {
        // 如果路由不需要登录权限，则直接放行
        next();
    }
});

export default router
