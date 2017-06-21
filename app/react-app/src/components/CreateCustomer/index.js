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

class CreateCustomer extends Component {
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
    this.setState({ username: '', password: '', open: false, error: { username: '', password: '' } })
  }

  handleCreate = () => {
    const { createCustomer } = this.props;

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

    //TODO: Jess should check the error messaging and other api responses
    createCustomer(this.state.username, this.state.password)
      .then(this.handleClose)
      // error: user already exists
      .catch((err) => {
        this.setState({ username: '', password: '', error: { username: 'Username already exists' } })
      })
  }

  renderForm() {
    return (
      <div>
        <div className='userTitle'> Create your Username </div>
        <div className='userInput'>
          <TextField
            value={this.state.username}
            hintText="Choose a Username"
            errorText={this.state.error.username}
            onChange={this.handleUsername}
          />
        </div>
        <div className='userInput'>
          <TextField
            type="password"
            value={this.state.password}
            hintText="Choose a Password"
            errorText={this.state.error.password}
            onChange={this.handlePass}
          />
        </div>
        <FlatButton
          label="Sign up"
          onClick={this.handleCreate}
          fullWidth={true}
        />
      </div>
    )
  }

  renderModal() {
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

  render() {
    const { isActive } = this.props
    return (
      <div>
        {!isActive ?
          <FlatButton onClick={this.handleOpen} label="Create Account" />
          : <div />
        }
        {this.renderModal()}
      </div>
    )
  }
}

CreateCustomer.propTypes = {
  createCustomer: PropTypes.func,
  isActive: PropTypes.bool
}


export default CreateCustomer;