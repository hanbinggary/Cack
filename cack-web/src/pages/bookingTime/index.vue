<template>
  <div class="booking-time container">
    <header>
      <p>可预约时间</p>
    </header>
    <main>
      <a-form class="form">
        <a-date-picker
          placeholder="选择日期"
          v-model:value="date"
          @change="onChangeDate"
        />
        <a-button type="primary" class="add-btn" @click.prevent="onAdd">
          新增
        </a-button>
      </a-form>
      <base-table
        :date="date"
        :dataSource="dataSource"
        :loading="loading"
        :pagination="pagination"
        @onChangeTable="onChangeTable"
      />
      <!-- 新增 -->
      <base-add :visible="addModalVisible" @onCancel="onCancelAdd" />
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, provide, ref } from 'vue';
import { Form, DatePicker, Button } from 'ant-design-vue';
import moment from 'moment';
import { listBookingTime } from '../../api/bookingTime';
import BaseTable from './components/BaseTable.vue';
import BaseAdd from './components/BaseAdd.vue';
import { getElementValue } from '@antv/g2/lib/interaction/action/util';

const dataSource = ref([]);
const loading = ref(false);
const date = ref(); // 选择日期
// 分页
const pagination = ref({
  current: 1,
  pageSize: 6,
  total: 0
});
const orderByTotal = ref(0); // 0-默认，1-升序，2-降序
// 新增
const addModalVisible = ref(false);
// 状态筛选标志
const statusSign = ref(2); // 0-false，1-true，2-默认

const getData = () => {
  listBookingTime({
    date: date.value && date.value.format('YYYY-MM-DD'),
    orderByTotal: orderByTotal.value,
    statusSign: statusSign.value,
    pageNum: pagination.value.current,
    pageSize: pagination.value.pageSize
  }).then(result => {
    if (result) {
      dataSource.value = result.list;
      pagination.value.total = result.total;
      loading.value = false;
    }
  });
};

onBeforeMount(() => {
  loading.value = true;
  getData();
});

provide('getData', getData);

const onChangeDate = () => {
  getData();
};

const onChangeTable = (paging, filters, sorter) => {
  pagination.value.current = paging.current;
  orderByTotal.value = 0;
  if (sorter.order) {
    orderByTotal.value = sorter.order === 'ascend' ? 1 : 2;
  }
  // 根据状态筛选
  if (filters.status && filters.status.length === 1) {
    statusSign.value = filters.status[0] ? 1 : 0;
  } else {
    statusSign.value = 2;
  }
  getData();
};

const onAdd = () => {
  addModalVisible.value = true;
};

const onCancelAdd = () => {
  addModalVisible.value = false;
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
  margin-left: 12px;
}
</style>
