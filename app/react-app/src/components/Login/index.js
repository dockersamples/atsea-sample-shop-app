import React, { Component, PropTypes } from 'react'
import TextField from 'material-ui/TextField'
import FlatButton from 'material-ui/FlatButton'
import Modal from 'react-modal'
import './styles.css'

const customStyles = {
  overlay: {
    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: 'rgba(255, 255, 255, 0.97)',
    boxShadow: '0 2px 4px 0 rgba(0, 0, 0, 0.27)',
    height: '100%',
    width: '100%'
  },
  content: {
    top: '30%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
    border: '0x',
  }
}

class Login extends Component {
  constructor(props) {
    super(props)
    this.state = {
      username: '',
      password: '',
      open: false,
      error: {
        username: '',
        password: '',
      }
    }
  }

  handleUsername = (event) => {
    this.setState({ username: event.target.value })
  }

  handlePass = (event) => {
    this.setState({ password: event.target.value })
  }

  handleOpen = () => {
    this.setState({ open: true });
  }

  handleClose = () => {
    this.setState({
      username: '',
      password: '',
      open: false,
      error: { username: '', password: '' }
    })
  }

  handleLogin = () => {
    const { loginCustomer } = this.props;

    // validate
    // error: empty username
    if (!this.state.username) {
      this.setState({ error: { username: 'Username is required' } })
      return
    }
    // error: empty password
    if (!this.state.password) {
      this.setState({ error: { password: 'Password is required' } })
      return
    }

    loginCustomer(this.state.username, this.state.password)
      .then(this.handleClose)
      // error: user does not exist (status 404)
      .catch((err) => {
        // TODO: check if error message is ok
        this.setState({
          username: '',
          password: '',
          error: { username: 'User not found' }
        })
      })
  }

  handleLogout = () => {
    const { logoutCustomer } = this.props;
    logoutCustomer()
  }

  renderForm() {
    return (
      <div>
        <div className='loginTitle'> Sign in to your account</div>
        <div className='userInput'>
          <TextField
            errorText={this.state.error.username}
            value={this.state.username}
            hintText="Username"
            onChange={this.handleUsername}
          />
        </div>
        <div className='loginInput'>
          <TextField
            type="password"
            value={this.state.password}
            hintText="Password"
            errorText={this.state.error.password}
            onChange={this.handlePass}
          />
        </div>
        <FlatButton
          onClick={this.handleLogin}
          label="Login"
          fullWidth={true}
        />
      </div>
    )
  }

  renderLogin() {
    const { isActive } = this.props
    return (
      <Modal
        isOpen={this.state.open && !isActive}
        onRequestClose={this.handleClose}
        style={customStyles}
        contentLabel="Create User"
      >
        {this.renderForm()}
      </Modal>
    )
  }

  renderActive() {
    return (
      <div>
        <FlatButton
          onClick={this.handleLogout}
          label="Logout"
        />
      </div>
    )
  }

  render() {
    const { isActive } = this.props
    return (
      <div>
        {isActive ?
          this.renderActive()
          : <FlatButton onClick={this.handleOpen} label="Login" />
        }
        {this.renderLogin()}
      </div>
    )
  }
}

Login.propTypes = {
  loginCustomer: PropTypes.func,
  logoutCustomer: PropTypes.func,
  isActive: PropTypes.bool
}

export default Login 