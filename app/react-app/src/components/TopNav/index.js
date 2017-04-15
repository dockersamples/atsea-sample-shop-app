import React, {Component, PropTypes} from 'react';
import {connect} from 'react-redux';
import {createCustomer, loginCustomer} from '../../actions';
import {isActive, getContainerId, getHost} from '../../reducers';
import LoginForm from '../LoginForm';
import CreateUserForm from '../CreateUserForm';
import SuccessMessage from '../SuccessMessage';
import FlatButton from 'material-ui/FlatButton';
import Modal from 'react-modal';
import Logo from '../Logo';
import './styles.css';
import '../globalStyles.css';
import getJwtToken from '../../actions/getJwtToken'

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
    width: '100%',
  },
  content: {
    top: '30%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
    border: '0x',
  },
};

class TopNav extends Component {
  constructor(props) {
    super(props);
    this.state = {
      isCreateModalOpen: false,
      isLoginModalOpen: false,
      authenticated: (getJwtToken() !== null) ,
      loginSuccessful: false,
      createUserSuccessful: false,
      user: {},
    };
  }

  handleLoginSuccess = ({value: {token}}) => {
    console.log('login success!');
    localStorage.setItem('jwtToken', token);
    this.setState({authenticated: true});
    this.setState({loginSuccessful: true});
  };

  handleCreateUserSuccess(username, password) {
    const {loginCustomer} = this.props;
    this.setState({createUserSuccessful: true});
    console.log('create user success');
    // temporary sleep so that login will work
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
      if (new Date().getTime() - start > 1000) {
        break;
      }
    }

    loginCustomer(username, password)
      .then(this.handleLoginSuccess)
      .catch(err => {
        console.log('error loggging in the customer');
      });
  }

  handleCreateUser = values => {
    const {
      username,
      password,
    } = values;
    const {createCustomer} = this.props;
    createCustomer(username, password)
      .then(this.handleCreateUserSuccess(username, password))
      .catch(err => {
        console.log('error creating the customer');
      });
  };

  handleLogin = values => {
    const {
      username,
      password,
    } = values;
    const {loginCustomer} = this.props;
    console.log('logging in');
    loginCustomer(username, password)
      .then(this.handleLoginSuccess)
      .catch(err => {
        console.log('error loggging in the customer');
      });
  };

  renderContainerId() {
    const {containerId, host} = this.props;
    return (
      <div className="containerSection">
        {`IP: ${containerId} HOST: ${host}`}
      </div>
    );
  }

  toggleCreateModal = () => {
    this.setState({
      isCreateModalOpen: !this.state.isCreateModalOpen,
    });
  };

  toggleLoginModal = () => {
    this.setState({
      isLoginModalOpen: !this.state.isLoginModalOpen,
    });
  };

  renderCreateModal = () => {
    const successMessage = 'Congratulations! Your account has been created!';
    const content = this.state.createUserSuccessful
      ? <SuccessMessage
          message={successMessage}
          label={'Continue Shopping'}
          handleClick={this.toggleCreateModal}
        />
      : <CreateUserForm onSubmit={this.handleCreateUser} />;
    return (
      <Modal
        isOpen={this.state.isCreateModalOpen}
        onRequestClose={this.toggleCreateModal}
        style={customStyles}
      >
        {content}
        <div className="formContainer" />
      </Modal>
    );
  };

  renderLoginModal = () => {
    return (
      <Modal
        isOpen={this.state.isLoginModalOpen}
        onRequestClose={this.toggleLoginModal}
        style={customStyles}
      >
        <div className="formContainer">
          <LoginForm onSubmit={this.handleLogin} />
        </div>
      </Modal>
    );
  };

  renderUnauthenticated() {
    const styles = {
        color: '#fff'
    };

    return (
        <div>
          <FlatButton style={styles} onClick={this.toggleCreateModal} label="Create User" />
          <FlatButton style={styles} onClick={this.toggleLoginModal} label="Login" />
          {this.renderCreateModal()}
          {this.renderLoginModal()}
        </div>
    );
  }

  renderAuthenticated() {
    return (
      <div>
        <span className="welcomeMessage">
          Welcome!
        </span>
        <FlatButton onClick={this.removeToken} label="Logout" />
      </div>
    );
  }

  removeToken = () => {
    localStorage.removeItem("jwtToken");
    this.setState({
      isCreateModalOpen: false,
      isLoginModalOpen: false,
      authenticated: false,
      loginSuccessful: false,
      createUserSuccessful: false,
    });
  };

  render() {
    return (
      <div className="globalContainer">
        <div className="navHeader">
          <div className="navLogo">
            <Logo />
          </div>
          <div className="navUser">
            {this.renderContainerId()}
            {this.state.authenticated
              ? this.renderAuthenticated()
              : this.renderUnauthenticated()}
          </div>
        </div>
      </div>
    );
  }
}

const mapStateToProps = state => ({
  isActive: isActive(state),
  containerId: getContainerId(state),
  host: getHost(state),
});

export default connect(mapStateToProps, {createCustomer, loginCustomer})(
  TopNav,
);
