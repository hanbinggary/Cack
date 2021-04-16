// 获取、设置、移除 token
import Cookie from 'js-cookie';

const KEY = 'token';
const TOKEN_HEADER = 'Bearer ';

export function setToken(token) {
  Cookie.set(KEY, TOKEN_HEADER + token);
}

export function getToken() {
  return Cookie.get(KEY);
}

export function removeToken() {
  Cookie.remove(KEY);
}
