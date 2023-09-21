import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path:'/',
            name:'index',
            component:() => import('@/views/indexView.vue'),
            children:[
                {
                    path:'/',
                    name:'ArticleList',
                    component:()=>import('@/components/ArticleList.vue')
                },  {
                    path:'/page/:aid',
                    name:'ArticleDetail',
                    component:()=>import('@/components/ArticleDetail.vue'),
                    aid:0,
                },
            ]
        }
    ]
})

export default router