import {
  CREATE_CUSTOMER,
  LOGIN_CUSTOMER,
  LOGOUT_CUSTOMER,
  ADD_USER,
} from '../constants/ActionTypes'


const initialState = {
  customerId: '',
  username: '',
  details: {}
}

const customer = (state = initialState, action) => {
  switch (action.type) {
    case `${ADD_USER}`:
      return {
        ...state,
        username: action.username
      }
    case `${CREATE_CUSTOMER}_ACK`:
      return {
        ...state,
        ...action.payload
      }
    case `${CREATE_CUSTOMER}_ERR`:
      return {
        ...state,
        customerId: ''
      }
    case `${LOGIN_CUSTOMER}_ACK`:
      return {
        ...state,
        customerId: action.payload.customerId,
        details: action.payload
      }
    case `${LOGIN_CUSTOMER}_ERR`:
      return {
        ...state,
        customerId: ''
      }
    case `${LOGOUT_CUSTOMER}`:
      return {
        ...state,
        customerId: '',
        details: {}
      }
    default:
      return state
  }
}

export const isActive = state => state.customerId !== ''

export default customer