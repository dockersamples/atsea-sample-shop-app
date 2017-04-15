import React, { PropTypes, Component } from 'react';
import { Link } from 'react-router';
import { Field, reduxForm } from 'redux-form';
import { TextField, FlatButton } from 'material-ui';
import Input from './Input';
import './CustomerInfoForm.css';

class CustomerInfoForm extends Component {

  renderCredit() {
    return (
      <div>
        <div className='formHeader'>Credit Card Information</div>
          <div className='formRow'>
          <Field 
            name="firstName"
            component={firstName=>
              <Input field={firstName} hintText={"First Name"} /> 
            }
         />
          <Field 
            name="lastName"
            component={lastName=>
              <Input field={lastName} hintText={"Last Name"} /> 
            }
         />
         </div>
          <div className='formRow'>
          <Field 
            name="cardNumber"
            component={cardNumber=>
              <Input field={cardNumber} hintText={"Card Number"} /> 
            }
         />
          <Field 
            name="cvv"
            component={cvv=> 
              <Input field={cvv} hintText={"CVV"} /> 
            }
         />
          <Field 
            name="expirationDate"
            component={date=> 
              <Input field={date} hintText={"Date"} /> 
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
          component={company=> 
            <Input field={company} hintText={"Company"} /> 
          }
        />
      <Field 
        name="title"
        component={title=> 
            <Input field={title} hintText={"Title"} /> 
        }
      />
      </div>
        <div className='infoRow'>
          <Field 
            name="address"
            component={address=> 
              <Input field={address} hintText={"Address"} /> 
            }
          />
          <Field 
            name="city"
            component={city=> 
              <Input field={city} hintText={"City"} /> 
            }
          />
        </div>
      </div>
    );
  }

  renderButtons() {
    return(
      <div className='infoButton'>
        <FlatButton
          label="Continue Shopping"
          containerElement={<Link to="/" />}
        />
        <FlatButton
          label="Complete Order"
          type="submit"
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
          {this.renderCredit()}
          {this.renderBilling()}
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
