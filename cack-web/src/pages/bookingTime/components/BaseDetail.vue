<template>
  <a-modal
    :visible="visible"
    :key="key"
    title="详情"
    @cancel="onClickCancel"
    :footer="null"
  >
    <a-skeleton :loading="loading" :paragraph="{ rows: 8 }">
      <div class="content">
        <div class="time">
          <div class="time-item">
            <span class="time-item-label">日期:</span>
            <span class="time-item-text">{{ bookingTime.date }}</span>
          </div>
          <div class="time-item">
            <span class="time-item-label">时段:</span>
            <span class="time-item-text"
              >{{ bookingTime.startTime }}:00 ~
              {{ bookingTime.endTime }}:00</span
            >
          </div>
          <div class="time-item">
            <span class="time-item-label">预约人数:</span>
            <span class="time-item-text">
              <span class="time-item-total">{{ bookingTime.total }}</span>
              <a-badge v-if="bookingTime.total <= 10" status="processing" />
              <a-badge v-else-if="bookingTime.total <= 30" status="warning" />
              <a-badge v-else status="error" />
            </span>
          </div>
          <div class="time-item">
            <span class="time-item-label">是否有效:</span>
            <a-switch :checked="bookingTime.status" />
          </div>
          <div class="time-item">
            <span class="time-item-label">创建时间:</span>
            <span class="time-item-text">{{ bookingTime.gmtCreate }}</span>
          </div>
          <div class="time-item">
            <span class="time-item-label">更新时间:</span>
            <span class="time-item-text">{{ bookingTime.gmtModified }}</span>
          </div>
          <div class="time-item" v-if="isInvalidReasonVisible">
            <span class="time-item-label">取消原因:</span>
            <span class="time-item-text">{{ bookingTime.invalidReason }}</span>
          </div>
          <a-button class="time-close-btn" @click="onClickCancel"
            >关闭</a-button
          >
        </div>
      </div>
    </a-skeleton>
  </a-modal>
</template>

<script setup>
import {
  computed,
  defineEmit,
  defineProps,
  onBeforeMount,
  onMounted,
  onUpdated,
  ref,
  watch
} from 'vue';
import { getBookingTimeById } from '../../../api/bookingTime';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number
});

const emits = defineEmit(['onChangeVisible']);
const bookingTime = ref({});
const loading = ref(false);

onMounted(() => {
  loading.value = true;
  getBookingTimeById(props.id).then(result => {
    bookingTime.value = result;
    loading.value = false;
  });
});

const isInvalidReasonVisible = computed(() => {
  const { invalidReason } = bookingTime.value;
  if (invalidReason == null) return false;
  if (invalidReason.length === 0) return false;
  return true;
});

const onClickCancel = () => {
  emits('onChangeVisible', false);
};
</script>

<style scoped>
.time {
  width: 300px;
  margin: 0 auto;
}

.time-item {
  padding-bottom: 20px;
}

.time-item-label {
  display: inline-block;
  width: 64px;
  margin-right: 16px;
  color: rgba(0, 0, 0, 0.65);
  text-align: right;
  vertical-align: top;
}

.time-item-label + span {
  display: inline-block;
  width: 220px;
}

.time-item-total {
  margin-right: 4px;
}

:deep(.ant-btn) {
  border-radius: 4px;
}

.time-close-btn {
  width: 100%;
  margin-top: 24px;
  margin-bottom: 16px;
}
</style>
