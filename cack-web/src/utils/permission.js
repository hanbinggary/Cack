// 权限控制
import router from '../router';
import NProgress from 'nprogress';
import { getToken } from './auth';
import store from '../store';
import { message } from 'ant-design-vue';

NProgress.configure({
  showSpinner: false,
  easing: 'ease'
});

// 判断账户是否拥有访问某个页面的权限
const hasPermission = (roleName, route) => {
  if (route.meta && route.meta.permission) {
    return route.meta.permission.includes(roleName);
  }
};

const isStoreEmpty = () => {
  const { id, username, nickname, roleName, passwordLevel } = store.getters;
  if (id === 0) return true;
  if (username === '') return true;
  if (nickname === '') return true;
  if (roleName === '') return true;
  if (passwordLevel === -1) return true;
  return false;
};

// 配置路由访问权限认证
router.beforeEach((to, from, next) => {
  NProgress.start();

  const { path } = to;
  if (isStoreEmpty()) {
    if (path === '/login') {
      next();
    } else {
      message.error('未登录或登录过期');
      next('/login');
      NProgress.done();
    }
  } else if (getToken()) {
    if (path === '/login') {
      next('/');
      NProgress.done();
    } else {
      if (hasPermission(store.getters.roleName, to)) {
        next();
      } else {
        message.error('没有访问权限');
        next('/403');
      }
    }
  } else {
    if (path === '/login') {
      next();
    } else {
      message.error('未登录或登录过期');
      next('/login');
      NProgress.done();
    }
  }
});

router.afterEach(() => {
  NProgress.done();
});
