<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :pagination="pagination"
      :dataSource="vehicles"
      :rowKey="record => record.id"
      @change="onChange"
    >
      <template #operation="{ record }">
        <a @click.prevent="onClickDetail(record.id)">查看</a>
      </template>
    </a-table>
  </a-spin>
  <base-detail
    :visible="detailVisible"
    :id="currentDetailId"
    :key="randomKey"
    @onCancel="onCancelDetail"
  />
</template>

<script setup>
import { defineEmit, defineProps, ref } from 'vue';
import BaseDetail from './BaseDetail.vue';

const props = defineProps({
  loading: Boolean,
  vehicles: Array,
  pagination: Object
});

const emits = defineEmit(['onChangeTable']);
const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '车牌号',
    dataIndex: 'plateNo'
  },
  {
    title: '车辆类型',
    dataIndex: 'vehicleType'
  },
  {
    title: '使用性质',
    dataIndex: 'useCharacter'
  },
  {
    title: '用户昵称',
    dataIndex: 'nickname'
  },
  {
    title: '手机号',
    dataIndex: 'tel'
  },
  {
    title: '注册日期',
    dataIndex: 'registerDate'
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: {
      customRender: 'operation'
    }
  }
];

const detailVisible = ref(false);
const randomKey = ref(Math.random());
const currentDetailId = ref(0);

const onChange = paging => {
  emits('onChangeTable', paging);
};

const onClickDetail = id => {
  currentDetailId.value = id;
  randomKey.value = Math.random();
  detailVisible.value = true;
};

const onCancelDetail = () => {
  detailVisible.value = false;
  console.log('取消');
};
</script>
