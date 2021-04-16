<template>
  <aside class="layout-sidebar">
    <div class="sidebar-container">
      <div class="sidebar-logo" @click="onClickGoHome">
        <img src="../assets/logo.png" alt="logo" />
        <h1 class="logo-text">Ca<span class="logo-ck">ck</span></h1>
      </div>
      <div class="sidebar-main">
        <ul class="sidebar-menus">
          <template v-for="menu in menus">
            <li
              v-if="menu.roles.includes(roleName)"
              class="menu-item"
              :key="menu.name"
              :class="
                currentSelectedKey === menu.name ? 'menu-item-active' : ''
              "
              @click="onClickMenu(menu.name)"
            >
              <component :is="$antIcons[menu.icon]"></component>
              <span class="menu-item-label">{{ menu.label }}</span>
            </li>
          </template>
        </ul>
      </div>
      <footer class="sidebar-footer">
        <h3>&copy; Cack. 2021</h3>
        <p>Cack is vehicle inspection management for all stuff.</p>
      </footer>
    </div>
  </aside>
</template>

<script setup>
import { onBeforeMount, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import store from '../store';

const router = useRouter();
const route = useRoute();
const roleName = ref(store.getters.roleName);
const menusRef = ref();
const currentSelectedKey = ref('Home');
const componentName = ref('SettingOutlined');

const menus = [
  {
    name: 'Home',
    label: '首页',
    icon: 'HomeOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Booking',
    label: '预约管理',
    icon: 'ClockCircleOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'BookingTime',
    label: '可预约时间',
    icon: 'HistoryOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'User',
    label: '车主管理',
    icon: 'UserOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Vehicle',
    label: '车辆管理',
    icon: 'CarOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Coupon',
    label: '优惠券',
    icon: 'TagOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Notify',
    label: '消息通知',
    icon: 'MessageOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Dictionary',
    label: '数字字典',
    icon: 'AppstoreOutlined',
    roles: ['USER', 'ADMIN']
  },
  {
    name: 'Role',
    label: '权限管理',
    icon: 'SafetyCertificateOutlined',
    roles: ['ADMIN']
  },
  {
    name: 'Admin',
    label: '管理员',
    icon: 'RobotOutlined',
    roles: ['ADMIN']
  },
  {
    name: 'Setting',
    label: '个人设置',
    icon: 'SettingOutlined',
    roles: ['USER', 'ADMIN']
  }
];

onBeforeMount(() => {
  currentSelectedKey.value = route.name;
});

// 跳转到首页
const onClickGoHome = () => {
  router.push('/');
  currentSelectedKey.value = 'Home';
};

// 点击菜单栏
const onClickMenu = key => {
  currentSelectedKey.value = key;
  router.push({ name: key });
};
</script>

<style scoped>
.layout-sidebar {
  z-index: 100;
  min-width: 256px;
  max-width: 256px;
  padding-bottom: 16px;
  border-top-left-radius: 16px;
  border-bottom-left-radius: 16px;
}

.sidebar-container {
  display: flex;
  flex-direction: column;
  width: 100%;
  height: 100%;
  box-shadow: 3px 0 8px -5px rgba(128, 128, 128, 0.2);
}

.sidebar-logo {
  display: flex;
  align-items: center;
  padding: 8px 24px;
  margin-bottom: 24px;
  line-height: 64px;
  cursor: pointer;
}

.sidebar-logo > img {
  width: 32px;
  height: 32px;
  margin-right: 8px;
}

.sidebar-main {
  flex: 1 1 auto;
  padding: 0 16px;
  overflow: scroll;
}

.sidebar-menus {
  padding: 0 8px;
  list-style: none;
}
.menu-item {
  padding: 10px 8px;
  padding-left: 26px;
  margin-bottom: 5px;
  cursor: pointer;
  border-radius: 8px;
}

.menu-item :deep(.anticon) {
  margin-right: 16px;
  color: #9cb6cd;
  font-size: 16px;
}

.menu-item-label {
  font-size: 15px;
  color: rgba(19, 32, 63, 0.45);
}

.menu-item:hover {
  background: #0075ff;
}

.menu-item:hover :deep(.anticon),
.menu-item:hover .menu-item-label {
  color: #fff;
}

.menu-item-active {
  background: #0075ff;
}

.menu-item-active :deep(.anticon),
.menu-item-active .menu-item-label {
  color: #fff;
}

/* 侧边栏 底部 */
.sidebar-footer {
  padding: 12px 24px 12px 26px;
  margin-left: 24px;
  margin-bottom: auto;
  border-top: 1px solid #eee;
}

.sidebar-footer > h3 {
  font-weight: bold;
  font-size: 17px;
  font-family: Arial, Helvetica, sans-serif;
}

.sidebar-footer {
  font-size: 15px;
  color: #acb1bc;
  font-family: Arial, Helvetica, sans-serif;
}
</style>
