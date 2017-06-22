import React, { Component, PropTypes } from 'react'
import Product from '../../components/Product'
import CustomerInfoForm from '../../components/CustomerInfoForm'
import './styles.css'

class Checkout extends Component {

  renderForm() {
    const { handleSubmit } = this.props;
    return (
      <CustomerInfoForm onSubmit={handleSubmit} />
    );
  }

  renderProductList() {
    const { products } = this.props;
    const hasProducts = products.length > 0
    const nodes = hasProducts ? (
      products.map(product =>
        <Product
          name={product.name}
          price={product.price}
          quantity={product.quantity}
          image={product.image}
          key={product.productId}
        />
      )
    ) : (
        //TODO: Ask Josh for formatting
        <span className='emptyMessage'>Please add some products to the cart.</span>
      )
    return (
      <div className='productSection'>
        {nodes}
      </div>
    );
  }

  renderCartTotal() {
    const { total } = this.props;
    const subtotal = parseFloat(total).toFixed(2);
    const shipping = parseFloat(0).toFixed(2);
    const taxes = parseFloat(.06 * total).toFixed(2);
    const finalTotal = (parseFloat(subtotal) + parseFloat(taxes)).toFixed(2);
    return (
      <div className='totalSection' >
        <div className='totalDetails'>
          <div>
            <span>subtotal</span>
            <span>{`$${subtotal}`}</span>
          </div>
          <div>
            <span>shipping</span>
            <span>{`$${shipping}`}</span>
          </div>
          <div>
            <span>taxes</span>
            <span>{`$${taxes}`}</span>
          </div>
        </div>
        <div className='totalFinal'>
          <span>Total</span>
          <span>{`$${finalTotal}`}</span>
        </div>
      </div>
    );
  }

  render() {
    return (
      <div>
        <div className='header'>
          <div className='checkoutTitle'>
            Checkout
          </div>
        </div>
        <div className='panel'>
          <div className='formSection'>
            {this.renderForm()}
          </div>
          <div className='cartSection'>
            {this.renderProductList()}
            {this.renderCartTotal()}
          </div>
        </div>
      </div>
    )
  }
}

Checkout.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired,
    image: PropTypes.string.isRequired
  })).isRequired,
  total: PropTypes.string,
  handleSubmit: PropTypes.func,
}

export default Checkout
