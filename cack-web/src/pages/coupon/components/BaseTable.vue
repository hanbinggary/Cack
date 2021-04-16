<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :dataSource="coupons"
      :rowKey="record => record.id"
      :pagination="pagination"
      @change="onChange"
    >
      <template #expired="{ record }">
        <a-switch :checked="record.expired" />
      </template>
      <template #status="{ record }">
        <a-switch :checked="record.status" @change="onChangeStatus(record)" />
      </template>
      <template #operation="{ record }">
        <a @click.prevent="onDelete(record.id)">删除</a>
      </template>
    </a-table>
  </a-spin>
</template>

<script setup>
import { message, Modal } from 'ant-design-vue';
import { createVNode, defineEmit, defineProps, h, inject } from 'vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { updateStatusById, deleteCouponById } from '../../../api/coupon';

defineProps({
  loading: Boolean,
  coupons: Array,
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
    title: '金额',
    dataIndex: 'money'
  },
  {
    title: '过期时间',
    dataIndex: 'expiredTime',
    sorter: true
  },
  {
    title: '创建时间',
    dataIndex: 'gmtCreate'
  },
  {
    title: '是否过期',
    dataIndex: 'expired',
    slots: {
      customRender: 'expired'
    },
    filters: [
      {
        text: '过期',
        value: 1
      },
      {
        text: '未过期',
        value: 0
      }
    ]
  },
  {
    title: '是否有效',
    dataIndex: 'status',
    slots: {
      customRender: 'status'
    }
  },
  {
    title: '领取数',
    dataIndex: 'total'
  },
  {
    title: '操作',
    dataIndex: 'operation',
    slots: {
      customRender: 'operation'
    }
  }
];

const onChange = (paging, filter, sorter) => {
  emits('onChange', paging, filter, sorter);
};

const onChangeStatus = coupon => {
  if (coupon.expired) {
    message.warning('优惠券已过期，不能修改状态');
  } else {
    updateStatusById({ id: coupon.id, status: !coupon.status }).then(() => {
      message.success('状态修改成功');
      getData();
    });
  }
};

const onDelete = id => {
  Modal.confirm({
    title: '删除优惠券',
    icon: createVNode(ExclamationCircleOutlined),
    content: h('div', { style: 'color: red;' }, '您确定删除当前优惠券吗？'),
    onOk() {
      deleteCouponById(id).then(() => {
        message.success('删除成功');
        getData();
      });
    }
  });
};
</script>
