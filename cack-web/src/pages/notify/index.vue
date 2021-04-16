<template>
  <div class="notify container">
    <header>
      <p>消息通知</p>
    </header>
    <main>
      <base-content v-if="tag === 'content'" @onChangeTag="onChangeTag" />
      <readed-list :id="currentReadedId" v-else />
    </main>
  </div>
</template>

<script setup>
import BaseContent from './BaseContent.vue';
import ReadedList from './ReadedList.vue';

import { provide, ref } from 'vue';

const tag = ref('content');
const currentReadedId = ref(0);

const onChangeReadedId = id => {
  currentReadedId.value = id;
};

const onChangeTag = tagName => {
  tag.value = tagName;
};

provide('onChangeReadedId', onChangeReadedId);
provide('onChangeTag', onChangeTag);
</script>

<style scoped>
main {
  min-width: 1020px;
  padding: 24px;
  border-bottom-right-radius: 16px;
  background: #fff;
}

:deep(.ant-input),
:deep(.ant-btn) {
  border-radius: 4px;
}
</style>
