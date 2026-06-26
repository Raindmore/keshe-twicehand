import request from '@/utils/request'
export default {
  getPage(n, s, g) { return request.get('/goods/getGoodsPage', { params: { pageNum: n, pageSize: s, Gname: g } }) },
  getSidGoods(Sid, Gs, Gn) { return request.get('/goods/getSidGoods', { params: { Sid, Gstatus: Gs, Gname: Gn } }) },
  getOne(id) { return request.get('/goods/getOneGoods', { params: { Gid: id } }) },
  insert(d) { return request.post('/goods/insertGoods', d) },
  update(d) { return request.put('/goods/updateGoods', d) },
  updateStudent(d) { return request.put('/goods/updateStudentGoods', d) },
  updateStatus(Gid, Gs) { return request.put('/goods/updateGoodsGstatus', null, { params: { Gid, Gstatus: Gs } }) },
  delete(id) { return request.delete('/goods/deleteGoods/' + id) },
  deleteBatch(ids) { return request.post('/goods/deleteGoods/batch', ids) },
  getAdminPage(n, s, g) { return request.get('/goods/getAdminGoodsPage', { params: { pageNum: n, pageSize: s, Gname: g } }) },
  getAllTotal() { return request.get('/goods/getAllTotal') }
}
