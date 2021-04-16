import { createStore } from 'vuex';
import createPersistedState from 'vuex-persistedstate';
import user from './modules/user';
import getters from './getters';

const store = createStore({
  modules: {
    user
  },
  getters,
  plugins: [createPersistedState()]
});

export default store;
