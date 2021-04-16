import request from '../utils/request';

export function listAdmin(params) {
  return request({
    url: '/admin',
    method: 'GET',
    params: { ...params }
  });
}

export function getAdminById(id) {
  return request({
    url: `/admin/${id}`,
    method: 'GET'
  });
}

export function countAdminByUsername(username) {
  return request({
    url: '/admin/count/username',
    method: 'GET',
    params: { username }
  });
}

export function insertAdmin(params) {
  console.log('params ==> ', params);
  return request({
    url: '/admin',
    method: 'POST',
    data: { ...params }
  });
}

export function updatePasswordById(params) {
  return request({
    url: `/admin/${params.id}/password`,
    method: 'PUT',
    params: {
      rawPassword: params.rawPassword,
      newPassword: params.newPassword
    }
  });
}

export function updateBaiscById(params) {
  return request({
    url: `/admin/${params.id}/basic`,
    method: 'PUT',
    params: {
      nickname: params.nickname,
      phone: params.phone
    }
  });
}

export function updateRoleIdById(params) {
  return request({
    url: `/admin/${params.id}/role`,
    method: 'PUT',
    params: {
      roleId: params.roleId
    }
  });
}

export function updateStatusById(params) {
  return request({
    url: `/admin/${params.id}/status`,
    method: 'PUT',
    params: {
      status: params.status
    }
  });
}

export function resetPasswordById(id) {
  return request({
    url: `/admin/${id}/reset/password`,
    method: 'PUT'
  });
}

export function deleteAdminById(id) {
  return request({
    url: `/admin/${id}`,
    method: 'DELETE'
  });
}
