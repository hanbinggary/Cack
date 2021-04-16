<template>
  <a-button type="primary" class="add-btn" @click.prevent="onAdd">
    发布系统消息
  </a-button>
  <base-table
    :notifys="state.notifys"
    :loading="state.loading"
    :pagination="state.pagination"
    @onChange="onChangeTable"
  />
  <base-add :visible="state.addVisible" @onCancel="onCancelAddVisible" />
</template>

<script setup>
import { onBeforeMount, provide, reactive } from 'vue';
import { listNotify } from '../../api/notify';
import BaseAdd from './components/BaseAdd.vue';
import BaseTable from './components/BaseTable.vue';

const state = reactive({
  notifys: [],
  loading: false,
  pagination: {
    current: 1,
    pageSize: 5,
    total: 0
  },
  orderByGmtCreate: 0, // 0-默认，1-升序，2-降序
  addVisible: false
});

const getData = () => {
  state.loading = true;
  listNotify({
    orderByGmtCreate: state.orderByGmtCreate,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.notifys = result.list;
      state.pagination.total = result.total;
      state.loading = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

provide('getData', getData);

const onAdd = () => {
  state.addVisible = true;
};

const onCancelAddVisible = () => {
  state.addVisible = false;
};

const onChangeTable = (paging, sorter) => {
  state.pagination.current = paging.current;
  state.orderByGmtCreate = 0;
  if (sorter.order) {
    state.orderByGmtCreate = sorter.order === 'ascend' ? 1 : 2;
  }
  getData();
};
</script>

<style scoped>
.add-btn {
  margin-bottom: 16px;
}
</style>
