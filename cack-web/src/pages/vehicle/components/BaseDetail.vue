<template>
  <a-modal
    :visible="visible"
    :key="key"
    title="详情"
    @cancel="onCancel"
    :footer="null"
  >
    <a-skeleton :loading="loading" :paragraph="{ rows: 8 }">
      <div class="content">
        <div class="vehicle">
          <div class="vehicle-item">
            <span class="vehicle-item-label">车牌号:</span>
            <span class="vehicle-item-text">{{ vehicle.plateNo }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">车辆类型:</span>
            <span class="vehicle-item-text">{{ vehicle.vehicleType }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">使用性质:</span>
            <span class="vehicle-item-text">{{ vehicle.useCharacter }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">车辆识别代码:</span>
            <span class="vehicle-item-text">{{ vehicle.vin }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">发动机号码:</span>
            <span class="vehicle-item-text">{{ vehicle.engineNo }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">总里程数:</span>
            <span class="vehicle-item-text">{{ vehicle.odometer }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">车辆注册日期:</span>
            <span class="vehicle-item-text">{{ vehicle.registerDate }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">用户昵称:</span>
            <span class="vehicle-item-text">{{ vehicle.nickname }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">用户手机号:</span>
            <span class="vehicle-item-text">{{ vehicle.tel }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">创建日期:</span>
            <span class="vehicle-item-text">{{ vehicle.gmtCreate }}</span>
          </div>
          <div class="vehicle-item">
            <span class="vehicle-item-label">更新日期:</span>
            <span class="vehicle-item-text">{{ vehicle.gmtModified }}</span>
          </div>
          <a-button class="vehicle-close-btn" @click="onCancel">关闭</a-button>
        </div>
      </div>
    </a-skeleton>
  </a-modal>
</template>

<script setup>
import { defineEmit, defineProps, onMounted, ref } from 'vue';
import { getVehicleById } from '../../../api/vehicle';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number
});

const emits = defineEmit(['onCancel']);
const vehicle = ref({});
const loading = ref(false);

onMounted(() => {
  loading.value = true;
  getVehicleById(props.id).then(result => {
    vehicle.value = result;
    loading.value = false;
  });
});

const onCancel = () => {
  emits('onCancel');
};
</script>

<style scoped>
.vehicle {
  width: 358px;
  margin: 0 auto;
}

.vehicle-item {
  padding-bottom: 20px;
}

.vehicle-item-label {
  display: inline-block;
  width: 92px;
  margin-right: 16px;
  color: rgba(0, 0, 0, 0.65);
  text-align: right;
  vertical-align: top;
}

.vehicle-item-label + span {
  display: inline-block;
  width: 250px;
}

:deep(.ant-btn) {
  border-radius: 4px;
}

.vehicle-close-btn {
  width: 100%;
  margin-top: 24px;
  margin-bottom: 16px;
}
</style>
