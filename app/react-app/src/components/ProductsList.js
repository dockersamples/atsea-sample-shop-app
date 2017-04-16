import React, { Component, PropTypes } from 'react'
import ProductItem from '../components/ProductItem'

import './ProductsList.css'

export default class ProductsList extends Component {

  fetchAll = () => {
    const { fetchAllItems } = this.props
      fetchAllItems()
        .then((resp) => {
          console.log('yay we have products')
        })
        .catch((err) => {
          console.log('boo retry failed')
          console.log(err)
        })
  }

  retryFetchProducts = () => {
    const { products } = this.props
    if (products.length===0) {
      console.log('attempting retry')
      setTimeout(() => {
        this.fetchAll()
        }, 250)
    } else {
      console.log('no need to retry')
    }
  }

  render() {
    const { products, addToCart } = this.props
    this.retryFetchProducts()
    return(
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
  fetchAllItems: PropTypes.func.isRequired,
}

