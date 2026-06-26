import request from './request'
import { serverIp } from '../../public/config'

export function uploadImg(params, token) {
  return request.post(`http://${serverIp}:9090/file/upload`, params)
}
