import React, { PropTypes } from 'react'
import ProductItem from '../components/ProductItem'


const ProductsList = ({ title, products, addToCart }) => (
  <div>
    <h3>{title}</h3>
    <div>
      {products.map(product =>
        <ProductItem
          key={product.productId}
          product={product}
          onAddToCartClicked={() => addToCart(product.productId)} />
      )}
      </div>
  </div>
)

ProductsList.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
  })).isRequired,
  addToCart: PropTypes.func.isRequired,
  title: PropTypes.string.isRequired
}

export default ProductsList
