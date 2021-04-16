<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :pagination="pagination"
      :dataSource="users"
      :rowKey="record => record.id"
      @change="onChange"
    >
      <template #avatarUrl="{ record }">
        <img :src="record.avatarUrl" alt="avatar" class="avatar" />
      </template>
      <template #status="{ record }">
        <a-switch
          :checked="record.status"
          @change="onChangeStatus(record.id, record.status)"
        />
      </template>
      <template #operation="{ record }">
        <a @click.prevent="onClickDetail(record.id)">查看</a>
      </template>
    </a-table>
  </a-spin>
  <!-- 查看 -->
  <base-detail
    :visible="detailVisible"
    :key="randomKey"
    :id="currentDetailId"
    @onCancelVisible="onCancelDetailVisible"
  />
</template>

<script setup>
import { createVNode, defineEmit, defineProps, h, inject, ref } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { updateStatusById } from '../../../api/user';
import BaseDetail from './BaseDetail.vue';

const props = defineProps({
  loading: Boolean,
  pagination: Object,
  users: Object
});

const emits = defineEmit(['onChange']);
const getData = inject('getData');
const columns = [
  {
    title: '#',
    dataIndex: 'id'
  },
  {
    title: '头像',
    dataIndex: 'avatarUrl',
    slots: {
      customRender: 'avatarUrl'
    }
  },
  {
    title: '昵称',
    dataIndex: 'nickname'
  },
  {
    title: '手机号',
    dataIndex: 'tel'
  },
  {
    title: '性别',
    dataIndex: 'gender',
    customRender: ({ record }) => {
      if (record.gender === 0) return h('span', '未知');
      else if (record.gender === 1) return h('span', '女');
      else return h('span', '男');
    }
  },
  {
    title: '省份',
    dataIndex: 'province'
  },
  {
    title: '城市',
    dataIndex: 'city'
  },
  {
    title: '是否启用',
    dataIndex: 'status',
    slots: {
      customRender: 'status'
    },
    filters: [
      {
        text: '无效',
        value: false
      },
      {
        text: '有效',
        value: true
      }
    ]
  },
  {
    title: '最后登录时间',
    dataIndex: 'lastLoginTime',
    sorter: true
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: {
      customRender: 'operation'
    }
  }
];
// 查看
const detailVisible = ref(false);
const currentDetailId = ref(0);
const randomKey = ref(Math.random());

const onChange = (pagination, filters, sorter) => {
  emits('onChange', pagination, filters, sorter);
};

const onChangeStatus = (id, status) => {
  let operation = '启用';
  if (status) {
    operation = '禁用';
  }

  Modal.confirm({
    title: `${operation}账户`,
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      { style: 'color:red;' },
      `您确定${operation}该账户吗？`
    ),
    onOk() {
      updateStatusById({ id, status: !status }).then(() => {
        message.success(`${operation}账户成功`);
        getData();
      });
    }
  });
};

const onClickDetail = id => {
  currentDetailId.value = id;
  detailVisible.value = true;
  randomKey.value = Math.random();
};

const onCancelDetailVisible = () => {
  detailVisible.value = false;
};
</script>

<style scoped>
.avatar {
  width: 32px;
  height: 32px;
  border-radius: 50%;
}
</style>
