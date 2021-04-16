<template>
  <a-spin tip="正在加载..." :spinning="loading">
    <div class="container">
      <div class="add-wrapper">
        <a-input
          v-model:value="type"
          placeholder="车辆类型"
          maxlength="20"
          @pressEnter="onPressEnter"
        />
        <a-button type="primary" @click="onAdd">添加</a-button>
      </div>
      <div class="type-list">
        <div class="type-item" v-for="type in types" :key="type.id">
          <span>{{ type.name }}</span>
          <component
            :is="$antIcons['DeleteOutlined']"
            class="type-item-icon"
            @click="onDelete(type.id)"
          ></component>
        </div>
        <div v-if="typesIsEmpty" class="empty">暂无车辆类型</div>
      </div>
    </div>
  </a-spin>
</template>

<script setup>
import { onBeforeMount, ref, createVNode, computed } from 'vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { message, Modal } from 'ant-design-vue';
import {
  listVehicleType,
  insertVehicleType,
  deleteVehicleType
} from '../../../api/vehicleType';

const types = ref([]);
const loading = ref(false);
const type = ref();

const typesIsEmpty = computed(() => (types.value.length > 0 ? false : true));

const getData = () => {
  loading.value = true;
  listVehicleType().then(result => {
    if (result) {
      types.value = result;
      loading.value = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

const onAdd = () => {
  insertVehicleType(type.value).then(result => {
    if (result) {
      console.log('result: ', result);
      message.success('成功添加类型');
      getData();
      type.value = '';
    }
  });
};

const onPressEnter = () => {
  onAdd();
};

const onDelete = id => {
  Modal.confirm({
    title: '删除类型',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode('div', { style: 'color:red;' }, '确认删除该类型吗？'),
    onOk() {
      deleteVehicleType(id).then(result => {
        if (result) {
          message.success('成功删除类型');
          getData();
        }
      });
    }
  });
};
</script>

<style scoped>
.container {
  min-height: 320px;
}
.add-wrapper > input {
  width: 220px;
  border-right: 0;
}

.add-wrapper > button {
  margin-left: -1px;
}

.type-list {
  width: 320px;
  padding: 16px 0;
  max-height: 420px;
  overflow: scroll;
}

.type-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 280px;
  margin-top: 12px;
  padding: 6px 12px;
  background: rgb(221, 254, 239);
  border: 1px solid transparent;
  border-radius: 4px;
  cursor: pointer;
}

.type-item :deep(.anticon) {
  color: #d9d9d9;
}

.type-item:hover :deep(.anticon) {
  color: red;
}

.empty {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 280px;
  height: 100px;
  background: #eee;
  border-radius: 4px;
  color: rgb(140, 140, 140);
}
</style>
