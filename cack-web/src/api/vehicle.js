import request from '../utils/request';

export function listVehicle(params) {
  return request({
    url: '/vehicle',
    method: 'GET',
    params: { ...params }
  });
}

export function getVehicleById(id) {
  return request({
    url: `/vehicle/${id}`,
    method: 'GET'
  });
}
