<template>
  <div class="tab-container station">
    <h3>安全设置</h3>
    <a-list item-layout="horizontal">
      <a-list-item>
        <template #extra>
          <a @click.prevent="onChangePassword">修改</a>
        </template>
        <a-list-item-meta :description="'当前密码强度：' + passwordLevelText">
          <template #title>
            <span>账户密码</span>
          </template>
        </a-list-item-meta>
      </a-list-item>
    </a-list>
    <!-- 修改密码 -->
    <a-modal
      :visible="changePasswordVisible"
      title="修改密码"
      @cancel="onCancel"
      @ok="onOk"
    >
      <a-form
        :model="formState"
        :label-col="labelCol"
        :wrapper-col="wrapperCol"
        :rules="formRules"
        ref="formRef"
      >
        <a-form-item label="原密码" name="rawPassword" ref="formRef">
          <a-input-password
            v-model:value="formState.rawPassword"
            placeholder="原密码"
            maxlength="20"
          />
        </a-form-item>
        <a-form-item label="新密码" name="newPassword">
          <a-input-password
            v-model:value="formState.newPassword"
            placeholder="新密码"
            maxlength="20"
          />
        </a-form-item>
        <a-form-item label="再次确认" name="againPassword">
          <a-input-password
            v-model:value="formState.againPassword"
            placeholder="再次确认密码"
            maxlength="20"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script setup>
import { computed, reactive, ref } from 'vue';
import store from '../../../store';
import { updatePasswordById, getAdminById } from '../../../api/admin';
import { message } from 'ant-design-vue';

const passwordLevelText = computed(() => {
  let passwordLevel = store.getters.passwordLevel;
  if (passwordLevel === 0) return '弱';
  else if (passwordLevel === 1) return '中等';
  else return '强';
});
const labelCol = { span: 6 };
const wrapperCol = { span: 14 };
const changePasswordVisible = ref(false);
const formRef = ref();
const formState = reactive({
  rawPassword: '',
  newPassword: '',
  againPassword: ''
});

const validateAgainPassword = (rule, value) => {
  if (!value) {
    return Promise.reject('再次确认密码不能为空');
  }
  if (value !== formState.newPassword) {
    return Promise.reject('两次输入密码不一致');
  } else {
    return Promise.resolve();
  }
};
const formRules = {
  rawPassword: [
    {
      required: true,
      message: '原密码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      message: '原密码的长度必须大于6个字符',
      trigger: 'blur'
    }
  ],
  newPassword: [
    {
      required: true,
      message: '新密码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      message: '新密码的长度必须大于6个字符',
      trigger: 'blur'
    }
  ],
  againPassword: [
    {
      required: true,
      message: '再次确认密码不能为空',
      trigger: 'blur'
    },
    {
      min: 6,
      message: '再次确认的长度必须大于6个字符',
      trigger: 'blur'
    },
    {
      trigger: 'blur',
      validator: validateAgainPassword
    }
  ]
};

// 修改密码
const onChangePassword = () => {
  changePasswordVisible.value = true;
};

const onCancel = () => {
  changePasswordVisible.value = false;
  formRef.value.resetFields();
};

const onOk = () => {
  const id = store.getters.id;
  formRef.value.validate().then(() => {
    updatePasswordById({
      id: id,
      rawPassword: formState.rawPassword,
      newPassword: formState.newPassword
    }).then(result => {
      if (result) {
        message.success('成功修改密码');
        changePasswordVisible.value = false;
        formRef.value.resetFields();
        getAdminById(id).then(admin => {
          if (admin) {
            store.dispatch('updatePasswordLevel', admin.passwordLevel);
          }
        });
      }
    });
  });
};
</script>

<style scoped>
.tab-container {
  padding: 8px 16px;
  padding-right: 40px;
}

.station > h3 {
  margin-bottom: 24px;
  font-size: 18px;
  line-height: 28px;
}

:deep(.ant-input-affix-wrapper) {
  border-radius: 4px;
}
</style>
