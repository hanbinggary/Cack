import { login, logout } from '../../api/login';
import { setToken, removeToken } from '../../utils/auth';

const user = {
  state: {
    id: 0,
    username: '',
    nickname: '',
    passwordLevel: -1,
    roleName: ''
  },
  mutations: {
    SET_ID: (state, id) => (state.id = id),
    SET_USERNAME: (state, username) => (state.username = username),
    SET_NICKNAME: (state, nickname) => (state.nickname = nickname),
    SET_PASSWORD_LEVEL: (state, passwordLevel) =>
      (state.passwordLevel = passwordLevel),
    SET_ROLE_NAME: (state, roleName) => (state.roleName = roleName)
  },
  actions: {
    // 登录
    Login({ commit }, info) {
      return new Promise(resolve => {
        login(info).then(result => {
          if (result) {
            const {
              id,
              nickname,
              passwordLevel,
              roleName,
              token,
              username
            } = result;
            // 将 token 存储到 cookie内
            setToken(token);
            // 将用户信息持久存储到 vuex 中
            commit('SET_ID', id);
            commit('SET_USERNAME', username);
            commit('SET_NICKNAME', nickname);
            commit('SET_PASSWORD_LEVEL', passwordLevel);
            commit('SET_ROLE_NAME', roleName);
            resolve(result);
          }
        });
      });
    },
    // 注销
    Logout({ commit }) {
      return new Promise(resolve => {
        logout().then(() => {
          removeToken();
          commit('SET_ID', 0);
          commit('SET_USERNAME', '');
          commit('SET_NICKNAME', '');
          commit('SET_PASSWORD_LEVEL', -1);
          commit('SET_ROLE_NAME', '');
          resolve();
        });
      });
    },
    // 更新角色名
    updateRoleName({ commit }, roleName) {
      return new Promise(resolve => {
        commit('SET_ROLE_NAME', roleName);
        resolve();
      });
    },
    // 更新基本信息
    updateNickname({ commit }, nickname) {
      return new Promise(resolve => {
        commit('SET_NICKNAME', nickname);
        resolve();
      });
    },
    // 更新密码强度
    updatePasswordLevel({ commit }, passwordLevel) {
      return new Promise(resolve => {
        commit('SET_PASSWORD_LEVEL', passwordLevel);
        resolve();
      });
    }
  }
};

export default user;
