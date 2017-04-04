import React, { PropTypes } from 'react'
import Product from './Product'

const ProductItem = ({ product, onAddToCartClicked }) => (
  <div style={{ marginBottom: 20 }}>
    <Product
      name={product.name}
      price={product.price} />
    <button
      onClick={onAddToCartClicked} > 
      Add to cart
    </button>
  </div>
)

ProductItem.propTypes = {
  product: PropTypes.shape({
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
  }).isRequired,
  onAddToCartClicked: PropTypes.func.isRequired
}

export default ProductItem
