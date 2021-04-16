import request from '../utils/request';

export function listVehicleUseCharacter() {
  return request({
    url: '/vehicle/use/character',
    method: 'GET'
  });
}

export function insertVehicleUseCharacter(name) {
  return request({
    url: '/vehicle/use/character',
    method: 'POST',
    data: { name }
  });
}

export function deleteVehicleUseCharacter(id) {
  return request({
    url: `/vehicle/use/character/${id}`,
    method: 'DELETE'
  });
}
