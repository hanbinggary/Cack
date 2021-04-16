import request from '../utils/request';

export function listUserNotify(params) {
  return request({
    url: `/user/notify/${params.id}`,
    method: 'GET',
    params: {
      pageNum: params.pageNum,
      pageSize: params.pageSize
    }
  });
}
