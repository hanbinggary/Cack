<template>
  <div class="vehicle container">
    <header>
      <p>车辆管理</p>
    </header>
    <main>
      <a-form class="form">
        <a-input-search
          v-model:value="state.search"
          placeholder="车牌号或用户昵称"
          enter-button
          @search="onSearch"
          class="vehicle-input-search"
          allowClear
        />
      </a-form>
      <base-table
        :loading="state.loading"
        :vehicles="state.vehicles"
        :pagination="state.pagination"
        @onChangeTable="onChangeTable"
      />
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, reactive } from 'vue';
import { listVehicle } from '../../api/vehicle';
import BaseTable from './components/BaseTable.vue';

const state = reactive({
  vehicles: [],
  loading: false,
  search: '',
  pagination: {
    current: 1,
    pageSize: 7,
    total: 0
  }
});

const getData = () => {
  listVehicle({
    searchValue: state.search,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.vehicles = result.list;
      state.pagination.total = result.total;
    }
  });
};

onBeforeMount(() => {
  getData();
});

const onSearch = () => {
  state.pagination.current = 1;
  getData();
};

const onChangeTable = paging => {
  state.pagination.current = paging.current;
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

.vehicle-input-search {
  width: 260px;
}
</style>
