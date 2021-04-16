import request from '../utils/request';

export function listNotify(params) {
  return request({
    url: '/notify',
    method: 'GET',
    params: { ...params }
  });
}

export function insertNotify(params) {
  return request({
    url: '/notify',
    method: 'POST',
    data: { ...params }
  });
}

export function deleteNotifyById(id) {
  return request({
    url: `/notify/${id}`,
    method: 'DELETE'
  });
}
