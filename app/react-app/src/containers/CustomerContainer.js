import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import { createCustomer } from '../actions'
import CreateCustomer from '../components/CreateCustomer'

class CustomerContainer extends Component {
    render() {
        const { createCustomer } = this.props
        return (
            <CreateCustomer createCustomer={createCustomer} />
         )
    }
}

CustomerContainer.propTypes = {
  createCustomer: PropTypes.func.isRequired
}

const mapStateToProps = (state) => ({
})

export default connect(
  mapStateToProps,
  { createCustomer }
)(CustomerContainer)
