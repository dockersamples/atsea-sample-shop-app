import React, { Component, PropTypes } from 'react'
import ProductItem from '../../components/ProductItem'

import './styles.css'

export default class ProductsList extends Component {

  render() {
    const { products, addToCart } = this.props
    return (
      <div>
        <div className="productListWrapper">
          {products.map(product => (
            <ProductItem
              key={product.productId}
              product={product}
              onAddToCartClicked={addToCart}
            />
          ))}
        </div>
      </div>
    )
  }
}

ProductsList.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    description: PropTypes.string,
    image: PropTypes.string,
  })).isRequired,
  addToCart: PropTypes.func.isRequired,
}

