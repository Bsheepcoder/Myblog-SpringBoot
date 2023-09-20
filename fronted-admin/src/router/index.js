import { createRouter, createWebHistory } from 'vue-router'

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
                    component:()=>import('@/components/welcome/LoginPage.vue')
                },
                {
                    path:'register',
                    name:'welcome-register',
                    component:()=>import('@/components/welcome/RegisterPage.vue')
                },
                {
                    path:'forget',
                    name:'welcome-forget',
                    component:()=>import('@/components/welcome/ForgetPage.vue')
                }
            ]
        },{
            path:'/index',
            name:'index',
            component:() => import('@/views/IndexView.vue'),
            children:[
                {
                    path:'/ArticleList',
                    name:'ArticleList',
                    component:()=>import('@/components/article/ArticleList.vue')
                },
                {
                    path:'/createArticle',
                    name:'createArticle',
                    component:()=>import('@/components/article/createArticle.vue')
                },
                {
                    path:'/taglist',
                    name:'taglist',
                    component:()=>import('@/components/tag/taglist.vue')
                },
            ]
        }
    ]
})

export default router