<template>
  <a-modal :visible="visible" title="无效原因" @cancel="onCancel" @ok="onOk">
    <a-form ref="formRef" :rules="rules" :model="formState">
      <a-form-item label="无效原因" name="invalidReason">
        <a-textarea
          showCount
          allowClear
          :maxlength="100"
          :cols="42"
          :autoSize="{ minRows: 6, maxRows: 6 }"
          placeholder="请输入无效原因"
          v-model:value="formState.invalidReason"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import { defineEmit, defineProps, reactive, ref } from 'vue';
import { updateStatusById } from '../../../api/bookingTime';

const emits = defineEmit(['onCancel']);

const props = defineProps({
  visible: Boolean,
  id: Number
});

const formRef = ref();
const formState = reactive({
  invalidReason: ''
});
const rules = {
  invalidReason: [
    {
      required: true,
      message: '删除原因不能为空',
      trigger: 'blur'
    }
  ]
};

const onCancel = () => {
  emits('onCancel');
};

const onOk = () => {
  formRef.value.validate().then(result => {
    updateStatusById({
      id: props.id,
      invalidReason: formState.invalidReason,
      status: false
    }).then(() => {
      message.success('状态更新成功');
      emits('onCancel');
      formRef.value.resetFields();
    });
  });
};
</script>
