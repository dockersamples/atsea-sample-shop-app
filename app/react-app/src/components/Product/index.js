import React, { Component, PropTypes } from 'react'
import './styles.css'

class Product extends Component {
  render() {
    const { price, quantity, name, image } = this.props;
    const image2 = (
      <img
        alt="Logo"
        src={process.env.PUBLIC_URL + image}
        height="60px"
        width="60px"
      />
    );
    return (
      <div className='productItem'>
        <div className='columnLeft'>
          {image2}
        </div>
        <div className='columnCenter'>
          <div>{name}</div>
          <div>
          <span>{`Quantity  ${quantity}`}</span>
          <span className='remove'>{`remove`}</span>
          </div>
        </div>
        <div className='columnRight'>
          {price}
        </div>
      </div>
    );
  }
}


Product.propTypes = {
  price: PropTypes.number,
  quantity: PropTypes.number,
  name: PropTypes.string,
  image: PropTypes.string,
}

export default Product
