import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import { checkout } from '../actions'
import { getTotal, getCartProducts, getTotalProducts } from '../reducers'
import Checkout from '../components/Checkout'

const CheckoutContainer = ({ products, total, totalProducts, checkout }) => (
  <Checkout
    products={products}
    total={total}
    totalProducts={totalProducts}
    onCheckoutClicked={() => checkout(products)} />
)

CheckoutContainer.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired
  })).isRequired,
  total: PropTypes.string,
  checkout: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({
  products: getCartProducts(state),
  total: getTotal(state),
  totalProducts: getTotalProducts(state)
})

export default connect(
  mapStateToProps,
  { checkout }
)(CheckoutContainer)
