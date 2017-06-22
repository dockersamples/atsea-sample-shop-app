import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { getCustomer, logoutCustomer } from '../actions'
import { isActive } from '../reducers'
import Login from '../components/Login'

class LoginContainer extends Component {
  render() {
    const { getCustomer, logoutCustomer, isActive } = this.props
    return (
      <Login loginCustomer={getCustomer} logoutCustomer={logoutCustomer} isActive={isActive} />
    )
  }
}

LoginContainer.propTypes = {
  getCustomer: PropTypes.func.isRequired,
  logoutCustomer: PropTypes.func.isRequired,
  isActive: PropTypes.bool
}

const mapStateToProps = state => ({
  isActive: isActive(state)
})

export default connect(
  mapStateToProps,
  { getCustomer, logoutCustomer }
)(LoginContainer)
