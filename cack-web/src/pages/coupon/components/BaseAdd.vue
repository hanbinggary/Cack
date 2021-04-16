<template>
  <a-modal
    :visible="visible"
    title="发布优惠券"
    @cancel="$emit('onCancel')"
    @ok="onOK"
  >
    <a-form :model="formState">
      <a-form-item label="截止日期" name="date">
        <a-date-picker
          placeholder="截止日期"
          v-model:value="state.formState.date"
          :disabledDate="disabledDate"
          @change="onChangeDate"
          :allowClear="false"
        />
      </a-form-item>
      <a-form-item label="截止小时" name="tihoursme">
        <a-select
          v-model:value="state.formState.hours"
          class="time-select"
          @select="onSelectHours"
        >
          <a-select-option
            v-for="hour in state.hours"
            :key="hour.value"
            :value="hour.value"
          >
            {{ hour.value }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="截止分钟" name="minutes">
        <a-select v-model:value="state.formState.minutes" class="time-select">
          <a-select-option
            v-for="minute in state.minutes"
            :key="minute.value"
            :value="minute.value"
          >
            {{ minute.value }}
          </a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="截止秒钟" name="seconds">
        <a-select v-model:value="state.formState.seconds" class="time-select">
          <a-select-option
            v-for="second in state.seconds"
            :key="second.value"
            :value="second.value"
          >
            {{ second.value }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
    <a-form-item label="优惠金额" name="money">
      <a-input
        type="number"
        prefix="￥"
        suffix="RMB"
        v-model:value="state.formState.money"
        class="money-input"
      />
    </a-form-item>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import moment from 'moment';
import {
  defineEmit,
  defineProps,
  onBeforeMount,
  onMounted,
  reactive
} from 'vue';
import { insertCoupon } from '../../../api/coupon';

defineProps({
  visible: Boolean,
  key: Number
});

const emits = defineEmit(['onCancel', 'onAddOk']);

const state = reactive({
  formState: {
    date: moment(),
    hours: 8,
    minutes: 59,
    seconds: 59,
    money: 10
  },
  hours: [],
  minutes: [],
  seconds: []
});

onMounted(() => {
  initTime();
});

const disabledDate = date => {
  return date < moment().subtract(1, 'days') || date > moment().add(28, 'days');
};

const onChangeDate = date => {
  const now = moment().format('YYYY-MM-DD');
  const select = date.format('YYYY-MM-DD');
  if (now === select) {
    initTime();
  } else {
    generateTime(24, 0, 0);
  }
};

const initTime = () => {
  const now = new Date();
  const hour = now.getHours();
  const minute = now.getMinutes() + 5;
  generateTime(hour, minute, 0);
};

const generateTime = (hour, minute, second) => {
  generateHours(hour);
  generateMinutes(minute);
  generateSeconds(second);
};

const generateHours = hour => {
  const hours = [];
  for (let i = hour; i < 24; i++) {
    hours.push({
      value: i
    });
  }
  state.formState.hours = hours[0].value;
  state.hours = hours;
};

const generateMinutes = minute => {
  const minutes = [];
  for (let i = minute; i < 60; i++) {
    minutes.push({
      value: i
    });
  }
  state.minutes = minutes;
};

const generateSeconds = second => {
  const seconds = [];
  for (let i = second; i < 60; i++) {
    seconds.push({
      value: i
    });
  }
  state.seconds = seconds;
};

const onSelectHours = hour => {
  const nowDate = moment().format('YYYY-MM-DD');
  const selectDate = state.formState.date.format('YYYY-MM-DD');
  const nowHours = new Date().getHours();
  if (nowDate === selectDate) {
    if (hour === nowHours) {
      generateMinutes(new Date().getMinutes());
    } else {
      generateMinutes(0);
    }
  } else {
    generateMinutes(0);
  }
};

const onOK = () => {
  insertCoupon({
    money: state.formState.money,
    date: state.formState.date.format('YYYY-MM-DD'),
    hours: state.formState.hours,
    minutes: state.formState.minutes,
    seconds: state.formState.seconds
  }).then(result => {
    message.success('发布优惠券成功');
    emits('onAddOk');
  });
};
</script>

<style scoped>
:deep(.ant-input),
:deep(.ant-btn),
:deep(.ant-select-single:not(.ant-select-customize-input) .ant-select-selector),
:deep(.ant-input-affix-wrapper) {
  border-radius: 4px;
}

.time-select,
.money-input {
  width: 171px;
}
</style>
