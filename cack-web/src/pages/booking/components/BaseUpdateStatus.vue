<template>
  <a-modal
    :visible="visible"
    :key="key"
    title="更新状态"
    @cancel="$emit('onCancel')"
    @ok="onOk"
  >
    <a-steps progress-dot :current="status - 1">
      <a-step title="待车检" />
      <a-step title="车检中" />
      <a-step title="完成车检" />
    </a-steps>
    <div class="operation-btn">
      <a-button
        class="btn"
        type="primary"
        v-if="status === 1 && state.proceedBtnVisible"
        @click="onProceedCheck"
      >
        进行车检
      </a-button>
      <a-button
        class="btn"
        type="primary"
        v-if="status === 2 && state.finishBtnVisible"
        @click="onFinishCheck"
      >
        完成车检
      </a-button>
      <a-button class="btn" type="danger" @click="onCancelCheck">
        取消车检
      </a-button>
      <a-form
        :model="state.formState"
        v-if="state.formVisible"
        class="form"
        layout="vertical"
        :rules="formRules"
        ref="formRef"
      >
        <a-form-item label="取消原因" name="cancelReason">
          <a-textarea
            showCount
            maxlength="200"
            placeholder="输入取消原因"
            v-model:value="state.formState.cancelReason"
            :autoSize="{ minRows: 6, maxRows: 6 }"
          />
        </a-form-item>
      </a-form>
    </div>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import {
  defineEmit,
  defineProps,
  inject,
  onBeforeMount,
  onMounted,
  reactive,
  ref
} from 'vue';
import {
  getBookingById,
  updateStatusById,
  updateCancelReasonById
} from '../../../api/booking';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number,
  status: Number
});

const emits = defineEmit(['onCancel', 'onUpdateStatusValue']);
const getData = inject('getData');
const state = reactive({
  proceedBtnVisible: true,
  finishBtnVisible: true,
  flag: true, // true-进行或完成车检操作，false-取消车检
  formState: {
    cancelReason: ''
  },
  formVisible: false
});
const formRef = ref();
const formRules = {
  cancelReason: [
    {
      required: true,
      message: '取消原因不能为空',
      trigger: 'blur'
    }
  ]
};

const onProceedCheck = () => {
  state.proceedBtnVisible = false;
  state.finishBtnVisible = false;
  state.flag = true;
  emits('onUpdateStatusValue', props.status + 1);
};

const onFinishCheck = () => {
  state.finishBtnVisible = false;
  state.flag = true;
  emits('onUpdateStatusValue', props.status + 1);
};

const onCancelCheck = () => {
  state.formVisible = true;
  state.flag = false;
  state.proceedBtnVisible = false;
  state.finishBtnVisible = false;
};

const onOk = () => {
  if (state.flag) {
    console.log('status: ', props.status);
    updateStatusById({
      id: props.id,
      status: props.status
    }).then(result => {
      if (result) {
        message.success('成功更新状态');
        getData();
        emits('onCancel');
      }
    });
  } else {
    formRef.value.validate().then(() => {
      updateCancelReasonById({
        id: props.id,
        cancelReason: state.formState.cancelReason
      }).then(result => {
        if (result) {
          message.success('成功取消预约');
          getData();
          emits('onCancel');
        }
      });
    });
  }
};
</script>

<style scoped>
.operation-btn {
  padding: 24px 48px;
}

.btn {
  margin-right: 16px;
}

.form {
  margin-top: 24px;
}

:deep(.ant-input) {
  border-radius: 4px;
}
</style>
