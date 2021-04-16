<template>
  <a-modal
    :visible="visible"
    title="分配角色"
    :key="key"
    @cancel="$emit('onCancel')"
    @ok="onOk"
  >
    <a-form>
      <a-form-item label="分配角色">
        <a-select v-model:value="selectRoleId">
          <a-select-option
            v-for="role in roles"
            :key="role.id"
            :value="role.id"
          >
            {{ role.name }}
          </a-select-option>
        </a-select>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script setup>
import { message } from 'ant-design-vue';
import {
  defineEmit,
  defineProps,
  inject,
  onBeforeMount,
  reactive,
  ref,
  toRaw
} from 'vue';
import store from '../../../store';
import { updateRoleIdById } from '../../../api/admin';

const props = defineProps({
  visible: Boolean,
  key: Number,
  id: Number,
  roleId: Number,
  roles: Array
});
const getData = inject('getData');
const emits = defineEmit(['onCancel']);
const selectRoleId = ref(props.roleId);

const onOk = () => {
  updateRoleIdById({
    id: props.id,
    roleId: selectRoleId.value
  }).then(() => {
    message.success('成功更新用户角色');
    emits('onCancel');
    getData();
    if (store.getters.id === props.id) {
      const role = props.roles.filter(
        role => role.id === selectRoleId.value
      )[0];
      store.dispatch('updateRoleName', role.name);
    }
  });
};
</script>
