<template>
  <div id="gender"></div>
</template>

<script setup>
import { Chart } from '@antv/g2';
import { onMounted } from 'vue';
import { statisticsGender } from '../../../api/statistics';

onMounted(() => {
  statisticsGender().then(data => {
    const genders = [];
    let genderTotal = 0;
    data.map(item => {
      genderTotal += item.total;
      if (item.type === 0) {
        item.type = '未知';
      } else if (item.type === 1) {
        item.type = '男性';
      } else {
        item.type = '女性';
      }
    });
    // 绘制
    const chart = new Chart({
      container: 'gender',
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
        return ((val / genderTotal) * 100).toFixed(2) + '%';
      }
    });

    chart
      .interval()
      .position('total')
      .color('type', type => {
        if (type === '女性') return '#ff707d';
        else if (type === '男性') return '#016eff';
        else return '#e2eefe';
      })
      .adjust('stack');

    chart.interaction('element-active');
    chart.render();
  });
});
</script>
