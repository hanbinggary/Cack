<template>
  <div class="setting container">
    <header>
      <p>个人设置</p>
    </header>
    <main>
      <a-tabs tabPosition="left" v-model:activeKey="activeKey">
        <a-tab-pane key="basic" tab="基本设置">
          <basic />
        </a-tab-pane>
        <a-tab-pane key="station" tab="验车站设置">
          <base-station />
        </a-tab-pane>
        <a-tab-pane key="security" tab="安全设置">
          <base-security />
        </a-tab-pane>
      </a-tabs>
    </main>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import Basic from './components/Basic.vue';
import BaseStation from './components/BaseStation.vue';
import BaseSecurity from './components/BaseSecurity.vue';

const state = reactive({
  activeKey: 'basic'
});
</script>

<style scoped>
main {
  min-width: 720px;
  padding: 24px 0;
  border-bottom-right-radius: 16px;
  background: #fff;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}

:deep(.ant-tabs .ant-tabs-left-bar .ant-tabs-tab) {
  padding-left: 24px;
  padding-right: 96px;
  text-align: left;
}

:deep(.ant-tabs-nav .ant-tabs-tab-active) {
  background: #e6f7ff;
}
</style>
