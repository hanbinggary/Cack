const getters = {
  id: state => state.user.id,
  username: state => state.user.username,
  nickname: state => state.user.nickname,
  passwordLevel: state => state.user.passwordLevel,
  roleName: state => state.user.roleName
};

export default getters;
