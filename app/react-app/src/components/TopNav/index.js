import React, { Component, PropTypes } from 'react'
import { connect } from 'react-redux'
import {
    createCustomer,
    loginCustomer,
} from '../../actions'
import {
    isActive,
    getContainerId,
    getHost,
} from '../../reducers'
import LoginForm from '../LoginForm'
import CreateUserForm from '../CreateUserForm'
import FlatButton from 'material-ui/FlatButton'
import Modal from 'react-modal'
import Logo from '../Logo'
import './styles.css'
import '../globalStyles.css'


const customStyles = {
  overlay : {
    position          : 'fixed',
    top               : 0,
    left              : 0,
    right             : 0,
    bottom            : 0,
    backgroundColor: 'rgba(255, 255, 255, 0.97)',
    boxShadow: '0 2px 4px 0 rgba(0, 0, 0, 0.27)',
    height : '100%',
    width : '100%'
  },
  content : {
    top                   : '30%',
    left                  : '50%',
    right                 : 'auto',
    bottom                : 'auto',
    marginRight           : '-50%',
    transform             : 'translate(-50%, -50%)',
    border: '0x',
  }
}

class TopNav extends Component {
    constructor(props) {
        super(props)
        this.state = {
            isCreateModalOpen: false,
            isLoginModalOpen: false,
            authenticated: false,
            loginSuccessful: false,
            user: {},
        }
    }

    handleCreateSuccess = () => {

    }

    handleLoginSuccess = ({ value: { token } }) => {
        console.log('success!')
        localStorage.setItem("jwtToken", token)
        this.setState({ authenticated: true})
        this.setState({ loginSuccessful: true})
    }

    handleCreateUser = (values) => {
        const {
            username,
            password,
        } = values;
        const { createCustomer } = this.props;
        createCustomer(username, password)
          .then(this.handleCreateSuccess)
          .catch((err) => {
            console.log('error creating the customer')
          })
    }

    handleLogin = (values) => {
        const {
            username,
            password,
        } = values;
        const { loginCustomer } = this.props;
        console.log('logging in')
        loginCustomer(username, password)
          .then(this.handleLoginSuccess)
          .catch((err) => {
            console.log('error loggging in the customer')
          })
    }

    renderContainerId() {
        const { containerId, host } = this.props
        // const version = 12345678
        return (
        <div className='containerSection'>
            {/*{ `ID: ${containerId} Version: ${version}` }*/}
            { `IP: ${containerId} HOST: ${host}` }
        </div>
        )
    }

    makeCreateButton() {
        return (
            <FlatButton
                onClick={this.toggleCreateModal}
                label="Create User"
            />
        )
    }

    makeLoginButton() {
        return (
            <FlatButton
                onClick={this.toggleLoginModal}
                label="Login"
            />
        )
    }

    toggleCreateModal = () => {
        this.setState({
            isCreateModalOpen: !this.state.isCreateModalOpen,
        })
    }

    toggleLoginModal = () => {
        this.setState({
            isLoginModalOpen: !this.state.isLoginModalOpen,
        })
    }
    
    renderCreateModal = () => {
        return (
            <Modal
                isOpen={this.state.isCreateModalOpen}
                onRequestClose={this.toggleCreateModal}
                style={customStyles}
            >
            <div className='formContainer'>
            <CreateUserForm onSubmit={this.handleCreateUser} />
            </div>
            </Modal>
        )
    }

    renderLoginModal = () => {
        return (
            <Modal
                isOpen={this.state.isLoginModalOpen}
                onRequestClose={this.toggleLoginModal}
                style={customStyles}
            >
            <div className='formContainer'>
            <LoginForm onSubmit={this.handleLogin} />
            </div>
            </Modal>

        )
    }

    renderUnauthenticated() {
        return (
            // <div className='inauthenticated'>
            <div>
                {this.makeLoginButton()}
                {this.makeCreateButton()}
                {this.renderCreateModal()}
                {this.renderLoginModal()}
            </div>
        )
    }

    renderAuthenticated() {
        return (
            <div className='authenticated'>

            </div>
        )
    }

    render() {
        return (
            <div className='globalContainer'>
                <div className='navHeader'>
                    <div className='navLogo'>
                        <Logo />
                    </div>
                    <div className='navUser'>
                    { this.renderContainerId()}
                    { this.state.authenticated ? this.renderAuthenticated() : this.renderUnauthenticated()}
                    </div>
                </div>
            </div>
        )
    }
}

const mapStateToProps = state => ({
    isActive: isActive(state),
    containerId: getContainerId(state),
    host: getHost(state),
})


export default connect(
  mapStateToProps,
  { createCustomer, loginCustomer }
)(TopNav)
