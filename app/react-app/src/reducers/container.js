import {
  FETCH_CONTAINER_ID
} from '../constants/ActionTypes'

const initialState = {
  ip: '10.0.2.3',
  host: 'fa8f41f55e98',
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
        ip: '10.0.2.3',
      }
    default:
      return state
  }
}

export default container