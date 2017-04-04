import React, { Component, PropTypes } from 'react'

import TextField from 'material-ui/TextField'

class Login extends Component {
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

    handleLogin = () => {
        const { loginCustomer } = this.props;
        loginCustomer(this.state.username, this.state.password)
    }

    handleLogout = () => {
        const { logoutCustomer } = this.props;
        this.setState({ username: '', password: ''})
        logoutCustomer()
    }

    renderLogin() {
       return (
            <div>
                <h2> Login here :) </h2>
                <TextField value={this.state.username} hintText="Username" onChange={this.handleUsername} />
                <TextField value={this.state.password} hintText="Choose a Password" onChange={this.handlePass} />
                <button onClick={this.handleLogin}> Login </button>

            </div>

        );
    }

    renderActive() {
        return (
            <div>
                <h3> Logged in! </h3>
                <button onClick={this.handleLogout}> Logout</button>
            </div>
        );
    }

    render() {
        const { isActive } = this.props
        return isActive ? this.renderActive() : this.renderLogin()
    }
}

Login.propTypes = {
  loginCustomer: PropTypes.func,
  logoutCustomer: PropTypes.func,
  isActive: PropTypes.bool
}


export default Login 