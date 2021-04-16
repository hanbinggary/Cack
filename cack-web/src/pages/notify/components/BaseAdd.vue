<template>
  <a-modal
    :visible="visible"
    title="发布系统消息"
    @cancel="$emit('onCancel')"
    @ok="onOk"
  >
    <a-form
      ref="formRef"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
      :rules="formRules"
      :model="formState"
    >
      <a-form-item label="标题" name="title">
        <a-input
          placeholder="标题"
          v-model:value="formState.title"
          maxlength="30"
        />
      </a-form-item>
      <a-form-item label="内容" name="content">
        <a-textarea
          placeholder="标题"
          v-model:value="formState.content"
          showCount
          maxlength="255"
          :autoSize="{ minRows: 8, maxRows: 8 }"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { defineEmit, defineProps, inject, reactive, ref } from 'vue';
import store from '../../../store';
import { insertNotify } from '../../../api/notify';
import { message } from 'ant-design-vue';

defineProps({
  visible: Boolean
});
const emits = defineEmit(['onCancel']);
const getData = inject('getData');
const labelCol = { span: 4 };
const wrapperCol = { span: 16 };
const formRef = ref();
const formState = reactive({
  title: '系统通知',
  content: ''
});
const formRules = {
  title: [
    {
      required: true,
      message: '标题不能为空',
      trigger: 'blur'
    }
  ],
  content: [
    {
      required: true,
      message: '内容不能为空',
      trigger: 'blur'
    }
  ]
};

const onOk = () => {
  formRef.value.validate().then(() => {
    insertNotify({
      title: formState.title,
      content: formState.content,
      adminId: store.getters.id
    }).then(result => {
      if (result) {
        message.success('成功发布系统消息');
        getData();
        emits('onCancel');
      }
    });
  });
};
</script>

<style scoped>
:deep(.ant-input) {
  border-radius: 4px;
}
</style>
