import request from '../utils/request';

export function listVehicleType() {
  return request({
    url: '/vehicle/type',
    method: 'GET'
  });
}

export function insertVehicleType(name) {
  return request({
    url: '/vehicle/type',
    method: 'POST',
    data: { name }
  });
}

export function deleteVehicleType(id) {
  return request({
    url: `/vehicle/type/${id}`,
    method: 'DELETE'
  });
}
