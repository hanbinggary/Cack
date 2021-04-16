<template>
  <a-spin tip="加载中..." :spinning="loading">
    <a-table
      :columns="columns"
      :pagination="pagination"
      :dataSource="notifys"
      :rowKey="record => record.id"
      @change="onChange"
    >
      <template #type="{ record }">
        <span v-if="record.type === 0">系统</span>
        <span v-else>预约</span>
      </template>
      <template #content="{ text }">
        <span class="notify-content">{{ text }}</span>
      </template>
      <template #operation="{ record }">
        <a @click.prevent="onDelete(record.id)">删除</a>
        <a-divider type="vertical" />
        <a @click.prevent="onClickReaded(record.id)">读取情况</a>
      </template>
      <template #expandedRowRender="{ record }">
        <span>
          <span class="all-content-text">内容：</span>
          {{ record.content }}
        </span>
      </template>
    </a-table>
  </a-spin>
</template>

<script setup>
import { createVNode, defineEmit, defineProps, h, inject, reactive } from 'vue';
import { message, Modal } from 'ant-design-vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { deleteNotifyById } from '../../../api/notify';

defineProps({
  loading: Boolean,
  notifys: Array,
  pagination: Object
});

const emits = defineEmit(['onChange']);

const columns = [
  {
    title: '#',
    dataIndex: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '标题',
    dataIndex: 'title'
  },
  {
    title: '内容',
    dataIndex: 'content',
    width: 300,
    slots: {
      customRender: 'content'
    }
  },
  {
    title: '消息类型',
    dataIndex: 'type',
    slots: {
      customRender: 'type'
    }
  },
  {
    title: '发布者用户名',
    dataIndex: 'username'
  },
  {
    title: '发布者昵称',
    dataIndex: 'nickname'
  },
  {
    title: '发布时间',
    dataIndex: 'gmtCreate',
    sorter: true
  },
  {
    title: '操作',
    slots: {
      customRender: 'operation'
    }
  }
];

const getData = inject('getData');
const onChangeTag = inject('onChangeTag');
const onChangeReadedId = inject('onChangeReadedId');

const onClickReaded = id => {
  onChangeTag('readed');
  onChangeReadedId(id);
};
const onChange = (paging, filters, sorter) => {
  emits('onChange', paging, sorter);
};

const onDelete = id => {
  Modal.confirm({
    title: '删除消息',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      {
        style: 'color:red;'
      },
      '您确定删除当前消息吗？'
    ),

    onOk() {
      deleteNotifyById(id).then(() => {
        message.success('删除成功');
        getData();
      });
    }
  });
};
</script>

<style scoped>
.notify-content {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  display: inline-block;
  width: 300px;
}

.all-content-text {
  font-weight: bold;
}
</style>
