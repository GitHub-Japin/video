import {createApp} from 'vue'; // 导入 createApp
import App from './App.vue';
import ElementPlus from 'element-plus';
// 如果您正在使用CDN引入，请删除下面一行。
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/dist/index.css';
import router from './router'; // 导入路由
import './assets/css/tailwind.css';

const app = createApp(App); // 使用 createApp 创建 Vue 实例
app.use(router); // 使用路由
app.use(ElementPlus); // 使用 Element Plus
app.mount('#app'); // 挂载到 #app 元素
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
