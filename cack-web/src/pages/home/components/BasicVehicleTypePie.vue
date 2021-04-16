<template>
  <div id="vehicle-type"></div>
</template>

<script setup>
import { Chart } from '@antv/g2';
import { onMounted } from 'vue';
import { statisticsVehicleType } from '../../../api/statistics';

onMounted(() => {
  statisticsVehicleType().then(data => {
    let typeTotal = data.reduce((old, current) => old + current.total, 0);
    // 绘制
    const chart = new Chart({
      container: 'vehicle-type',
      autoFit: true,
      height: 200
    });

    chart.data(data);
    chart.coordinate('theta', {
      radius: 0.8,
      innerRadius: 0.7
    });
    chart.tooltip({
      showTitle: false,
      showMarkers: false
    });

    chart.scale('total', {
      formatter: val => {
        return ((val / typeTotal) * 100).toFixed(2) + '%';
      }
    });

    chart.interval().position('total').color('vehicleType').adjust('stack');

    chart.interaction('element-active');
    chart.render();
  });
});
</script>
