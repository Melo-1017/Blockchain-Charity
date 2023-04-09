import { createApp } from 'vue'
import App from './App.vue'
import VueRouter from './router/index'
import axios from "axios";
import router from './router'
import ElementPlus from 'element-plus'
axios.defaults.baseURL="http://81.68.174.137:8080/"
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css'
//允许传递cookie
axios.defaults.withCredentials=true

let app =createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
//vuerouter插入到当前vue实例中
app.use(VueRouter)
app.use(ElementPlus)
app.config.globalProperties.$http=axios

app.use(router).mount('#app')

