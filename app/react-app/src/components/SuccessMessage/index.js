import React, { PropTypes } from 'react'
import FlatButton from 'material-ui/FlatButton'
import './styles.css'

const SuccessMessage = ({ message, buttonLabel, handleClick }) => {
  return (
    <div className="successSection">
      <div className="successMessage">
        { message } 
      </div>
      <div className="successButton">
        <FlatButton
          label={buttonLabel}
          onClick={handleClick}
        />
      </div>
    </div>
  )
}

SuccessMessage.propTypes = {
  buttonLabel: PropTypes.string.isRequired,
  message: PropTypes.string.isRequired,
  handleClick: PropTypes.func.isRequired,
}

export default SuccessMessage
