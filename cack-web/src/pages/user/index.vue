<template>
  <div class="user container">
    <header>
      <p>用户管理</p>
    </header>
    <main>
      <a-form class="form">
        <a-date-picker
          placeholder="注册日期"
          v-model:value="state.date"
          @change="onChangeDate"
        />
        <a-input-search
          v-model:value="state.search"
          placeholder="用户名"
          enter-button
          @search="onSearch"
          class="user-input-search"
          allowClear
        />
      </a-form>
      <base-table
        :loading="state.loading"
        :users="state.users"
        :pagination="state.pagination"
        @onChange="onChangeTable"
      />
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, provide, reactive, ref } from 'vue';
import BaseTable from './components/BaseTable.vue';
import { listUser } from '../../api/user';
import moment from 'moment';

const state = reactive({
  users: [],
  loading: false,
  date: null,
  search: '',
  pagination: {
    current: 1,
    pageSize: 6,
    total: 0
  },
  orderByLastLoginTime: 0,
  statusSign: 2 //  0-false, 1-true, 2-默认
});

const getData = () => {
  state.loading = true;
  listUser({
    nickname: state.search,
    date: state.date && state.date.format('YYYY-MM-DD'),
    orderByLastLoginTime: state.orderByLastLoginTime,
    statusSign: state.statusSign,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    console.log('result ==> ', result);
    if (result) {
      state.users = result.list;
      state.pagination.total = result.total;
      state.loading = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

provide('getData', getData);

const onChangeDate = () => {
  getData();
};

const onSearch = () => {
  getData();
};

const onChangeTable = (paging, filters, sorter) => {
  state.pagination.current = paging.current;
  // 根据最后登录时间排序
  state.orderByLastLoginTime = 0;
  if (sorter.order) {
    state.orderByLastLoginTime = sorter.order === 'ascend' ? 1 : 2;
  }
  // 根据状态筛选
  if (filters.status && filters.status.length === 1) {
    state.statusSign = filters.status[0] ? 1 : 0;
  } else {
    state.statusSign = 2;
  }
  getData();
};
</script>

<style scoped>
main {
  min-width: 820px;
  padding: 24px;
  border-bottom-right-radius: 16px;
  background: #fff;
}

.form {
  margin-bottom: 32px;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}

:deep(.ant-input-affix-wrapper) {
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
}

.user-input-search {
  width: 260px;
  margin: 0 12px;
}
</style>
