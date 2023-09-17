import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import header from "./components/header.vue"
const app = createApp(App)

app.use(ElementPlus)
app.component('header', header);
app.mount('#app')
