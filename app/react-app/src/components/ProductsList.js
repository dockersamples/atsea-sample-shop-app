import React, { Component, PropTypes } from 'react'
import ProductItem from '../components/ProductItem'

import { GridList } from 'material-ui/GridList'
import './ProductsList.css'

export default class ProductsList extends Component {

  render() {
    const { title, products, addToCart } = this.props
    return(
      <div>
        <div className="productListTitle">{title}</div>
        <GridList cols={4} padding={42} cellHeight={347}>
          {products.map(product => (
            <ProductItem
              key={product.productId}
              product={product}
              onAddToCartClicked={addToCart}
            />
          ))}
        </GridList>
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
  title: PropTypes.string.isRequired
}

