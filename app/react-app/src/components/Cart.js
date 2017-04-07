import React, { PropTypes } from 'react'
import { Link } from 'react-router'
import Product from './Product'
import CartIcon from './CartIcon'
import FlatButton from 'material-ui/FlatButton'
import './Cart.css'

const Cart = ({ products, total, totalProducts }) => {
  // const hasProducts = products.length > 0
  const cartRow = (
      <div className="cartRow">
        <div className="cartIcon">
          <CartIcon />
        </div>
        <div className="cartQuantity">
          {totalProducts}
        </div>
      </div>
  )
  return (
    <div className="checkoutSection">
      <FlatButton
        label="Checkout"
        containerElement={<Link to="checkout"> Checkout </Link>}
      />
      {cartRow}
    </div>
  )
}

Cart.propTypes = {
  products: PropTypes.array,
  total: PropTypes.string,
}

export default Cart
