<template>
  <div id="month"></div>
</template>

<script setup>
import { Chart } from '@antv/g2';
import { onMounted } from 'vue';
import { statisticsMonth } from '../../../api/statistics';

onMounted(() => {
  statisticsMonth().then(result => {
    const chart = new Chart({
      container: 'month',
      autoFit: true,
      height: 370
    });

    chart.data(result);
    chart.scale({
      label: [0, 1],
      total: {
        nice: true
      }
    });

    chart.tooltip({
      showCrosshairs: true,
      shared: true
    });

    chart.axis('date', {
      label: {
        style: {
          fill: '#b9bdc6',
          fontSize: 13
        }
      }
    });

    chart.axis('total', {
      label: {
        style: {
          fill: '#b6b0c3',
          fontSize: 13
        }
      }
    });

    chart.legend('name', {
      position: 'top-left'
    });

    chart
      .line()
      .position('date*total')
      .color('name', ['#1890ff', '#00D8A6'])
      .style({
        lineWidth: 3
      })
      .shape('smooth');
    chart.point().position('date*total').color('name').shape('circle');

    chart.render();
  });
});
</script>

<style scoped>
#month {
  padding: 16px 0;
}
</style>
