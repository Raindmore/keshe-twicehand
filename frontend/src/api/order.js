import request from '@/utils/request'
export default {
  getPage(n, s, o) { return request.get('/order/getOrderPage', { params: { pageNum: n, pageSize: s, Oid: o } }) },
  getOne(Oid) { return request.get('/order/getOneOrder', { params: { Oid } }) },
  findBuy(BUYSid, Oid) { return request.get('/order/findOneBUY', { params: { BUYSid, Oid } }) },
  findSell(SELLSid, Oid) { return request.get('/order/findOneSELL', { params: { SELLSid, Oid } }) },
  insert(BUYSid, SELLSid, Gid, Oprice) { return request.post('/order/insertOrder', null, { params: { BUYSid, SELLSid, Gid, Oprice } }) },
  delete(id) { return request.delete('/order/deleteOrder/' + id) },
  deleteBatch(ids) { return request.post('/order/deleteOrder/batch', ids) },
  getAllTotal() { return request.get('/order/getAllTotal') },
  getOpriceTotal() { return request.get('/order/getOpriceTotal') }
}
