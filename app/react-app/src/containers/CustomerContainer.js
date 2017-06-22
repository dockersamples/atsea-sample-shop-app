import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { createCustomer } from '../actions'
import { isActive } from '../reducers'
import CreateCustomer from '../components/CreateCustomer'

class CustomerContainer extends Component {
  render() {
    const { createCustomer, isActive } = this.props
    return (
      <CreateCustomer createCustomer={createCustomer} isActive={isActive} />
    )
  }
}

CustomerContainer.propTypes = {
  createCustomer: PropTypes.func.isRequired,
  isActive: PropTypes.bool
}

const mapStateToProps = state => ({
  isActive: isActive(state)
})

export default connect(
  mapStateToProps,
  { createCustomer }
)(CustomerContainer)
