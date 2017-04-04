import {
  CREATE_CUSTOMER,
  LOGIN_CUSTOMER
} from '../constants/ActionTypes'


const initialState = {
    customerId: ''
}

const customer = (state = initialState, action) => {
  switch (action.type) {
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
    case `${LOGIN_CUSTOMER}_ACK`:{
        console.log('in login customer ack')
        console.log(action.payload)
        return state
    }
    case `${LOGIN_CUSTOMER}_ERR`:{
        console.log('in login customer err')
        console.log(action.payload)
        return state
    }
    default:
      return state
  }
}

export const isActive = state => state.customerId !== ''

export default customer