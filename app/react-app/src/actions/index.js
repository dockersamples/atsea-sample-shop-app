const request = require('superagent-promise')(require('superagent'), Promise)
import shop from '../api/shop'
import * as types from '../constants/ActionTypes'

const receiveProducts = products => ({
  type: types.RECEIVE_PRODUCTS,
  products: products
})

const BASE_URL = '/mobyartshop/api'

export const fetchAllItems = () => (dispatch) => {
  let dispatchObj = {
    type: types.ITEMS_REQUEST,
    payload: {
      promise:
        request
          .get(`${BASE_URL}/product/`)
          .accept('application/json')
          .end()
          .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
};

export const getAllProducts = () => dispatch => {
  shop.getProducts(products => {
    dispatch(receiveProducts(products))
  })
}

const addToCartUnsafe = productId => ({
  type: types.ADD_TO_CART,
  productId
})

export const addToCart = productId => (dispatch, getState) => {
    dispatch(addToCartUnsafe(productId))
}

export const checkout = products => (dispatch, getState) => {
  const { cart } = getState()

  dispatch({
    type: types.CHECKOUT_REQUEST
  })
  shop.buyProducts(products, () => {
    dispatch({
      type: types.CHECKOUT_SUCCESS,
      cart
    })
    // Replace the line above with line below to rollback on failure:
    // dispatch({ type: types.CHECKOUT_FAILURE, cart })
  })
}
