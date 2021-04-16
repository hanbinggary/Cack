import request from '../utils/request';

export function listCoupon(params) {
  return request({
    url: '/coupon',
    method: 'GET',
    params: { ...params }
  });
}

export function insertCoupon(params) {
  return request({
    url: '/coupon',
    method: 'POST',
    data: { ...params }
  });
}

export function updateStatusById(params) {
  return request({
    url: `/coupon/${params.id}/status`,
    method: 'PUT',
    params: {
      status: params.status
    }
  });
}

export function deleteCouponById(id) {
  return request({
    url: `/coupon/${id}`,
    method: 'DELETE'
  });
}
