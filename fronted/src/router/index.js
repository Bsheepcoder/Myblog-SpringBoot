import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path:'/',
            name:'index',
            component:() => import('@/views/indexView.vue'),
            children:[
            ]
        }
    ]
})

export default router