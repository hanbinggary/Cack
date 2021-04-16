<template>
  <header class="layout-header">
    <div class="layout-header-container">
      <div class="notify">
        <Badge :count="count">
          <BellOutlined />
        </Badge>
      </div>
      <div class="account">
        <img src="../assets/logo.png" alt="logo" />
        <div class="account-content">
          <span class="account-nickname">{{ nickname }}</span>
          <span class="account-role">{{ roleName }} Manager</span>
        </div>
        <div class="account-dropmenus">
          <div class="menu-logout" @click="onClickLogout">
            <LogoutOutlined />
            <span>退出登录</span>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { computed, ref } from 'vue';
import { useRouter } from 'vue-router';
import store from '../store';
import { BellOutlined, LogoutOutlined } from '@ant-design/icons-vue';
import { Badge, message } from 'ant-design-vue';
import { capitalize } from '../utils/commons';

const router = useRouter();

const count = ref(0);
const nickname = computed(() => {
  return store.getters.nickname;
});
const roleName = computed(() => {
  return capitalize(store.getters.roleName);
});

// 退出登录
const onClickLogout = () => {
  store.dispatch('Logout').then(() => {
    message.success('注销成功');
    router.push({
      name: 'Login',
      replace: true
    });
  });
};
</script>

<style scoped>
.layout-header {
  display: flex;
  flex-direction: row-reverse;
  height: 96px;
  padding: 0 16px;
  text-align: right;
  border-top-right-radius: 16px;
}

.layout-header-container {
  display: flex;
  align-items: center;
  padding: 32px 24px 16px;
}

.notify {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  margin-right: 24px;
  cursor: pointer;
  border-radius: 50%;
  background: #f0f7fd;
}

.notify :deep(.anticon) {
  font-size: 26px;
  color: #9cb6cd;
}

.account {
  position: relative;
  display: flex;
  align-items: center;
  padding: 8px 16px;
  border-radius: 16px;
  background: #f0f7fd;
  cursor: pointer;
}

.account:hover {
  background: #e3eff9;
}

.account:hover .account-dropmenus {
  display: block;
}

.account > img {
  width: 32px;
  height: 32px;
}

.account-content {
  margin-left: 12px;
  text-align: left;
}

.account-nickname {
  display: block;
  font-weight: bold;
}

.account-role {
  color: rgba(19, 32, 63, 0.45);
}

.account-dropmenus {
  display: none;
  position: absolute;
  left: 6px;
  bottom: -56px;
  width: 96%;
  padding: 8px 0;
  border-radius: 4px;
  box-shadow: 0 2px 8px -2px rgba(0, 0, 0, 0.1);
  background: #fff;
}

.menu-logout {
  width: 100%;
  padding: 8px 12px;
  text-align: left;
  cursor: pointer;
}

.menu-logout:hover {
  background: rgba(0, 0, 0, 0.05);
}

.menu-logout :deep(.anticon) {
  margin-right: 10px;
  font-size: 18px;
}
</style>
