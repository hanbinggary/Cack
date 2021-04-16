import axios from 'axios';
import NProgress from 'nprogress';
import { message } from 'ant-design-vue';
import { getToken, removeToken } from './auth';
import {
  UNAUTHORIZATION,
  ACCESS_FORBIDDEN,
  REQUEST_FAILED
} from '../utils/constant';
import store from '../store';

// 配置进度条
NProgress.configure({
  showSpinner: false,
  easing: 'ease-out'
});

const instance = axios.create({
  baseURL: 'http://127.0.0.1:8080/cack/web',
  timeout: 5000
});

instance.interceptors.request.use(
  config => {
    NProgress.start(); // 进度条开始
    config.headers['Authorization'] = getToken();
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

instance.interceptors.response.use(
  response => {
    NProgress.done(); // 进度条结束
    const { data } = response;
    if (data.code === 200) {
      return data.result;
    } else if (data.code === 400) {
      message.error(data.message);
    } else if (data.code === 401) {
      removeToken(); // 移除过期 token
      store.dispatch('Logout'); // 清空 vuex 缓存的数据
      message.error(UNAUTHORIZATION);
      location.href = '/login'; // 跳转到登录页面
    } else if (data.code === 403) {
      message.warning(ACCESS_FORBIDDEN);
    } else {
      message.error(REQUEST_FAILED);
    }

    return null;
  },
  error => {
    return Promise.reject(error);
  }
);

export default instance;
