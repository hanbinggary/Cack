<template>
  <div class="home container">
    <header>
      <p>Cack 数据分析</p>
    </header>
    <main class="main">
      <section class="statistics">
        <div class="statistics-total">
          <div class="statistics-total-item">
            <Skeleton :paragraph="{ rows: 1 }" :loading="loading">
              <div class="total-item-thumb total-item-clock-circle-icon">
                <ClockCircleFilled />
              </div>
              <div class="total-item-desc">
                <span class="total-item-label">总预约数</span>
                <span class="total-item-num">{{ bookingTotal }}</span>
              </div>
            </Skeleton>
          </div>
          <div class="statistics-total-item">
            <Skeleton :paragraph="{ rows: 1 }" :loading="loading">
              <div class="total-item-thumb total-item-car-icon">
                <CarFilled />
              </div>
              <div class="total-item-desc">
                <span class="total-item-label">总车辆数</span>
                <span class="total-item-num">{{ vehicleTotal }}</span>
              </div>
            </Skeleton>
          </div>
          <div class="statistics-total-item">
            <Skeleton :paragraph="{ rows: 1 }" :loading="loading">
              <div class="total-item-thumb total-item-user-icon">
                <img
                  src="../../assets/my_fill.svg"
                  alt="my-fill"
                  width="28"
                  height="28"
                />
              </div>
              <div class="total-item-desc">
                <span class="total-item-label">总用户数</span>
                <span class="total-item-num">{{ userTotal }}</span>
              </div>
            </Skeleton>
          </div>
        </div>
        <div class="statistics-chart">
          <div class="statistics-chart-header">
            <p>用户预约统计</p>
            <div class="statistics-chart-optional">
              <span
                :class="selectedChart === 'Weekly' ? 'selected-chart' : ''"
                @click="onClickChartToggle('Weekly')"
                >Weekly</span
              >
              <span
                @click="onClickChartToggle('Month')"
                :class="selectedChart === 'Month' ? 'selected-chart' : ''"
                >Month</span
              >
            </div>
          </div>
          <template v-if="selectedChart === 'Weekly'">
            <BasicWeekly />
          </template>
          <template v-else>
            <BasicMonth />
          </template>
        </div>
      </section>
      <section class="pie-chart">
        <div class="user-gender pie-chart-item">
          <p>用户性别</p>
          <div class="user-gender-pie-chart">
            <BasicGenderPie />
          </div>
        </div>
        <div class="user-city pie-chart-item">
          <p>车辆类型</p>
          <div class="user-gender-pie-chart">
            <BasicVehicleTypePie />
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<script setup>
import { onBeforeMount, onMounted, ref, toRaw } from 'vue';
import moment from 'moment';
import BasicWeekly from './components/BasicWeekly.vue';
import BasicMonth from './components/BaiscMonth.vue';
import BasicGenderPie from './components/BasicGenderPie.vue';
import BasicVehicleTypePie from './components/BasicVehicleTypePie.vue';
import { total } from '../../api/statistics';
import { Skeleton } from 'ant-design-vue';

const selectedChart = ref('Weekly');
const bookingTotal = ref(0);
const vehicleTotal = ref(0);
const userTotal = ref(0);
const loading = ref(false);

onBeforeMount(() => {
  loading.value = true;
  // 获取总预约数、总车辆数和总用户数
  total().then(result => {
    bookingTotal.value = result.bookingTotal;
    vehicleTotal.value = result.vehicleTotal;
    userTotal.value = result.userTotal;
    loading.value = false;
  });
});

// 点击图表切换按钮
const onClickChartToggle = option => {
  selectedChart.value = option;
};
</script>

<style scoped>
.main {
  display: flex;
  flex-direction: row;
}

.statistics {
  flex: auto;
  display: flex;
  flex-direction: column;
  max-width: 820px;
}

.statistics-total {
  display: flex;
  justify-content: space-between;
}

.statistics-total-item {
  display: flex;
  align-items: center;
  min-width: 246px;
  padding: 18px 24px;
  border-radius: 16px;
  background: #fff;
}

.total-item-thumb {
  padding: 18px;
  margin-right: 28px;
  border-radius: 16px;
}

.total-item-thumb :deep(.anticon) {
  font-size: 28px;
}

.total-item-desc {
  padding-right: 38px;
}

.total-item-label {
  display: block;
  font-size: 15px;
  color: #a5aab7;
}

.total-item-num {
  font-size: 24px;
  font-weight: bold;
  color: #121f3e;
  font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
}

.total-item-car-icon {
  background: #fef6ee;
}

.total-item-car-icon :deep(.anticon) {
  color: #ffa53c;
}

.total-item-clock-circle-icon {
  background: #f0fbf8;
}

.total-item-clock-circle-icon :deep(.anticon) {
  color: #3bcfad;
}

.total-item-user-icon {
  background: #e5f1ff;
}

/* 统计用户、预约 */
.statistics-chart {
  margin-top: 16px;
  padding: 16px 24px;
  border-radius: 16px;
  background: #fff;
}

.statistics-chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.statistics-chart-header > p {
  font-size: 18px;
  font-weight: bold;
}

.statistics-chart-optional {
  padding: 14px 10px;
  border-radius: 10px;
  background: #e5f1ff;
}

.statistics-chart-optional > span {
  padding: 10px 24px;
  font-size: 16px;
  font-weight: bold;
  cursor: pointer;
  border-radius: 8px;
  transition: background-color 200ms ease;
}

.statistics-chart-optional > span:first-child {
  margin-right: 8px;
}

.statistics-chart-optional > span:hover {
  background: #fff;
}

.selected-chart {
  border-radius: 8px;
  background: #fff;
}

.pie-chart {
  flex: auto;
  display: flex;
  flex-direction: column;
  margin-left: 24px;
}

.pie-chart-item {
  flex: auto;
  min-width: 240px;
  padding: 24px;
  border-radius: 16px;
  background: #fff;
}

.pie-chart-item > p {
  font-size: 18px;
  font-weight: bold;
}

.user-gender {
  margin-bottom: 16px;
}
</style>
