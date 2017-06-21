import React, { PropTypes, Component } from 'react';
import { Field, reduxForm } from 'redux-form';
import { FlatButton } from 'material-ui';
import Input from '../Input';
import './styles.css';
import validate from './validate.js';

class CreateUserForm extends Component {

  renderCreateUser() {
    const header = 'Create your user ID'
    return (
      <div>
        <div className='createFormHeader'>
          {header}
        </div>
        <div className='createFormRow'>
          <Field
            name="username"
            component={username =>
              <Input field={username} hintText={"Choose a user ID"} />
            }
          />
          <Field
            name="password"
            component={password =>
              <Input type={"password"} field={password} hintText={"Choose a password"} />
            }
          />
        </div>
      </div>
    );
  }

  renderButtons() {
    const { handleSubmit } = this.props
    const labelStyles = {
      textTransform: 'none',
      fontFamily: 'Open Sans',
      fontWeight: 600,
    };
    const styles = {
      color: '#fff',
      backgroundColor: '#099CEC',
    };

    return (
      <div className='createFormButton'>
        <FlatButton
          label="Sign up"
          onClick={handleSubmit}
          style={styles}
          labelStyle={labelStyles}
        />
      </div>
    );
  }

  render() {
    const {
      handleSubmit,
    } = this.props;

    return (
      <div className='createFormContent'>
        <form onSubmit={handleSubmit}>
          {this.renderCreateUser()}
          {this.renderButtons()}
        </form>
      </div>
    );
  }
}

CreateUserForm.propTypes = {
  handleSubmit: PropTypes.func.isRequired,
};

export default CreateUserForm = reduxForm({
  form: 'createUserForm',
  validate,
})(CreateUserForm);
