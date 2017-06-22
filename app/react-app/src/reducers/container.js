import {
  FETCH_CONTAINER_ID
} from '../constants/ActionTypes'

const initialState = {
  ip: '',
  host: '',
}
const container = (state = initialState, action) => {
  switch (action.type) {
    case `${FETCH_CONTAINER_ID}_ACK`:
      return {
        ...state,
        ip: action.payload.ip,
        host: action.payload.host,
      }
    case `${FETCH_CONTAINER_ID}_ERR`:
      return {
        ...state,
        ip: ''
      }
    default:
      return state
  }
}

export default container