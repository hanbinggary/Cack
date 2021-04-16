<template>
  <div class="tab-container station">
    <a-skeleton :loading="loading">
      <h3>验车站设置</h3>
      <a-form
        layout="vertical"
        :model="formState"
        ref="formRef"
        :rules="formRules"
        class="form"
      >
        <a-form-item label="验车站名称" name="name">
          <a-input
            placeholder="用户名"
            maxlength="20"
            v-model:value="formState.name"
          />
        </a-form-item>
        <a-form-item label="地址" name="addr">
          <a-input
            placeholder="地址"
            maxlength="50"
            v-model:value="formState.addr"
          />
        </a-form-item>
        <a-form-item label="经度" name="longitude">
          <a-input
            placeholder="经度"
            maxlength="10"
            v-model:value="formState.longitude"
          />
        </a-form-item>
        <a-form-item label="纬度" name="latitude">
          <a-input
            placeholder="纬度"
            maxlength="10"
            v-model:value="formState.latitude"
          />
        </a-form-item>
        <a-form-item label="手机号" name="phone">
          <a-input
            placeholder="手机号"
            maxlength="11"
            v-model:value="formState.phone"
          />
        </a-form-item>
        <a-form-item label="时段最大预约数" name="maxTimeBookingTotal">
          <a-input-number
            v-model:value="formState.maxTimeBookingTotal"
            :min="1"
          />
        </a-form-item>
        <a-form-item label="联系电话" name="tel">
          <a-input
            placeholder="联系电话"
            maxlength="15"
            v-model:value="formState.tel"
          />
        </a-form-item>
        <a-form-item label="营业时间" name="openingTime">
          <a-select
            v-model:value="formState.startTime"
            class="opening-time-select"
            @change="onSelectStartTime"
          >
            <a-select-option
              v-for="startTime in startTimes"
              :key="startTime.value"
              :value="startTime.value"
              >{{ startTime.value }}:00</a-select-option
            >
          </a-select>
          <a-select
            v-model:value="formState.endTime"
            class="opening-time-select"
          >
            <a-select-option
              v-for="endTime in endTimes"
              :key="endTime.value"
              :value="endTime.value"
              >{{ endTime.value }}:00</a-select-option
            >
          </a-select>
        </a-form-item>
        <a-form-item label="描述" name="desc">
          <a-textarea
            placeholder="描述"
            v-model:value="formState.desc"
            showCount
            :maxlength="200"
            :autoSize="{ minRows: 8, maxRows: 8 }"
          />
        </a-form-item>
        <a-form-item>
          <a-button type="primary" @click.prevent="onUpdate">
            更新验车站信息
          </a-button>
        </a-form-item>
      </a-form>
    </a-skeleton>
  </div>
</template>

<script setup>
import {
  onBeforeMount,
  onBeforeUpdate,
  onMounted,
  onUpdated,
  reactive,
  ref,
  toRaw
} from 'vue';
import store from '../../../store';
import { getStation, updateStation } from '../../../api/station';
import { message } from 'ant-design-vue';

const currentId = ref(store.getters.id);
const formRef = ref();
const loading = ref(false);
const startTimes = ref([]);
const endTimes = ref([]);
const formState = ref({});

const validateMaxTimeBookingTotal = (rule, value) => {
  if (!value) {
    return Promise.reject('时段最大预约数不能为空');
  }
  if (typeof value !== 'number') {
    return Promise.reject('时段最大预约数只能是正整数');
  } else {
    return Promise.resolve();
  }
};

const formRules = {
  name: [
    {
      required: true,
      message: '验车站名称不能为空',
      trigger: 'blur'
    }
  ],
  addr: [
    {
      required: true,
      message: '地址不能为空',
      trigger: 'blur'
    }
  ],
  longitude: [
    {
      required: true,
      message: '经度不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^\d+[.]?\d+$/,
      message: '经度只能包含数字和.'
    }
  ],
  latitude: [
    {
      required: true,
      message: '纬度不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^\d+[.]?\d+$/,
      message: '纬度只能包含数字和.'
    }
  ],
  phone: [
    {
      required: true,
      message: '手机号不能为空',
      trigger: 'blur'
    },
    {
      pattern: /^1[3-9]\d{9}$/,
      message: '手机号格式不正确',
      trigger: 'blur'
    }
  ],
  maxTimeBookingTotal: [
    {
      required: true,
      validator: validateMaxTimeBookingTotal,
      trigger: 'blur'
    }
  ]
};

const getData = () => {
  loading.value = true;
  getStation(currentId.value).then(result => {
    if (result) {
      result.startTime =
        result.openingTime &&
        parseInt(result.openingTime.replace(/:00[@]*/g, '').slice(0, 2));
      result.endTime =
        result.openingTime &&
        parseInt(result.openingTime.replace(/:00[@]*/g, '').slice(2, 4));
      formState.value = result;
      loading.value = false;
    }
  });
};

onBeforeMount(() => {
  generateStartTime(0);
  generateEndTime(0);
  getData();
});

const generateStartTime = () => {
  const times = [];
  for (let i = 0; i < 24; i++) {
    times.push({ value: i });
  }
  startTimes.value = times;
};

const generateEndTime = start => {
  const times = [];
  for (let i = start; i < 24; i++) {
    times.push({ value: i });
  }
  endTimes.value = times;
};

const onSelectStartTime = selectKey => {
  generateEndTime(selectKey);
};

const onUpdate = () => {
  formRef.value.validate().then(() => {
    updateStation({
      name: formState.value.name,
      addr: formState.value.addr,
      longitude: formState.value.longitude,
      latitude: formState.value.latitude,
      desc: formState.value.desc,
      phone: formState.value.phone,
      tel: formState.value.tel,
      maxTimeBookingTotal: formState.value.maxTimeBookingTotal,
      openingTime:
        (formState.value.startTime + '').padStart(2, 0) +
        ':00@' +
        (formState.value.endTime + '').padStart(2, 0) +
        ':00'
    }).then(result => {
      if (result) {
        message.success('成功更新验车站信息');
        getData();
      }
    });
  });
};
</script>

<style scoped>
.tab-container {
  padding: 8px 16px;
}

.station > h3 {
  margin-bottom: 24px;
  font-size: 18px;
  line-height: 28px;
}

.form {
  width: 388px;
}

:deep(.ant-select-single:not(.ant-select-customize-input) .ant-select-selector),
:deep(.ant-input),
:deep(.ant-btn),
:deep(.ant-input-number) {
  border-radius: 4px;
}

:deep(.ant-input) {
  width: 100%;
}

.opening-time-select {
  width: 190px;
}

.opening-time-select:first-child {
  margin-right: 8px;
}
</style>
