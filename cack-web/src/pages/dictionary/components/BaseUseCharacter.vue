<template>
  <a-spin tip="正在加载..." :spinning="loading">
    <div class="container">
      <div class="add-wrapper">
        <a-input
          v-model:value="useCharacter"
          placeholder="车辆使用性质"
          maxlength="20"
          @pressEnter="onPressEnter"
        />
        <a-button type="primary" @click="onAdd">添加</a-button>
      </div>
      <div class="useCharacter-list">
        <div
          class="useCharacter-item"
          v-for="useCharacter in useCharacters"
          :key="useCharacter.id"
        >
          <span>{{ useCharacter.name }}</span>
          <component
            :is="$antIcons['DeleteOutlined']"
            class="useCharacter-item-icon"
            @click="onDelete(useCharacter.id)"
          ></component>
        </div>
        <div v-if="useCharactersIsEmpty" class="empty">暂无车辆使用性质</div>
      </div>
    </div>
  </a-spin>
</template>

<script setup>
import { onBeforeMount, ref, createVNode, computed } from 'vue';
import { ExclamationCircleOutlined } from '@ant-design/icons-vue';
import { message, Modal } from 'ant-design-vue';
import {
  listVehicleUseCharacter,
  insertVehicleUseCharacter,
  deleteVehicleUseCharacter
} from '../../../api/vehicleUseCharacter';

const useCharacters = ref([]);
const loading = ref(false);
const useCharacter = ref();

const useCharactersIsEmpty = computed(() =>
  useCharacters.value.length > 0 ? false : true
);

const getData = () => {
  loading.value = true;
  listVehicleUseCharacter().then(result => {
    if (result) {
      useCharacters.value = result;
      loading.value = false;
    }
  });
};

onBeforeMount(() => {
  getData();
});

const onAdd = () => {
  insertVehicleUseCharacter(useCharacter.value).then(result => {
    if (result) {
      message.success('成功添加使用性质');
      getData();
      useCharacter.value = '';
    }
  });
};

const onPressEnter = () => {
  onAdd();
};

const onDelete = id => {
  Modal.confirm({
    title: '删除使用性质',
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      'div',
      { style: 'color:red;' },
      '确认删除该使用性质吗？'
    ),
    onOk() {
      deleteVehicleUseCharacter(id).then(result => {
        if (result) {
          message.success('成功删除使用性质');
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

.useCharacter-list {
  width: 320px;
  padding: 16px 0;
  max-height: 420px;
  overflow: scroll;
}

.useCharacter-item {
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

.useCharacter-item :deep(.anticon) {
  color: #d9d9d9;
}

.useCharacter-item:hover :deep(.anticon) {
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
