import React, { Component, PropTypes } from 'react';
import { connect } from 'react-redux';
import {
  createCustomer,
  loginCustomer,
} from '../../actions';
import {
  getIP,
  getHost,
} from '../../reducers';
import LoginForm from '../LoginForm';
import CreateUserForm from '../CreateUserForm';
import SuccessMessage from '../SuccessMessage';
import FlatButton from 'material-ui/FlatButton';
import Modal from 'react-modal';
import Logo from '../Logo';
import './styles.css';
import '../globalStyles.css';
import {
  getJwtToken,
  removeJwtToken,
  setJwtToken,
} from '../../actions/storage';
import { SubmissionError } from 'redux-form'

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
      authenticated: (getJwtToken() !== null),
      loginSuccessful: false,
      createUserSuccessful: false,
    };
  }

  handleLoginSuccess = ({ value: { token } }, username) => {
    setJwtToken(token);
    this.setState({ authenticated: true });
    this.setState({ loginSuccessful: true });
  };

  handleCreateUserSuccess(username, password) {
    const { loginCustomer } = this.props;
    this.setState({ createUserSuccessful: true });

    // temporary sleep so that login will work
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
      if (new Date().getTime() - start > 1000) {
        break;
      }
    }

    return loginCustomer(username, password)
      .then((response) => {
        this.handleLoginSuccess(response, username)
      })
      .catch(err => {
        throw new SubmissionError({ _error: "Error logging in." })
      });
  }

  handleCreateUser = values => {
    const {
      username,
      password,
    } = values;
    const { createCustomer } = this.props;
    return createCustomer(username, password)
      .then((response) => {
        this.handleCreateUserSuccess(username, password)
      })
      .catch(err => {
        throw new SubmissionError({ username: "Username already exists" })
      });
  };

  handleLogin = values => {
    const {
      username,
      password,
    } = values;
    const { loginCustomer } = this.props;
    return loginCustomer(username, password)
      .then((response) => {
        this.handleLoginSuccess(response, username)
        this.toggleLoginModal();
      })
      .catch(err => {
        throw new SubmissionError({ _error: "Error logging in." })
      });
  };

  renderContainerId() {
    const {ip, host} = this.props;
    return (
      <div className="containerSection">
        {`IP: ${ip} HOST: ${host}`}
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
      : <CreateUserForm onSubmit={this.handleCreateUser} onSubmitFail={this.handleSubmitFail} />;
    return (
      <Modal
        isOpen={this.state.isCreateModalOpen}
        onRequestClose={this.toggleCreateModal}
        style={customStyles}
        contentLabel={''}
      >
        <div className="formContainer">
          {content}
        </div>
      </Modal>
    );
  };

  renderLoginModal = () => {
    return (
      <Modal
        isOpen={this.state.isLoginModalOpen}
        onRequestClose={this.toggleLoginModal}
        style={customStyles}
        contentLabel={''}
      >
        <div className="formContainer">
          <LoginForm onSubmit={this.handleLogin} />
        </div>
      </Modal>
    );
  };

  renderUnauthenticated() {
    const styles = {
      color: '#fff',
    };
    const labelStyles = {
      textTransform: 'none',
      fontFamily: 'Open Sans',
      fontWeight: 600,
    };

    return (
      <div>
        <FlatButton
          style={styles}
          labelStyle={labelStyles}
          onClick={this.toggleCreateModal}
          label="Create User"
        />
        <FlatButton
          style={styles}
          labelStyle={labelStyles}
          onClick={this.toggleLoginModal}
          label="Sign in"
        />
      </div>
    );
  }

  renderAuthenticated() {
    const styles = {
      color: '#fff'
    };
    const labelStyles = {
      textTransform: 'none',
      fontFamily: 'Open Sans',
      fontWeight: 600,
    };
    const welcome = 'Welcome!'
    return (
      <div>
        <span className="welcomeMessage">
          {welcome}
        </span>
        <FlatButton
          style={styles}
          labelStyle={labelStyles}
          onClick={this.removeToken}
          label="Sign out"
        />
      </div>
    );
  }

  removeToken = () => {
    removeJwtToken();
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
            <div className="buttonSection">
              {this.state.authenticated
                ? this.renderAuthenticated()
                : this.renderUnauthenticated()}
            </div>
            {this.renderCreateModal()}
            {this.renderLoginModal()}
          </div>
        </div>
      </div>
    );
  }
}

TopNav.propTypes = {
  ip: PropTypes.string.isRequired,
  host: PropTypes.string.isRequired,
  createCustomer: PropTypes.func.isRequired,
  loginCustomer: PropTypes.func.isRequired,
};

const mapStateToProps = state => ({
  ip: getIP(state),
  host: getHost(state),
});

export default connect(mapStateToProps, {
  createCustomer,
  loginCustomer,
})(TopNav);
