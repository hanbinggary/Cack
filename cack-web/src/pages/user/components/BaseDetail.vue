<template>
  <a-modal
    :visible="visible"
    :key="key"
    title="详情"
    @cancel="onCancel"
    :footer="null"
  >
    <a-skeleton :loading="loading" :paragraph="{ rows: 8 }">
      <div class="content">
        <div class="user">
          <div class="user-item">
            <span class="user-item-label">头像:</span>
            <!-- <span class="user-item-text">{{ bookingTime.date }}</span> -->
            <img :src="user.avatarUrl" alt="avatar" class="user-item-avatar" />
          </div>
          <div class="user-item">
            <span class="user-item-label">昵称:</span>
            <span class="user-item-text">{{ user.nickname }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">性别:</span>
            <span class="user-item-text">{{
              user.gender === 0 ? '未知' : user.gender === 1 ? '女' : '男'
            }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">手机号:</span>
            <span class="user-item-text">{{ user.tel }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">地址:</span>
            <span class="user-item-text"
              >{{ user.country }} {{ user.province }} {{ user.city }}</span
            >
          </div>
          <div class="user-item">
            <span class="user-item-label">是否启用:</span>
            <a-switch :checked="user.status" />
          </div>
          <div class="user-item">
            <span class="user-item-label">openid:</span>
            <span class="user-item-text">{{ user.openid }}</span>
          </div>
          <div class="user-item" v-if="user.unionId && user.unionId.length > 0">
            <span class="user-item-label">unionId:</span>
            <span class="user-item-text">{{ user.unionId }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">sessionKey:</span>
            <span class="user-item-text">{{ user.sessionKey }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">最后登录:</span>
            <span class="user-item-text">{{ user.lastLoginTime }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">注册时间:</span>
            <span class="user-item-text">{{ user.gmtCreate }}</span>
          </div>
          <div class="user-item">
            <span class="user-item-label">更新时间:</span>
            <span class="user-item-text">{{ user.gmtModified }}</span>
          </div>
          <a-button class="user-close-btn" @click="onCancel">关闭</a-button>
        </div>
      </div>
    </a-skeleton>
  </a-modal>
</template>

<script setup>
import { defineEmit, defineProps, onMounted, ref } from 'vue';
import { getUserById } from '../../../api/user';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number
});

const emits = defineEmit(['onCancelVisible']);
const user = ref({});
const loading = ref(false);

onMounted(() => {
  loading.value = true;
  getUserById(props.id).then(result => {
    console.log('result: ', result);
    user.value = result;
    loading.value = false;
  });
});

const onCancel = () => {
  emits('onCancelVisible');
};
</script>

<style scoped>
.user {
  width: 346px;
  margin: 0 auto;
}

.user-item {
  padding-bottom: 20px;
}

.user-item-label {
  display: inline-block;
  width: 80px;
  margin-right: 16px;
  color: rgba(0, 0, 0, 0.65);
  text-align: right;
  vertical-align: top;
}

.user-item-label + span {
  display: inline-block;
  width: 250px;
}

.user-item-avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}

:deep(.ant-btn) {
  border-radius: 4px;
}

.user-close-btn {
  width: 100%;
  margin-top: 24px;
  margin-bottom: 16px;
}
</style>
