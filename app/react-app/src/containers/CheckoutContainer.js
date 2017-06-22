import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import moment from 'moment'
import {
  checkout,
  createOrder,
  purchaseOrder,
} from '../actions'
import { getTotal, getCartProducts, getTotalProducts, getCustomerId, getQuantityById } from '../reducers'
import SuccessMessage from '../components/SuccessMessage'
import Checkout from '../components/Checkout'
import { Link } from 'react-router'
import { SubmissionError } from 'redux-form'


class CheckoutContainer extends Component {
  constructor(props) {
    super(props)
    this.state = {
      orderComplete: false,
    }
  }

  handleSuccess = () => {
    this.setState({ orderComplete: true })
  }

  // eslint-disable-next-line
  handleSubmit = (values) => {
    const {
        customerId,
      purchaseOrder,
      totalProducts,
      quantityById
      } = this.props

    // This data will be used for create order endpoint
    const date = moment().format()
    // eslint-disable-next-line
    const {
        firstName,
      lastName,
      } = values
    // eslint-disable-next-line
    const submitData = {
      customerId,
      name: firstName,
      orderDate: date,
      lastName,
      quantityById
    }

    if (totalProducts === 0) {
      throw new SubmissionError({ _error: "Please add to cart first..." })
    }

    // TODO: Create Order
    return purchaseOrder()
      .then(this.handleSuccess)
      // error: status 404
      .catch((err) => {
        throw new SubmissionError({ _error: "Please login before completing order..." })
      })
  }

  renderCheckout() {
    const { products, total, totalProducts, checkout } = this.props
    return (
      <Checkout
        products={products}
        total={total}
        totalProducts={totalProducts}
        onCheckoutClicked={() => checkout(products)}
        handleSubmit={this.handleSubmit}
      />
    );
  }

  renderSuccess() {
    const successMessage = "You have successfully placed an order!"
    return (
      <SuccessMessage
        message={successMessage}
        label="Continue Shopping"
        containerElement={<Link to="/" />}
      />
    );
  }

  render() {
    return (
      <div>
        {this.state.orderComplete ?
          this.renderSuccess()
          : this.renderCheckout()
        }
      </div>
    );
  }
}

CheckoutContainer.propTypes = {
  products: PropTypes.arrayOf(PropTypes.shape({
    productId: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    quantity: PropTypes.number.isRequired,
    image: PropTypes.string.isRequired,
  })).isRequired,
  total: PropTypes.string,
  checkout: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({
  products: getCartProducts(state),
  total: getTotal(state),
  totalProducts: getTotalProducts(state),
  customerId: getCustomerId(state),
  quantityById: getQuantityById(state),
})

export default connect(
  mapStateToProps,
  { checkout, createOrder, purchaseOrder }
)(CheckoutContainer)
