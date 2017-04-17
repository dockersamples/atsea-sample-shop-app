const request = require('superagent-promise')(require('superagent'), Promise)
import { getJwtToken } from './storage'
import shop from '../api/shop'
import * as types from '../constants/ActionTypes'

const API = '/api'
const UTILITY = '/utility'

export const createOrder = (values) => (dispatch) => {
  const url = `${API}/order/`
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
          "orderDate": values.orderDate,
          "customerId": values.customerId,
          "productsOrdered": values.quantityById,
        }
        )
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
};

export const purchaseOrder = () => (dispatch) => {
  const token = getJwtToken()
  let dispatchObj = {
    type: types.PURCHASE,
    payload: {
      promise:
      request
        .get('/purchase/')
        .set('Authorization', 'Bearer ' + token)
        .accept('application/json')
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
}

export const fetchAllItems = () => (dispatch) => {
  let dispatchObj = {
    type: types.ITEMS_REQUEST,
    payload: {
      promise:
      request
        .get(`${API}/product/`)
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
        .get(`${API}/customer/`)
        .accept('application/json')
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
};

export const createCustomer = (username, password) => (dispatch) => {
  const url = `${API}/customer/`
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
        { address: "144 Townsend Street", email: "test@gmail.com", name: "Jess", password: password, phone: "9999999999", username: username, customerId: 0, enabled: "true", role: "user" }
        )
        .end()
        .then((res) => res.body)
    },
  }

  return dispatch(dispatchObj)
};


export const getCustomer = (username, password) => (dispatch) => {
  //TODO: update actions 
  let dispatchObj = {
    type: types.LOGIN_CUSTOMER,
    payload: {
      promise:
      request
        .get(`${API}/customer/username=${username}`)
        .accept('application/json')
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
}

export const loginCustomer = (username, password) => (dispatch) => {
  let dispatchObj = {
    type: types.LOGIN_CUSTOMER,
    payload: {
      promise:
      request
        .post('/login/')
        .set('Content-Type', 'application/json')
        .accept('application/json')
        .send(
        {
          username: username,
          password: password,
        }
        )
        .end()
        .then((res) => res.body)
    },
  }
  return dispatch(dispatchObj)
}

export const fetchContainerId = () => (dispatch) => {
  const url = `${UTILITY}/containerid/`
  let dispatchObj = {
    type: types.FETCH_CONTAINER_ID,
    payload: {
      promise:
      request
        .get(url)
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

export const addUser = (username) => (dispatch) => {
  dispatch({
    type: types.ADD_USER,
    username
  })
}

// DUMMY ITEMS
const fetchDummyItems = products => ({
  type: types.DUMMY_ITEMS_REQUEST,
  products: products
})

export const fetchAllDummyItems = () => dispatch => {
  shop.getProducts(products => {
    dispatch(fetchDummyItems(products))
  })
}

// success message shows for 2.5 seconds
export const resetItemAdded = () => (dispatch) => {
  dispatch({
    type: types.RESET_ADD_TO_CART,
  })
}

const addToCartUnsafe = productId => ({
  type: types.ADD_TO_CART,
  productId
})

export const showAddToCart = () => (dispatch) => {
  dispatch({
    type: types.SHOW_ADD_TO_CART,
  })
}

export const addToCart = productId => (dispatch, getState) => {
  dispatch(addToCartUnsafe(productId))
  dispatch(showAddToCart())
  setTimeout(() => {
    dispatch(resetItemAdded())
  }, 2500)
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
