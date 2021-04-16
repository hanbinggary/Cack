<template>
  <a-spin :spinning="loading">
    <div class="table-btn-group">
      <a-button type="primary" @click.prevent="onDeleteMulti">
        删除{{ selectedRowKeys.length }}条
      </a-button>
    </div>
    <a-table
      :data-source="dataSource"
      :columns="columns"
      :row-key="record => record.id"
      :pagination="pagination"
      :row-selection="{ onChange: onSelectChange }"
      @change="onChange"
    >
      <template #status="{ record }">
        <a-switch
          v-model:checked="record.status"
          @click="onClickStatus(record)"
          :disabled="record.status === false"
        />
      </template>
      <template #timeInterval="{ record }">
        <span>{{ record.startTime }}:00 ~ {{ record.endTime }}:00</span>
      </template>
      <template #total="{ record }">
        <a-badge v-if="record.total <= 10" status="processing" />
        <a-badge v-else-if="record.total <= 30" status="warning" />
        <a-badge v-else status="error" />
        <span>{{ record.total }}</span>
      </template>
      <template #operation="{ record }">
        <a @click.prevent="onShowDetail(record.id)">查看</a>
        <a-divider type="vertical" />
        <a @click.prevent="onShowDelete(record.id)">删除</a>
      </template>
    </a-table>
  </a-spin>
  <!-- 查看 -->
  <base-detail
    :visible="detailModalVisible"
    :key="currentDetailKey"
    :id="currentDetailId"
    @onChangeVisible="onChangeDetailVisible"
  />
  <!-- 删除原因 -->
  <base-delete
    :visible="deleteModalVisible"
    :id="currentDeleteId"
    @onChangeVisible="onChangeDeleteVisible"
  />
  <!-- 更新状态 -->
  <base-update-status
    :visible="updateStatusModalVisible"
    :id="updateStatusId"
    @onCancel="onCancelUpdateStatus"
  />
  <!-- 删除多条记录 -->
  <base-delete-multi
    :visible="deleteMultiModalVisible"
    :ids="selectedRowKeys"
    @onCancelVisible="onCancelDeleteMultiVisible"
    @onChangeSelectedKeys="onChangeSelectedKeys"
  />
</template>

<script setup>
import {
  ref,
  onBeforeMount,
  defineProps,
  inject,
  defineEmit,
  toRaw
} from 'vue';
import { message } from 'ant-design-vue';
import BaseDetail from './BaseDetail.vue';
import BaseDelete from './BaseDelete.vue';
import BaseUpdateStatus from './BaseUpdateStatus.vue';
import BaseDeleteMulti from './BaseDeleteMulti.vue';
import { updateStatusById } from '../../../api/bookingTime';

const { date } = defineProps({
  date: Object,
  dataSource: Array,
  loading: Boolean,
  pagination: Object
});

const emits = defineEmit(['onChangeTable']);
const getData = inject('getData');

const columns = [
  {
    title: '#',
    dataIndex: 'id',
    key: 'id',
    width: 60,
    align: 'center'
  },
  {
    title: '日期',
    dataIndex: 'date',
    key: 'date'
  },
  {
    title: '时段',
    dataIndex: 'timeInterval',
    slots: {
      customRender: 'timeInterval'
    }
  },
  {
    title: '是否有效',
    dataIndex: 'status',
    key: 'status',
    slots: {
      customRender: 'status'
    },
    filters: [
      {
        text: '有效',
        value: true
      },
      {
        text: '无效',
        value: false
      }
    ]
  },
  {
    title: '预约人数',
    dataIndex: 'total',
    key: 'total',
    slots: {
      customRender: 'total'
    },
    sorter: true
  },
  {
    title: '操作',
    slots: {
      customRender: 'operation'
    }
  }
];
// 查看
const detailModalVisible = ref(false);
const currentDetailId = ref(0);
const currentDetailKey = ref(Math.random());
// 删除
const deleteModalVisible = ref(false);
const currentDeleteId = ref(0);
// 切换状态
const updateStatusModalVisible = ref(false);
const updateStatusId = ref(0);
// 多选
const selectedRowKeys = ref([]);
const deleteMultiModalVisible = ref(false);

const onChange = (pagination, filters, sorter) => {
  emits('onChangeTable', pagination, filters, sorter);
};

const onShowDetail = id => {
  currentDetailId.value = id;
  currentDetailKey.value = Math.random();
  onChangeDetailVisible(true);
};

const onChangeDetailVisible = visible => {
  detailModalVisible.value = visible;
};

// 删除
const onShowDelete = id => {
  currentDeleteId.value = id;
  onChangeDeleteVisible(true);
};

const onChangeDeleteVisible = visible => {
  deleteModalVisible.value = visible;
};

// 点击状态切换
const onClickStatus = record => {
  console.log('record: ', record);
  if (!record.status) {
    updateStatusModalVisible.value = true;
    updateStatusId.value = record.id;
  }
  //  else {
  //   updateStatusById({ id: record.id, status: record.status }).then(result => {
  //     message.success('状态更新成功');
  //     getData();
  //   });
  // }
};

const onCancelUpdateStatus = () => {
  updateStatusModalVisible.value = false;
  getData();
};

// 行多选
const onSelectChange = selectedKeys => {
  selectedRowKeys.value = selectedKeys;
};

// 删除后，恢复选择keys为空数组
const onChangeSelectedKeys = () => {
  selectedRowKeys.value = [];
};

// 点击删除多条记录按钮
const onDeleteMulti = () => {
  deleteMultiModalVisible.value = true;
  console.log('selectedRowKeys: ', toRaw(selectedRowKeys.value));
};

const onCancelDeleteMultiVisible = () => {
  deleteMultiModalVisible.value = false;
};
</script>

<style scoped>
.table-btn-group {
  margin-bottom: 12px;
}
</style>
