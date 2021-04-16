import request from '../utils/request';

export function total() {
  return request({
    url: '/statistics/total',
    method: 'GET'
  });
}

export function statisticsGender() {
  return request({
    url: '/statistics/gender',
    method: 'GET'
  });
}

export function statisticsVehicleType() {
  return request({
    url: '/statistics/vehicle/type',
    method: 'GET'
  });
}

export function statisticsWeekly() {
  return request({
    url: '/statistics/weekly',
    method: 'GET'
  });
}

export function statisticsMonth() {
  return request({
    url: '/statistics/month',
    method: 'GET'
  });
}
