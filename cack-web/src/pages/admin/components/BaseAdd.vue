<template>
  <a-modal :visible="visible" title="新增管理员" @cancel="onCancel" @ok="onOK">
    <a-form
      :model="formState"
      :wrapper-col="wrapperCol"
      :label-col="labelCol"
      :rules="formRules"
      ref="formRef"
    >
      <a-form-item label="用户名" name="username" has-feedback>
        <a-input
          v-model:value="formState.username"
          maxlength="20"
          placeholder="用户名"
        />
      </a-form-item>
      <a-form-item label="密码" name="password">
        <a-input-password
          v-model:value="formState.password"
          maxlength="20"
          placeholder="密码"
        />
      </a-form-item>
      <a-form-item label="再次确认" name="againPassword">
        <a-input-password
          v-model:value="formState.againPassword"
          maxlength="20"
          placeholder="确认密码"
        />
      </a-form-item>
      <a-form-item label="昵称" name="nickname">
        <a-input
          v-model:value="formState.nickname"
          maxlength="20"
          placeholder="昵称"
        />
      </a-form-item>
      <a-form-item label="手机号" name="phone">
        <a-input
          v-model:value="formState.phone"
          maxlength="11"
          placeholder="手机号"
        />
      </a-form-item>
      <a-form-item label="分配角色">
        <a-select v-model:value="formState.roleId">
          <a-select-option
            v-for="role in roles"
            :key="role.id"
            :value="role.id"
          >
            {{ role.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import {
  defineEmit,
  defineProps,
  inject,
  onBeforeMount,
  reactive,
  ref
} from 'vue';
import { countAdminByUsername, insertAdmin } from '../../../api/admin';

const props = defineProps({
  visible: Boolean,
  roles: []
});
const emits = defineEmit(['onCancel']);
const getData = inject('getData');
const formRef = ref();
const formState = reactive({
  username: '',
  password: '',
  againPassword: '',
  nickname: '',
  phone: '',
  roleId: 1
});
const labelCol = { span: 6 };
const wrapperCol = { span: 14 };

const validateUsername = async (rule, value) => {
  if (!value) {
    return Promise.reject('用户名不能为空');
  }
  const total = await countAdminByUsername(value);
  if (total > 0) {
    return Promise.reject('用户名已存在');
  } else {
    return Promise.resolve();
  }
};

const validateAgainPassword = async (rule, value) => {
  if (!value) {
    return Promise.reject('再次确认密码不能为空');
  }
  if (formState.password !== value) {
    return Promise.reject('两次输入的密码不一致');
  } else {
    return Promise.resolve();
  }
};

const formRules = {
  username: [
    {
      required: true,
      message: '用户名不能为空',
      trigger: 'blur'
    },
    {
      min: 3,
      max: 20,
      message: '用户名必须在3到20个字符之间',
      trigger: 'blur'
    },
    {
      trigger: 'change',
      validator: validateUsername
    }
  ],
  password: [
    {
      required: true,
      message: '密码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      max: 20,
      message: '密码必须在6到20个字符之间',
      trigger: 'blur'
    }
  ],
  againPassword: [
    {
      required: true,
      trigger: 'blur',
      validator: validateAgainPassword
    }
  ],
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

const onCancel = () => {
  formRef.value.resetFields();
  emits('onCancel');
};

const onOK = () => {
  formRef.value.validate().then(() => {
    insertAdmin(formState).then(result => {
      if (result) {
        message.success('成功添加管理员');
        getData();
        onCancel();
      }
    });
  });
};
</script>

<style scoped>
:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}
</style>
