<template>
  <div class="container">
    <div class="layout">
      <BasicSideBar />
      <div class="layout-main">
        <BasicHeader />
        <BasicContent />
      </div>
    </div>
  </div>
</template>

<script setup>
import BasicSideBar from './BasicSideBar.vue';
import BasicHeader from './BasicHeader.vue';
import BasicContent from './BasicContent.vue';
</script>

<style scoped>
.container {
  box-sizing: border-box;
  width: 100%;
  height: 100%;
  padding: 16px;
  background: #edf1f4;
}

.layout {
  position: relative;
  display: flex;
  flex-direction: row;
  grid-row: 256px auto;
  width: 100%;
  height: 100%;
  border-radius: 16px;
  background: #fff;
}

.layout-main {
  flex: auto;
  display: flex;
  flex-direction: column;
  overflow: hidden;
}
</style>
