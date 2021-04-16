<template>
  <a-modal :visible="visible" title="新增" @cancel="onCancel" @ok="onOk">
    <a-form :model="formState" ref="formRef">
      <a-form-item label="选择日期" name="date">
        <a-range-picker
          v-model:value="formState.date"
          :disabledDate="disabledDate"
        />
      </a-form-item>
      <a-form-item label="选择时段" name="time">
        <a-select
          v-model:value="formState.startTime"
          style="width: 160px"
          @change="onChangeStartTime"
        >
          <a-select-option
            v-for="startTime in startTimes"
            :value="startTime.value"
            >{{ startTime.value }}:00</a-select-option
          >
        </a-select>
        <a-select
          v-model:value="formState.endTime"
          style="width: 160px; margin-left: 16px"
        >
          <a-select-option
            v-for="endTime in endTimes"
            :key="endTime.value"
            :value="endTime.value"
            >{{ endTime.value }}:00</a-select-option
          >
        </a-select>
      </a-form-item>
      <a-form-item label="选择天数">
        <a-checkbox-group v-model:value="formState.weeklyList">
          <a-row>
            <a-col :span="7">
              <a-checkbox value="MONDAY">星期一</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="TUESDAY">星期二</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="WEDNESDAY">星期三</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="THURSDAY">星期四</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="FRIDAY">星期五</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="SATURDAY">星期六</a-checkbox>
            </a-col>
            <a-col :span="7">
              <a-checkbox value="SUNDAY">星期日</a-checkbox>
            </a-col>
          </a-row>
        </a-checkbox-group>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import moment from 'moment';
import {
  defineEmit,
  defineProps,
  inject,
  onBeforeMount,
  ref,
  toRaw
} from 'vue';
import { insertBookingTimes } from '../../../api/bookingTime';

const props = defineProps({
  visible: Boolean
});

const emits = defineEmit(['onCancel']);
const getData = inject('getData');

const formRef = ref();
const dateFormat = 'YYYY-MM-DD';
const startTimes = ref([]);
const endTimes = ref([]);
const formState = ref({
  date: [
    moment(new Date(), dateFormat),
    moment(new Date(), dateFormat).add(14, 'days')
  ],
  startTime: '',
  endTime: '',
  weeklyList: [
    'MONDAY',
    'TUESDAY',
    'WEDNESDAY',
    'THURSDAY',
    'FRIDAY',
    'SATURDAY',
    'SUNDAY'
  ]
});

onBeforeMount(() => {
  generateTimes();
});

const generateTimes = () => {
  const startTimeList = [];
  const endTimeList = [];
  for (let i = 6; i <= 21; i++) {
    startTimeList.push({
      value: i
    });
  }

  endTimeList.push(...startTimeList);
  endTimeList.shift();
  startTimeList.pop();

  formState.value.startTime = startTimeList[0] && startTimeList[0].value;
  formState.value.endTime = endTimeList[0] && endTimeList[0].value;
  startTimes.value = startTimeList;
  endTimes.value = endTimeList;
};

// 不可选择的日期
const disabledDate = currentDate => {
  if (!currentDate) return false;
  return (
    currentDate < moment().subtract(1, 'days') ||
    currentDate > moment().add(28, 'days')
  );
};

// 更新时段的开始时间
const onChangeStartTime = startTime => {
  const endTimeList = [];
  for (let i = startTime + 1; i <= 21; i++) {
    endTimeList.push({
      value: i
    });
  }
  formState.value.endTime = endTimeList[0] && endTimeList[0].value;
  endTimes.value = endTimeList;
};

const onCancel = () => {
  emits('onCancel');
};

const onOk = () => {
  formRef.value.validate().then(result => {
    const params = toRaw(formState.value);
    params.startDate = params.date[0].format(dateFormat);
    params.endDate = params.date[1].format(dateFormat);
    insertBookingTimes(params).then(ids => {
      message.success(`新增${ids.length}条数据`);
      emits('onCancel');
      getData();
    });
  });
};
</script>
