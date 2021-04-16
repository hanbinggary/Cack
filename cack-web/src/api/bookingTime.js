import request from '../utils/request';

export function listBookingTime(params) {
  return request({
    url: '/booking/time',
    method: 'GET',
    params: {
      ...params
    }
  });
}

export function getBookingTimeById(id) {
  return request({
    url: `/booking/time/${id}`,
    method: 'GET'
  });
}

export function insertBookingTimes(params) {
  return request({
    url: '/booking/time',
    method: 'POST',
    data: {
      ...params
    }
  });
}

export function updateStatusById(params) {
  return request({
    url: `/booking/time/${params.id}/status`,
    method: 'PUT',
    params: {
      status: params.status,
      invalidReason: params.invalidReason
    }
  });
}

export function deleteBookingTimeById(params) {
  return request({
    url: `/booking/time/${params.id}`,
    method: 'DELETE',
    params: {
      invalidReason: params.invalidReason
    }
  });
}

export function deleteBatchBookingTimeByIds(params) {
  return request({
    url: '/booking/time/batch',
    method: 'DELETE',
    data: { ...params }
  });
}
