import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('../pages/login/index.vue')
  },
  {
    path: '/',
    name: 'Layout',
    component: () => import('../layouts/index.vue'),
    children: [
      {
        path: '/',
        name: 'Home',
        component: () => import('../pages/home/index.vue'),
        meta: { permission: ['ADMIN', 'USER'] }
      },
      {
        path: '/booking',
        name: 'Booking',
        component: () => import('../pages/booking/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/booking/time',
        name: 'BookingTime',
        component: () => import('../pages/bookingTime/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/user',
        name: 'User',
        component: () => import('../pages/user/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/vehicle',
        name: 'Vehicle',
        component: () => import('../pages/vehicle/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/coupon',
        name: 'Coupon',
        component: () => import('../pages/coupon/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/notify',
        name: 'Notify',
        component: () => import('../pages/notify/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/setting',
        name: 'Setting',
        component: () => import('../pages/setting/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/dictionary',
        name: 'Dictionary',
        component: () => import('../pages/dictionary/index.vue'),
        meta: { permission: ['USER', 'ADMIN'] }
      },
      {
        path: '/role',
        name: 'Role',
        component: () => import('../pages/role/index.vue'),
        meta: { permission: ['ADMIN'] }
      },
      {
        path: '/admin',
        name: 'Admin',
        component: () => import('../pages/admin/index.vue'),
        meta: { permission: ['ADMIN'] }
      },
      {
        path: '/403',
        name: '403',
        component: () => import('../pages/exception/403.vue'),
        meta: { permission: ['ADMIN', 'USER'] }
      }
    ]
  }
];

export default createRouter({
  history: createWebHistory(),
  routes
});
