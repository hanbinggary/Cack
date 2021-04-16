// 通用工具类封装

export const capitalize = str => {
  if (typeof str !== 'string') return str;
  return str.slice(0, 1).toUpperCase() + str.slice(1).toLowerCase();
};
