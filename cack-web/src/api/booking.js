import request from '../utils/request';

export function listBooking(params) {
  return request({
    url: '/booking',
    method: 'GET',
    params: { ...params }
  });
}

export function getBookingById(id) {
  return request({
    url: `/booking/${id}`,
    method: 'GET'
  });
}

export function updateStatusById(params) {
  return request({
    url: `/booking/${params.id}/status`,
    method: 'PUT',
    params: {
      status: params.status
    }
  });
}

export function updateCancelReasonById(params) {
  return request({
    url: `/booking/${params.id}/cancel/reason`,
    method: 'PUT',
    params: {
      cancelReason: params.cancelReason
    }
  });
}
