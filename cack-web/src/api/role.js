import request from '../utils/request';

export function listRole() {
  return request({
    url: '/role',
    method: 'GET'
  });
}

export function updateDescById(params) {
  return request({
    url: `/role/${params.id}/desc`,
    method: 'PUT',
    params: {
      desc: params.desc
    }
  });
}
