import React, { PropTypes } from 'react'

const Product = ({ price, quantity, name}) => (
  <div>
    {name} - &#36;{price}{quantity ? ` x ${quantity}` : null}
  </div>
)

Product.propTypes = {
  price: PropTypes.number,
  quantity: PropTypes.number,
  name: PropTypes.string
}

export default Product
