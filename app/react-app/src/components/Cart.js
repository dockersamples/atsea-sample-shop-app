import React, { PropTypes } from 'react'
import CartIcon from './CartIcon'
import './Cart.css'

const Cart = ({ total }) => {
  // const successMessage = `Added to your cart`;
  const filler = ' ';
  return (
    <div className="checkoutSection">
      <div className="cartRow">
      <div className="checkoutMessage">
        { filler } 
      </div>
        <div className="cartQuantity">
          <CartIcon />
          <div className="cartDigit">
            {total}
          </div>
        </div>
      </div>
    </div>
  )
}

Cart.propTypes = {
  total: PropTypes.number,
}

export default Cart
