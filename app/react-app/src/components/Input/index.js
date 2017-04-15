import React, { PropTypes } from 'react';
import { TextField } from 'material-ui';

const Input = ({ field, hintText }) => {
  return (
      <TextField
        hintText={hintText}
        errorText={field.touched && field.error}
        {...field.input}
      />
  );
}

Input.propTypes = {
  hintText: PropTypes.string,
  field: PropTypes.object,
}

export default Input
