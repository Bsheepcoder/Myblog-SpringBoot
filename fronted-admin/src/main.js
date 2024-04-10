
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'element-plus/theme-chalk/dark/css-vars.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

import router from './router'
import axios from "axios";
import App from './App.vue'
import store from './stores'; // 导入 Vuex store
const app = createApp(App)

//配置后端服务地址
axios.defaults.baseURL = 'https://www.husile.top'
app.use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.use(createPinia())
app.use(router)
app.use(store);
app.mount('#app')
