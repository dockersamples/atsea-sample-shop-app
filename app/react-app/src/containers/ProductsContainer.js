import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import { addToCart, fetchAllItems } from '../actions'
import { getVisibleProducts } from '../reducers/products'
import ProductsList from '../components/ProductsList'

const ProductsContainer = ({ products, addToCart, fetchAllItems }) => (
  <ProductsList products={products} addToCart={addToCart} fetchAllItems={fetchAllItems} />
)

ProductsContainer.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    description: PropTypes.string,
    image: PropTypes.string
  })).isRequired,
  addToCart: PropTypes.func.isRequired
}

const mapStateToProps = state => ({
  products: getVisibleProducts(state.products)
})

export default connect(
  mapStateToProps,
  { addToCart, fetchAllItems }
)(ProductsContainer)
