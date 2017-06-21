import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import { getTotal, getTotalProducts, getCartProducts } from '../reducers'
import Title from '../components/Title'

const TitleContainer = ({ products, total, totalProducts }) => (
  <Title
    products={products}
    total={total}
    totalProducts={totalProducts}
  />
)

TitleContainer.propTypes = {
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

export default connect(mapStateToProps)(TitleContainer)
