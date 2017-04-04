import React, { Component, PropTypes } from 'react'

import TextField from 'material-ui/TextField'

class CreateCustomer extends Component {
    constructor(props){
        super(props)
        this.state = {
            username: '',
            password: ''
        }
    }

    handleUsername = (event) => {
        this.setState({ username: event.target.value })
    }
    handlePass = (event) => {
        this.setState({ password: event.target.value })
    }

    handleCreate = () => {
        const { createCustomer } = this.props;
        createCustomer(this.state.username, this.state.password)
    }

    renderCreate() {
       return (
            <div>
                <h2> Create your user ID </h2>
                <TextField value={this.state.username} hintText="Username" onChange={this.handleUsername} />
                <TextField value={this.state.password} hintText="Choose a Password" onChange={this.handlePass} />
                <button onClick={this.handleCreate}> Sign Up</button>

            </div>

        );
    }

    renderActive() {
        const { username } = this.state
        const message = `Welcome ${username}!`
        return (
            <div>
               {message}
            </div>
        );
    }

    render() {
        const { isActive } = this.props
        return isActive ? this.renderActive() : this.renderCreate()
    }
}

CreateCustomer.propTypes = {
  createCustomer: PropTypes.func,
  isActive: PropTypes.bool
}


export default CreateCustomer;