<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :dataSource="bookings"
      :rowKey="record => record.id"
      :pagination="pagination"
      @change="onChange"
    >
      <template #status="{ record }">
        <a-tag color="green" v-if="record.status === 1">待车检</a-tag>
        <a-tag color="purple" v-else-if="record.status === 2">车检中</a-tag>
        <a-tag color="blue" v-else-if="record.status === 3">车检完成</a-tag>
        <a-tag color="red" v-else-if="record.status === 4">订单取消</a-tag>
        <a-tag color="red" v-else>强制取消</a-tag>
      </template>
      <template #bookingTime="{ record }">
        <span
          >{{ record.bookingTime.date }} {{ record.bookingTime.startTime }}:00 ~
          {{ record.bookingTime.endTime }}:00</span
        >
      </template>
      <template #operation="{ record }">
        <a @click.prevent="onDetail(record.id)">查看</a>
        <a-divider type="vertical" />
        <a
          @click.prevent="onUpdateStatus(record.id, record.status)"
          v-if="record.status === 1 || record.status === 2"
        >
          更新状态
        </a>
      </template>
    </a-table>
    <base-detail
      :visible="state.detailVisible"
      :key="state.key"
      :id="state.detailId"
      @onCancel="onCancelDetail"
    />
    <base-update-status
      :visible="state.updateStatusVisible"
      :key="state.key"
      :id="state.updateStatusId"
      :status="state.updateStatusValue"
      @onCancel="onCancelUpdateStatus"
      @onUpdateStatusValue="onUpdateStatusValue"
    />
  </a-spin>
</template>

<script setup>
import { defineEmit, defineProps, inject, reactive } from 'vue';
import BaseDetail from './BaseDetail.vue';
import BaseUpdateStatus from './BaseUpdateStatus.vue';

defineProps({
  loading: Boolean,
  bookings: Array,
  pagination: Object
});

const emits = defineEmit(['onChange']);
const getData = inject('getData');
const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '订单号',
    dataIndex: 'orderNo'
  },
  {
    title: '订单状态',
    dataIndex: 'status',
    slots: {
      customRender: 'status'
    },
    filters: [
      {
        text: '强制取消',
        value: 0
      },
      {
        text: '待车检',
        value: 1
      },
      {
        text: '车检中',
        value: 2
      },
      {
        text: '车检完成',
        value: 3
      },
      {
        text: '订单取消',
        value: 4
      }
    ]
  },
  {
    title: '预约时间',
    slots: {
      customRender: 'bookingTime'
    }
  },
  {
    title: '下单时间',
    dataIndex: 'gmtCreate',
    sorter: true
  },
  {
    title: '用户昵称',
    dataIndex: 'user.nickname'
  },
  {
    title: '车牌号',
    dataIndex: 'vehicle.plateNo'
  },
  {
    title: '操作',
    slots: {
      customRender: 'operation'
    }
  }
];

const state = reactive({
  detailVisible: false,
  detailId: 0,
  key: Math.random(),
  updateStatusVisible: false,
  updateStatusId: 0,
  updateStatusValue: 1
});

const onChange = (paging, filters, sorter) => {
  emits('onChange', paging, filters, sorter);
};

const onDetail = id => {
  state.detailVisible = true;
  state.key = Math.random();
  state.detailId = id;
};

const onCancelDetail = () => {
  state.detailVisible = false;
};

const onUpdateStatus = (id, status) => {
  state.updateStatusVisible = true;
  state.key = Math.random();
  state.updateStatusId = id;
  state.updateStatusValue = status;
};

const onCancelUpdateStatus = () => {
  state.updateStatusVisible = false;
};

const onUpdateStatusValue = status => {
  state.updateStatusValue = status;
};
</script>
