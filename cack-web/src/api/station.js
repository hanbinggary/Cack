import request from '../utils/request';

export function getStation() {
  return request({
    url: '/station',
    method: 'GET'
  });
}

export function updateStation(params) {
  return request({
    url: '/station',
    method: 'PUT',
    data: { ...params }
  });
}
