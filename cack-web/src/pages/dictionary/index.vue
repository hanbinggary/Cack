<template>
  <div class="dictionary container">
    <header>
      <p>数字字典</p>
    </header>
    <main>
      <a-tabs>
        <a-tab-pane key="type" tab="车辆类型">
          <base-type />
        </a-tab-pane>
        <a-tab-pane key="useCharacter" tab="车辆使用性质">
          <base-use-character />
        </a-tab-pane>
      </a-tabs>
    </main>
  </div>
</template>

<script setup>
import {} from 'vue';
import BaseType from './components/BaseType.vue';
import BaseUseCharacter from './components/BaseUseCharacter.vue';
</script>

<style scoped>
main {
  min-width: 720px;
  padding: 24px;
  border-bottom-right-radius: 16px;
  background: #fff;
}
</style>
