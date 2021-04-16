<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :dataSource="roles"
      :rowKey="record => record.id"
    >
      <template #desc="{ record }">
        <a-input
          v-model:value="record.desc"
          class="desc-input"
          @blur="onBlurDesc(record)"
          maxlength="100"
        />
      </template>
    </a-table>
  </a-spin>
</template>

<script setup>
import { message } from 'ant-design-vue';
import { defineProps, inject } from 'vue';
import { updateDescById } from '../../../api/role';

defineProps({
  loading: Boolean,
  roles: Array
});
const getData = inject('getData');
const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '角色名称',
    dataIndex: 'name'
  },
  {
    title: '角色描述',
    dataIndex: 'desc',
    slots: {
      customRender: 'desc'
    }
  },
  {
    title: '更新时间',
    dataIndex: 'gmtModified'
  }
];

const onBlurDesc = record => {
  updateDescById({ id: record.id, desc: record.desc }).then(() => {
    message.success('成功更新角色描述');
    getData();
  });
};
</script>

<style scoped>
.desc-input {
  padding: 4px 8px;
  outline: none;
  border-radius: 4px;
  border: 1px solid transparent;
}

.desc-input:hover {
  border-color: #ddd;
}
</style>
