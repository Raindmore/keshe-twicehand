import request from '@/utils/request'
export default {
  getCollection(Sid, Gn) { return request.get('/collection/getCollection', { params: { Sid, Gname: Gn } }) },
  getMyCollection(Sid, Gid) { return request.get('/collection/getMyCollection', { params: { Sid, Gid } }) },
  insert(Sid, Gid) { return request.post('/collection/insertCollection', null, { params: { Sid, Gid } }) },
  delete(Sid, Gid) { return request.delete('/collection/deleteCollection/' + Sid + '&' + Gid) }
}
