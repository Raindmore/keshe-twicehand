import request from '@/utils/request'
export default {
  login(u, p) { return request.get('/student/login', { params: { Susername: u, Spassword: p } }) },
  getPage(n, s, u) { return request.get('/student/getStudentPage', { params: { pageNum: n, pageSize: s, Susername: u } }) },
  getAll() { return request.get('/student/getStudent') },
  getOne(id) { return request.get('/student/getOneStudent', { params: { Sid: id } }) },
  insert(d) { return request.post('/student/insertStudent', d) },
  update(d) { return request.put('/student/updateStudent', d) },
  delete(id) { return request.delete('/student/deleteStudent/' + id) },
  deleteBatch(ids) { return request.post('/student/deleteStudent/batch', ids) },
  updatePassword(Sid, Sp, Snp) { return request.put('/student/updatePassword', null, { params: { Sid, Spassword: Sp, Snewpassword: Snp } }) },
  getAllTotal() { return request.get('/student/getStudentAllTotal') }
}
