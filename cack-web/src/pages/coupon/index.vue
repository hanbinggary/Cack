<template>
  <div class="coupon container">
    <header>
      <p>优惠券管理</p>
    </header>
    <main>
      <a-button
        type="primary"
        class="published-btn"
        @click.prevent="onPublishedCoupon"
      >
        发布优惠券
      </a-button>
      <base-table
        :coupons="state.coupons"
        :loading="state.loading"
        :pagination="state.pagination"
        @onChange="onChangeTable"
      />
    </main>
    <base-add
      :visible="state.addVisible"
      @onCancel="onCancelAddVisible"
      @onAddOk="onAddOkCancelVisible"
    />
  </div>
</template>

<script setup>
import { onBeforeMount, provide, reactive } from 'vue';
import { listCoupon } from '../../api/coupon';
import BaseTable from './components/BaseTable.vue';
import BaseAdd from './components/BaseAdd.vue';

const state = reactive({
  coupons: [],
  loading: false,
  pagination: {
    current: 1,
    pageSize: 7,
    total: 0
  },
  expiredSign: 2,
  orderByExpiredTime: 0,
  // 发布优惠券
  addVisible: false
});

const getData = () => {
  state.loading = true;
  listCoupon({
    expiredSign: state.expiredSign,
    orderByExpiredTime: state.orderByExpiredTime,
    pageNum: state.pagination.current,
    pageSize: state.pagination.pageSize
  }).then(result => {
    if (result) {
      state.coupons = result.list;
      state.pagination.total = result.total;
      state.loading = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

provide('getData', getData);

const onChangeTable = (paging, filters, sorter) => {
  state.pagination.current = paging.current;
  // 过期时间排序
  state.orderByExpiredTime = 0;
  if (sorter.order) {
    state.orderByExpiredTime = sorter.order === 'ascend' ? 1 : 2;
  }
  // 过滤是否过期
  if (filters.expired && filters.expired.length === 1) {
    state.expiredSign = filters.expired[0] ? 1 : 0;
  } else {
    state.expiredSign = 2;
  }
  getData();
};

const onPublishedCoupon = () => {
  state.addVisible = true;
};

const onCancelAddVisible = () => {
  state.addVisible = false;
};

const onAddOkCancelVisible = () => {
  state.addVisible = false;
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

.published-btn {
  margin-bottom: 12px;
}
</style>
