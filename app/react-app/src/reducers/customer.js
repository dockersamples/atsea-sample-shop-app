import {
  CREATE_CUSTOMER 
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
    default:
      return state
  }
}

export const isActive = state => state.customerId !== ''

export default customer