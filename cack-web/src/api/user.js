import request from '../utils/request';

export function listUser(params) {
  return request({
    url: '/user',
    method: 'GET',
    params: { ...params }
  });
}

export function getUserById(id) {
  return request({
    url: `/user/${id}`,
    method: 'GET'
  });
}

export function updateStatusById(params) {
  return request({
    url: `/user/${params.id}`,
    method: 'PUT',
    params: {
      status: params.status
    }
  });
}
