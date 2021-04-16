<template>
  <a-modal
    :visible="visible"
    :key="key"
    title="预约详情"
    @cancel="$emit('onCancel')"
    @ok="$emit('onCancel')"
    :width="820"
  >
    <a-descriptions title="订单信息" :column="6" layout="horizontal">
      <a-descriptions-item label="订单号" span="3">
        {{ booking.orderNo }}
      </a-descriptions-item>
      <a-descriptions-item label="订单状态" span="3">
        <a-tag color="green" v-if="booking.status === 1">待车检</a-tag>
        <a-tag color="purple" v-else-if="booking.status === 2">车检中</a-tag>
        <a-tag color="blue" v-else-if="booking.status === 3">车检完成</a-tag>
        <a-tag color="red" v-else>订单取消</a-tag>
      </a-descriptions-item>
      <a-descriptions-item label="下单时间" span="3">
        {{ booking.gmtCreate }}
      </a-descriptions-item>
      <a-descriptions-item label="更新时间" span="3">
        {{ booking.gmtModified }}
      </a-descriptions-item>
      <a-descriptions-item label="预约时间" span="3">
        {{ booking.bookingTime && booking.bookingTime.date }}
        {{ booking.bookingTime && booking.bookingTime.startTime }}:00 ~
        {{ booking.bookingTime && booking.bookingTime.endTime }}:00
      </a-descriptions-item>
      <a-descriptions-item
        label="开始车检时间"
        v-if="booking.status > 1"
        span="3"
      >
        {{ booking.startCheckTime }}
      </a-descriptions-item>
      <a-descriptions-item
        label="结束车检时间"
        v-if="booking.status > 2"
        span="3"
      >
        {{ booking.endCheckTime }}
      </a-descriptions-item>
      <a-descriptions-item
        label="取消车检原因"
        v-if="booking.status === 0"
        span="6"
      >
        {{ booking.cancelReason }}
      </a-descriptions-item>
    </a-descriptions>
    <a-descriptions
      title="用户信息"
      :column="6"
      layout="horizontal"
      v-if="booking.user"
    >
      <a-descriptions-item label="用户昵称" span="2">
        {{ booking.user.nickname }}
      </a-descriptions-item>
      <a-descriptions-item label="手机号" span="2">
        {{ booking.user.tel }}
      </a-descriptions-item>
      <a-descriptions-item label="省份城市" span="2">
        {{ booking.user.province }}
        {{ booking.user.city }}
      </a-descriptions-item>
    </a-descriptions>
    <a-descriptions
      title="车辆信息"
      :column="6"
      layout="horizontal"
      v-if="booking.vehicle"
    >
      <a-descriptions-item label="车牌号" span="2">
        {{ booking.vehicle.plateNo }}
      </a-descriptions-item>
      <a-descriptions-item label="车辆类型" span="2">
        {{ booking.vehicle.vehicleType }}
      </a-descriptions-item>
      <a-descriptions-item label="使用性质" span="2">
        {{ booking.vehicle.useCharacter }}
      </a-descriptions-item>
      <a-descriptions-item label="车辆注册日期" span="2">
        {{ booking.vehicle.registerDate }}
      </a-descriptions-item>
      <a-descriptions-item label="总里程数" span="2">
        {{ booking.vehicle.odometer }}
      </a-descriptions-item>
      <a-descriptions-item label="发动机号码" span="2">
        {{ booking.vehicle.engineNo }}
      </a-descriptions-item>
      <a-descriptions-item label="车辆识别代码" span="3">
        {{ booking.vehicle.vin }}
      </a-descriptions-item>
    </a-descriptions>
    <a-descriptions
      title="使用优惠券"
      :column="6"
      layout="horizontal"
      v-if="booking.coupon && !booking.coupon.expired && booking.coupon.status"
    >
      <a-descriptions-item label="金额" span="3">
        {{ booking.coupon.money }}
      </a-descriptions-item>
    </a-descriptions>
  </a-modal>
</template>

<script setup>
import { defineEmit, defineProps, onBeforeMount, onMounted, ref } from 'vue';
import { getBookingById } from '../../../api/booking';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number
});

const emits = defineEmit(['onCancel']);
const booking = ref({});

onMounted(() => {
  getBookingById(props.id).then(result => {
    if (result) {
      booking.value = result;
    }
  });
});
</script>
