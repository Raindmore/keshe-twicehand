import request from '@/utils/request'
export default {
  getPage(n, s, g) { return request.get('/leave/getLeavePage', { params: { pageNum: n, pageSize: s, Gid: g } }) },
  getPageSid(n, s, Sid) { return request.get('/leave/getLeavePageSid', { params: { pageNum: n, pageSize: s, Sid } }) },
  insert(Sid, Gid, Lm) { return request.post('/leave/insertLeave', null, { params: { Sid, Gid, Lmessage: Lm } }) },
  delete(Lid, Sid) { return request.delete('/leave/deleteLeave/' + Lid + '&' + Sid) }
}
