import React, { PropTypes } from 'react'
import GridTile from '../../components/GridTile'

const ProductItem = ({ product, onAddToCartClicked }) => (
  <div style={{ marginBottom: 20 }}>
    <GridTile
      productId={product.productId}
      name={product.name}
      description={product.description}
      price={`$${product.price}`}
      image={product.image}
      onAddToCartClicked={onAddToCartClicked}
    />
  </div>
)

ProductItem.propTypes = {
  product: PropTypes.shape({
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    description: PropTypes.string,
    image: PropTypes.string
  }).isRequired,
  onAddToCartClicked: PropTypes.func.isRequired
}

export default ProductItem
