import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import { getTotal, getTotalProducts, getCartProducts } from '../reducers'
import Cart from '../components/Cart'

const CartContainer = ({ products, total, totalProducts }) => (
  <Cart
    products={products}
    total={total}
    totalProducts={totalProducts}
   />
)

CartContainer.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired
  })).isRequired,
  total: PropTypes.string,
  totalProducts: PropTypes.number,
}

const mapStateToProps = (state) => ({
  products: getCartProducts(state),
  total: getTotal(state),
  totalProducts: getTotalProducts(state)
})

export default connect(mapStateToProps)(CartContainer)
