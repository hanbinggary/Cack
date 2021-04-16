<template>
  <a-modal :visible="visible" @cancel="onCancel" title="删除" @ok="onOk">
    <a-form ref="formRef" :rules="rules" :model="formState">
      <a-form-item label="删除原因" name="invalidReason">
        <a-textarea
          showCount
          allowClear
          :maxlength="100"
          :cols="42"
          :autoSize="{ minRows: 6, maxRows: 6 }"
          placeholder="请输入删除原因"
          v-model:value="formState.invalidReason"
        />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { defineEmit, defineProps, reactive, ref, toRaw, inject } from 'vue';
import { deleteBookingTimeById } from '../../../api/bookingTime';

const props = defineProps({
  visible: Boolean,
  id: Number
});
const emits = defineEmit(['onChangeVisible']);
const getData = inject('getData');
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
  emits('onChangeVisible', false);
};

const onOk = () => {
  formRef.value.validate().then(() => {
    deleteBookingTimeById({
      id: props.id,
      invalidReason: formState.invalidReason
    }).then(result => {
      emits('onChangeVisible', false);
      getData();
      formRef.value.resetFields();
    });
  });
};
</script>
