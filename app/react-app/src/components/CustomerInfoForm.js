import React, { PropTypes, Component } from 'react';
import { Link } from 'react-router';
import { Field, reduxForm } from 'redux-form';
import { TextField, FlatButton } from 'material-ui';
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
              <TextField
                hintText="First Name" 
                errorText={firstName.touched && firstName.error}
                {...firstName.input} 
              />} 
         />
          <Field 
            name="lastName"
            component={lastName=> 
              <TextField
                hintText="Last Name" 
                errorText={lastName.touched && lastName.error}
                {...lastName.input} 
              />} 
         />
         </div>
          <div className='formRow'>
          <Field 
            name="cardNumber"
            component={cardNumber=> 
              <TextField
                hintText="Card Number" 
                errorText={cardNumber.touched && cardNumber.error}
                {...cardNumber.input} 
              />} 
         />
          <Field 
            name="cvv"
            component={cvv=> 
              <TextField
                hintText="CVV" 
                errorText={cvv.touched && cvv.error}
                {...cvv.input} 
              />} 
         />
          <Field 
            name="expirationDate"
            component={date=> 
              <TextField
                hintText="Month / Year" 
                errorText={date.touched && date.error}
                {...date.input} 
              />} 
         />
        </div>
      </div>
    );
  }

  renderBilling() {
    return (
      <div>
        <div className='formHeader'>Billing Information</div>
        <div className='formRow'>
        <Field 
          name="company"
          component={company => 
            <TextField
              hintText="Company" 
              errorText={company.touched && company.error}
              {...company.input} 
            />} 
        />
      <Field 
        name="title"
        component={title=> 
          <TextField
            hintText="Title" 
            errorText={title.touched && title.error}
            {...title.input} 
          />} 
      />
      </div>
        <div className='formRow'>
          <Field 
            name="address"
            component={address=> 
              <TextField
                hintText="Address" 
                errorText={address.touched && address.error}
                {...address.input} 
              />} 
          />
          <Field 
            name="city"
            component={city=> 
              <TextField
                hintText="City" 
                errorText={city.touched && city.error}
                {...city.input} 
              />} 
          />
        </div>
      </div>
    );
  }

  renderButtons() {
    return(
      <div className='formButton'>
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
  fields: PropTypes.object.isRequired
};

export default CustomerInfoForm = reduxForm({
  form: 'customerInfo',
})(CustomerInfoForm);
