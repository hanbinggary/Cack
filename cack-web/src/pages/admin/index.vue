<template>
  <div class="admin container">
    <header>
      <p>管理员管理</p>
    </header>
    <main>
      <a-button type="primary" class="add-btn" @click.prevent="onAdd">
        新增
      </a-button>
      <base-table
        :admins="state.admins"
        :loading="state.loading"
        :pagination="state.pagination"
        @onChange="onChangeTable"
      />
    </main>
    <base-add
      :visible="state.addVisible"
      :roles="state.roles"
      @onCancel="onCancelAddVisible"
    />
  </div>
</template>

<script setup>
import { onBeforeMount, provide, reactive } from 'vue';
import BaseTable from './components/BaseTable.vue';
import BaseAdd from './components/BaseAdd.vue';
import { listAdmin } from '../../api/admin';
import { listRole } from '../../api/role';

const state = reactive({
  admins: [],
  loading: false,
  pagination: {
    current: 1,
    pageSize: 6,
    total: 0
  },
  addVisible: false,
  roles: []
});

const getData = () => {
  state.loading = true;
  listAdmin({
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.admins = result.list;
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
  listRole().then(result => {
    state.roles = result;
    state.addVisible = true;
  });
};

const onCancelAddVisible = () => {
  state.addVisible = false;
};

const onChangeTable = paging => {
  state.pagination.current = paging.current;
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

.form {
  margin-bottom: 32px;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}

.add-btn {
  margin-bottom: 12px;
}
</style>
