const request = require('superagent-promise')(require('superagent'), Promise)
import shop from '../api/shop'
import * as types from '../constants/ActionTypes'

const receiveProducts = products => ({
  type: types.RECEIVE_PRODUCTS,
  products: products
})

const BASE_URL = '/atsea/api'

export const createOrder = (values) => (dispatch) => {
  const url = `${BASE_URL}/order/`
  let dispatchObj = {
    type: types.CREATE_ORDER,
    payload: {
      promise:
      request
        .post(url)
        // TODO: will there ever be some sort of authentication here? for username and password.
        .set('Content-Type', 'application/json')
        .accept('application/json')
        .send(
          {
              /*
                TODO: orderId is hard coded in because the api will return a null pointer exception without it.
                However, the orderId is decided by the backend. If we pass an id in the request, and it already exists,
                we will get an "Unable to create."
                0 was chosen because the backend begins incrementing it's order id at 1.
              */
              "orderId": 0,
              "orderDate" : values.orderDate,
              "customerId" : values.customerId,
              "productsOrdered" : values.quantityById,
          }
        )
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
};

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

export const fetchAllCustomers = () => (dispatch) => {
  let dispatchObj = {
    type: types.FETCH_CUSTOMERS,
    payload: {
      promise:
        request
          .get(`${BASE_URL}/customer/`)
          .accept('application/json')
          .end()
          .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
};

export const createCustomer = (username, password) => (dispatch) => {
  const url = `${BASE_URL}/customer/`
  let dispatchObj = {
    type: types.CREATE_CUSTOMER,
    payload: {
      promise:
      request
        .post(url)
        .set('Content-Type', 'application/json')
        .accept('application/json')
        .send(
          //TODO: take out hard coded values for customer information
          {address:"144 Townsend Street",email:"test@gmail.com",name:"Jess",password:password,phone:"9999999999",username:username,customerId:0, enabled:"true", role:"user"}
        )
        .end()
        .then((res) => res.body)
    },
  }

  return dispatch(dispatchObj)
};

export const getCustomer = (username, password) => (dispatch) => {
 let dispatchObj = {
    type: types.LOGIN_CUSTOMER,
    payload: {
      promise:
        request
          .get(`${BASE_URL}/customer/username=${username}`)
          .accept('application/json')
          .end()
          .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
}

export const logoutCustomer = () => (dispatch) => {
  dispatch({
    type: types.LOGOUT_CUSTOMER
  })
}


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
