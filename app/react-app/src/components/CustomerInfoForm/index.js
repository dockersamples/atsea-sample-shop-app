import React, { PropTypes, Component } from 'react';
import { Link } from 'react-router';
import { Field, reduxForm } from 'redux-form';
import { FlatButton } from 'material-ui';
import Input from '../../components/Input';
import './styles.css';

class CustomerInfoForm extends Component {

  renderCredit() {
    return (
      <div>
        <div className='infoHeader'>Credit Card Information</div>
        <div className='infoRow'>
          <Field
            name="firstName"
            component={firstName =>
              <Input field={firstName} hintText={"First Name"} />
            }
          />
          <Field
            name="lastName"
            component={lastName =>
              <Input field={lastName} hintText={"Last Name"} />
            }
          />
        </div>
        <div className='infoRow'>
          <Field
            name="cardNumber"
            component={cardNumber =>
              <Input field={cardNumber} hintText={"Card Number"} />
            }
          />
          <Field
            name="cvv"
            component={cvv =>
              <Input field={cvv} hintText={"CVV"} />
            }
          />
        </div>
        <div className='infoRow'>
          <Field
            name="expirationDate"
            component={date =>
              <Input field={date} hintText={"MM/YY"} />
            }
          />
        </div>
      </div>
    );
  }

  renderBilling() {
    return (
      <div>
        <div className='infoHeader'>Billing Information</div>
        <div className='infoRow'>
          <Field
            name="company"
            component={company =>
              <Input field={company} hintText={"Company"} />
            }
          />
          <Field
            name="title"
            component={title =>
              <Input field={title} hintText={"Title"} />
            }
          />
        </div>
        <div className='infoRow'>
          <Field
            name="address"
            component={address =>
              <Input field={address} hintText={"Address"} />
            }
          />
          <Field
            name="city"
            component={city =>
              <Input field={city} hintText={"City"} />
            }
          />
        </div>
      </div>
    );
  }

  renderButtons() {
    const labelStyles = {
      textTransform: 'none',
      fontFamily: 'Open Sans',
      fontWeight: 600,
    };
    return (
      <div className='infoButton'>
        <FlatButton
          label="Continue Shopping"
          containerElement={<Link to="/" />}
          style={{
            color: '#099CEC',
          }}
          labelStyle={labelStyles}
        />
        <FlatButton
          label="Complete Order"
          type="submit"
          style={{
            color: '#fff',
            backgroundColor: '#099CEC',
          }}
          labelStyle={labelStyles}
        />
      </div>
    );
  }

  render() {
    const {
      handleSubmit,
      error,
    } = this.props;
    const err = error ? <span className='loginErrorMessage'>{error}</span> : null

    return (
      <div className="infoSection">
        <form onSubmit={handleSubmit}>
          {this.renderCredit()}
          {this.renderBilling()}
          {err}
          {this.renderButtons()}
        </form>
      </div>
    );
  }
}

CustomerInfoForm.propTypes = {
  handleSubmit: PropTypes.func.isRequired,
};

export default CustomerInfoForm = reduxForm({
  form: 'customerInfo',
})(CustomerInfoForm);
