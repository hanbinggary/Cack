<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :dataSource="admins"
      :rowKey="record => record.id"
      :pagination="pagination"
      @change="onChange"
    >
      <template #status="{ record }">
        <a-switch
          :checked="record.status"
          :disabled="record.id === 1"
          @change="onChangeStatus(record.id, record.status)"
        />
      </template>
      <template #operation="{ record }">
        <template v-if="record.id !== 1">
          <a @click.prevent="onResetPassword(record.id)">重置密码</a>
          <a-divider type="vertical" />
          <a @click.prevent="onResetRole(record.id, record.roleId)">分配角色</a>
          <a-divider type="vertical" />
          <a @click.prevent="onDelete(record.id)">删除</a>
        </template>
      </template>
    </a-table>
    <base-reset-role
      :key="state.randomKey"
      :visible="state.resetRoleVisible"
      :id="state.currentResetId"
      :roleId="state.currentResetRoleId"
      :roles="state.roles"
      @onCancel="onCancelResetRoleVisible"
    />
  </a-spin>
</template>

<script setup>
import { defineEmit, defineProps, inject, reactive, createVNode } from 'vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { message, Modal } from 'ant-design-vue';
import BaseResetRole from './BaseResetRole.vue';
import { listRole } from '../../../api/role';
import {
  resetPasswordById,
  deleteAdminById,
  updateStatusById
} from '../../../api/admin';

defineProps({
  loading: Boolean,
  admins: Array,
  pagination: Object
});
const emits = defineEmit(['onChange']);
const getData = inject('getData');
const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '用户名',
    dataIndex: 'username'
  },
  {
    title: '昵称',
    dataIndex: 'nickname'
  },
  {
    title: '手机号',
    dataIndex: 'phone'
  },
  {
    title: '权限',
    dataIndex: 'roleName'
  },
  {
    title: '最后登录',
    dataIndex: 'lastLoginTime'
  },
  {
    title: '是否启用',
    dataIndex: 'status',
    slots: {
      customRender: 'status'
    }
  },
  {
    title: '操作',
    slots: {
      customRender: 'operation'
    }
  }
];

const state = reactive({
  // 分配角色
  resetRoleVisible: false,
  currentResetId: 0,
  currentResetRoleId: 0,
  roles: [],
  randomKey: Math.random()
});

const onResetRole = (id, roleId) => {
  listRole().then(result => {
    state.resetRoleVisible = true;
    state.currentResetId = id;
    state.currentResetRoleId = roleId;
    state.randomKey = Math.random();
    state.roles = result;
  });
};

const onCancelResetRoleVisible = () => {
  state.resetRoleVisible = false;
};

const onResetPassword = id => {
  Modal.confirm({
    title: '重置密码',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      {
        style: 'color:red;'
      },
      '您确定将当前用户密码重置为123456吗？'
    ),
    onOk() {
      resetPasswordById(id).then(() => {
        message.success('成功重置密码');
      });
    }
  });
};

const onDelete = id => {
  Modal.confirm({
    title: '删除管理员',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      {
        style: 'color:red;'
      },
      '您确定删除当前管理员吗？'
    ),
    onOk() {
      deleteAdminById(id).then(() => {
        message.success('删除成功');
        getData();
      });
    }
  });
};

const onChangeStatus = (id, status) => {
  let operation = '启用';
  if (status) {
    operation = '禁用';
  }
  Modal.confirm({
    title: `${operation}管理员`,
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      {
        style: 'color:red;'
      },
      `您确定${operation}当前管理员吗？`
    ),
    onOk() {
      updateStatusById({ id, status: !status }).then(result => {
        message.success('成功更新用户状态');
        getData();
      });
    }
  });
};

const onChange = paging => {
  emits('onChange', paging);
};
</script>

<style scoped></style>
