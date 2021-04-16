<template>
  <main class="layout-content">
    <div class="container">
      <router-view />
    </div>
  </main>
</template>

<style scoped>
.layout-content {
  flex: auto;
  display: flex;
  padding-right: 16px;
  padding-bottom: 16px;
  overflow: scroll;
}

.container {
  flex: auto;
  padding: 16px;
  border-bottom-right-radius: 16px;
  background: #f0f7fd;
  overflow: scroll;
}
</style>
