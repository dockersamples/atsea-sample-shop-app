import React, { PropTypes, Component } from 'react';
import { Link } from 'react-router';
import { Field, reduxForm } from 'redux-form';
import { TextField, FlatButton } from 'material-ui';
import Input from '../Input';
import './styles.css';

class LoginForm extends Component {

  renderLogin() {
    return (
      <div>
        <div className='formHeader'>Sign in to your account</div>
          <div className='formRow'>
          <Field 
            name="username"
            component={username=>
              <Input field={username} hintText={"Username"} /> 
            }
         />
          <Field 
            name="password"
            component={password=>
              <Input field={password} hintText={"Password"} /> 
            }
         />
         </div>
      </div>
    );
  }

  renderButtons() {
    const { handleSubmit } = this.props
    return(
      <div className='formButton'>
        <FlatButton
          onClick={handleSubmit}
          label="Login"
        />
      </div>
    );
  }

  render() {
    const { 
      handleSubmit,
    } = this.props;

    return (
      <div>
        <form onSubmit={handleSubmit}>
          {this.renderLogin()}
          {this.renderButtons()}
        </form>
      </div>
    );
  }
}

LoginForm.propTypes = {
  handleSubmit: PropTypes.func.isRequired,
};

export default LoginForm = reduxForm({
  form: 'loginForm',
})(LoginForm);
