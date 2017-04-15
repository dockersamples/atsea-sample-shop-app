import React, { PropTypes } from 'react'
import FlatButton from 'material-ui/FlatButton'
import './styles.css'

const SuccessMessage = ({ message, label, handleClick, containerElement }) => {
  return (
    <div className="successContainer">
      <div className="successMessage">
        { message } 
      </div>
      <div className="successButton">
        <FlatButton
          label={label}
          onClick={handleClick}
          containerElement={containerElement}
        />
      </div>
    </div>
  )
}

SuccessMessage.propTypes = {
  label: PropTypes.string.isRequired,
  message: PropTypes.string.isRequired,
  handleClick: PropTypes.func,
  containerElement: PropTypes.func,
}

export default SuccessMessage
