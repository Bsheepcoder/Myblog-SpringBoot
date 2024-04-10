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
                    component:()=>import('@/views/article/articleList.vue')
                },  {
                    path:'/page/:aid',
                    name:'ArticleDetail',
                    component:()=>import('@/views/article/articleDetail.vue'),
                    aid:0,
                },
                {
                    path:'/new',
                    name:'new',
                    component:()=>import('@/views/new/newItem.vue'),
                }
            ]
        }
    ]
})

export default router
