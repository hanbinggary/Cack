<template>
  <div class="role container">
    <header>
      <p>权限管理</p>
    </header>
    <main>
      <base-table :roles="state.roles" :loading="state.loading" />
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, provide, reactive } from 'vue';
import BaseTable from './components/BaseTable.vue';
import { listRole } from '../../api/role';

const state = reactive({
  roles: [],
  loading: false
});

const getData = () => {
  state.loading = true;
  listRole().then(result => {
    if (result) {
      state.roles = result;
      state.loading = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

provide('getData', getData);
</script>

<style scoped>
main {
  min-width: 720px;
  padding: 24px;
  border-bottom-right-radius: 16px;
  background: #fff;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}
</style>
