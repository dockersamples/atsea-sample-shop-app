import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import moment from 'moment'
import { checkout, createOrder } from '../actions'
import { getTotal, getCartProducts, getTotalProducts, getCustomerId, getQuantityById } from '../reducers'
import Checkout from '../components/Checkout'

class CheckoutContainer extends Component {
    constructor(props){
      super(props)
      this.state = {
        orderComplete: false,
      }
    }

    handleSuccess = () => {
      // TODO: make an overlay panel
      // TODO: should state be controlled in this container?
      // change the state to a success
      this.setState({ orderComplete: true })
      console.log('success!')
    }

    handleSubmit = (values) => {
      // e.preventDefault()
      //TODO: put check for if there's no user logged in or no quantity
      const { customerId, createOrder, quantityById } = this.props
      const date = moment().format()
      const {
        firstName,
        lastName,
      } = values
      const submitData = {
        customerId,
        name: firstName,
        orderDate: date,
        lastName,
        quantityById
      }
      createOrder(submitData)
        .then(this.handleSuccess)
        // error: status 404
        .catch((err) => {
          //TODO: more elegant error handling
          console.log('There was an error creating the order!!!!')
          console.log(err)
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
      return (
        <div>
          You have successfully placed an order!
        </div>
      );
   }

  render () {
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
  { checkout, createOrder }
)(CheckoutContainer)
