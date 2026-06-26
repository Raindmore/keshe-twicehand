import request from '@/utils/request'
export default {
  login(u, p) { return request.get('/admin/login', { params: { Ausername: u, Apassword: p } }) },
  getPage(n, s, u) { return request.get('/admin/getAdminPage', { params: { pageNum: n, pageSize: s, Ausername: u } }) },
  getAll() { return request.get('/admin/getAdmin') },
  getOne(id) { return request.get('/admin/getOneAdmin', { params: { Aid: id } }) },
  insert(d) { return request.post('/admin/insertAdmin', d) },
  update(d) { return request.put('/admin/updateAdmin', d) },
  delete(id) { return request.delete('/admin/deleteAdmin/' + id) },
  deleteBatch(ids) { return request.post('/admin/deleteAdmin/batch', ids) },
  updatePassword(Aid, Ap, Anp) { return request.put('/admin/updatePassword', null, { params: { Aid, Apassword: Ap, Anewpassword: Anp } }) },
  getAllTotal() { return request.get('/admin/getAdminAllTotal') }
}
