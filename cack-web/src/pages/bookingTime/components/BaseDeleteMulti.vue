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
import { deleteBatchBookingTimeByIds } from '../../../api/bookingTime';

const props = defineProps({
  visible: Boolean,
  ids: Array
});
const emits = defineEmit(['onCancelVisible', 'onChangeSelectedKeys']);
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
  emits('onCancelVisible');
};

const onOk = () => {
  formRef.value.validate().then(result => {
    deleteBatchBookingTimeByIds({
      ids: props.ids,
      invalidReason: formState.invalidReason
    }).then(() => {
      emits('onCancelVisible');
      emits('onChangeSelectedKeys');
      getData();
      formRef.value.resetFields();
    });
  });
};
</script>
