import axios from 'axios';

const baseURL = 'http://127.0.0.1:8088/pocketbook/api'

const api = axios.create({
  baseURL,
  timeout: 1000,
  config: {
    'Login-Free': '1'
  }
})

api.interceptors.request.use(
  config => {
    // 配置免登录
    config.headers['Login-Free'] = '1'
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

api.interceptors.response.use(
  response => {
    const { code, data, msg } = response
    //todo: 根据code码校验请求状态
    return data
  }
)

export default api