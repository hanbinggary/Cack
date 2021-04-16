<template>
  <div class="booking container">
    <header>
      <p>预约管理</p>
    </header>
    <main>
      <a-input-search
        v-model:value="state.searchText"
        placeholder="输入订单号、用户昵称或车牌号"
        enter-button
        allowClear
        class="search-input"
        @search="onSearch"
      />
      <base-table
        :loading="state.loading"
        :bookings="state.bookings"
        :pagination="state.pagination"
        @onChange="onChangeTable"
      />
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, provide, reactive } from 'vue';
import { listBooking } from '../../api/booking';
import BaseTable from './components/BaseTable.vue';

const state = reactive({
  bookings: [],
  loading: false,
  pagination: {
    current: 1,
    pageSize: 7,
    total: 0
  },
  orderByGmtCreate: 0, // 0-默认，1-升序，2-降序
  filterByOrderStatus: '',
  searchText: ''
});

const getData = () => {
  state.loading = true;
  listBooking({
    searchText: state.searchText,
    orderByGmtCreate: state.orderByGmtCreate,
    filterByOrderStatus: state.filterByOrderStatus,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.bookings = result.list;
      state.pagination.total = result.total;
      state.loading = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

provide('getData', getData);

const onSearch = () => {
  state.pagination.current = 1;
  getData();
};

const onChangeTable = (paging, filters, sorter) => {
  state.pagination.current = paging.current;
  state.orderByGmtCreate = 0;
  if (sorter.order) {
    state.orderByGmtCreate = sorter.order === 'ascend' ? 1 : 2;
  }
  if (filters.status && filters.status.length > 0) {
    state.filterByOrderStatus = filters.status.join(',');
  } else {
    state.filterByOrderStatus = '';
  }
  getData();
};
</script>

<style scoped>
main {
  min-width: 720px;
  padding: 24px;
  border-bottom-right-radius: 16px;
  background: #fff;
}

.search-input {
  width: 320px;
  margin-bottom: 16px;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}
</style>
