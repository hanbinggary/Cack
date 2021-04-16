<template>
  <a-page-header title="读取列表" @back="onChangeTag('content')">
    <a-table
      :columns="columns"
      :dataSource="state.userNotifys"
      :pagination="state.pagination"
      :rowKey="record => record.id"
      @change="onChangeTable"
    >
      <template #readed="{ record }">
        <span>{{ record.readed ? '已读' : '未读' }}</span>
      </template>
    </a-table>
  </a-page-header>
</template>

<script setup>
import { defineProps, inject, onBeforeMount, reactive } from 'vue';
import { useRouter } from 'vue-router';
import { listUserNotify } from '../../api/userNotify';

const router = useRouter();
const onChangeTag = inject('onChangeTag');

const props = defineProps({
  id: Number
});

const state = reactive({
  userNotifys: [],
  pagination: {
    current: 1,
    pageSize: 6,
    total: 0
  }
});
const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '用户昵称',
    dataIndex: 'nickname'
  },
  {
    title: '是否已读',
    dataIndex: 'readed',
    slots: {
      customRender: 'readed'
    }
  },
  {
    title: '读取时间',
    dataIndex: 'readedTime'
  }
];

const getData = () => {
  listUserNotify({
    id: props.id,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.userNotifys = result.list;
      state.pagination.total = result.total;
    }
  });
};

onBeforeMount(() => {
  getData();
});

const onChangeTable = paging => {
  state.pagination.current = paging.current;
  getData();
};
</script>
