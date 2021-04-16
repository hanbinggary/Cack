import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import AntD from 'ant-design-vue';
import './index.css';
import './utils/permission';

import * as antIcons from '@ant-design/icons-vue';

const app = createApp(App);

Object.keys(antIcons).forEach(key => {
  app.component(key, antIcons[key]);
});

// 添加到全局
app.config.globalProperties.$antIcons = antIcons;

app.use(router).use(store).use(AntD).mount('#app');
