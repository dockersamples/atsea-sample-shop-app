import {
  ADD_TO_CART,
  SHOW_ADD_TO_CART,
  RESET_ADD_TO_CART,
  CHECKOUT_REQUEST,
  CHECKOUT_FAILURE,
  CREATE_ORDER,
  PURCHASE,
} from '../constants/ActionTypes'

const initialState = {
  addedIds: [],
  quantityById: {},
  itemJustAdded: false,
}

const addedIds = (state = initialState.addedIds, action) => {
  switch (action.type) {
    case ADD_TO_CART:
      if (state.indexOf(action.productId) !== -1) {
        return state
      }
      return [...state, action.productId]
    default:
      return state
  }
}

const quantityById = (state = initialState.quantityById, action) => {
  switch (action.type) {
    case ADD_TO_CART:
      const { productId } = action
      return {
        ...state,
        [productId]: (state[productId] || 0) + 1
      }
    default:
      return state
  }
}

export const getQuantity = (state, productId) =>
  state.quantityById[productId] || 0

export const getAddedIds = state => state.addedIds

const cart = (state = initialState, action) => {
  switch (action.type) {
    case SHOW_ADD_TO_CART:
      return {
        ...state,
        itemJustAdded: true,
      }
    case RESET_ADD_TO_CART:
      return {
        ...state,
        itemJustAdded: false,
      }
    case `${CREATE_ORDER}_ACK`:
      return initialState
    case `${CREATE_ORDER}_ERR`:
      return state
    case `${PURCHASE}_ACK`:
      return initialState
    case `${PURCHASE}_ERR`:
      return state
    case CHECKOUT_REQUEST:
      return initialState
    case CHECKOUT_FAILURE:
      return action.cart
    default:
      return {
        addedIds: addedIds(state.addedIds, action),
        quantityById: quantityById(state.quantityById, action)
      }
  }
}

export default cart
