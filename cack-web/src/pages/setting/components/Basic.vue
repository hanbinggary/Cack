<template>
  <div class="tab-container basic">
    <a-skeleton :loading="loading">
      <h3>基本设置</h3>
      <a-form
        layout="vertical"
        :model="formState"
        :rules="formRules"
        ref="formRef"
        class="form"
      >
        <a-form-item label="用户名" name="username" has-feedback>
          <a-input
            placeholder="用户名"
            maxlength="20"
            v-model:value="formState.username"
            disabled
          />
        </a-form-item>
        <a-form-item label="昵称" name="nickname">
          <a-input
            placeholder="昵称"
            maxlength="20"
            v-model:value="formState.nickname"
          />
        </a-form-item>
        <a-form-item label="手机号" name="phone">
          <a-input
            placeholder="手机号"
            maxlength="11"
            v-model:value="formState.phone"
          />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click.prevent="onUpdate"
            >更新基本信息</a-button
          >
        </a-form-item>
      </a-form>
    </a-skeleton>
  </div>
</template>

<script setup>
import { onBeforeMount, reactive, ref, toRaw } from 'vue';
import store from '../../../store';
import {
  getAdminById,
  countAdminByUsername,
  updateBaiscById
} from '../../../api/admin';
import { message } from 'ant-design-vue';

const currentId = ref(store.getters.id);
const formRef = ref();
const loading = ref(false);
const formState = reactive({
  username: '',
  nickname: '',
  phone: ''
});

const formRules = {
  nickname: [
    {
      required: true,
      message: '昵称不能为空',
      trigger: 'blur'
    },
    {
      min: 3,
      max: 20,
      message: '昵称必须在3到20个字符之间',
      trigger: 'blur'
    }
  ],
  phone: [
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '手机号格式不正确',
      trigger: 'blur'
    }
  ]
};

const getData = () => {
  loading.value = true;
  getAdminById(currentId.value).then(result => {
    if (result) {
      formState.username = result.username;
      formState.nickname = result.nickname;
      formState.phone = result.phone;
      loading.value = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

const onUpdate = () => {
  formRef.value.validate().then(() => {
    updateBaiscById({
      id: currentId.value,
      ...formState
    }).then(() => {
      message.success('成功更新基本信息');
      getData();
      store.dispatch('updateNickname', formState.nickname);
    });
  });
};
</script>

<style scoped>
.tab-container {
  padding: 8px 16px;
}

.basic > h3 {
  margin-bottom: 24px;
  font-size: 18px;
  line-height: 28px;
}

.form {
  width: 388px;
}

:deep(.ant-input) {
  width: 100%;
}
</style>
