import api from '../request.js'

const prefix = '/bill'

export const getCardData = (data) => 
  api.post(prefix + '/getCardData', data)

export const getBillList = (data) => 
  api.post(prefix + '/getBillList', data)

export const addBillInfo = (data) => 
  api.post(prefix + '/updateBillInfo', data)

export const updateBillInfo = (data) => 
  api.post(prefix + '/updateBillInfo', data)

export const delBillInfo = (data) => 
  api.post(prefix + '/delBillInfo', data)