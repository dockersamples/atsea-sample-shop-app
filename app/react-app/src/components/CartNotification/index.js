import React, {PropTypes} from 'react';
import {VelocityComponent} from 'velocity-react';

const CartNotification = ({showItemAdded}) => {
  const child = <div> Added to your cart </div>;
  return (
    <VelocityComponent
        animation={{opacity: showItemAdded ? 1 : 0}}
        duration={500}
    >
      {child}
    </VelocityComponent>
  );
};

CartNotification.propTypes = {
  showItemAdded: PropTypes.bool.isRequired,
};

CartNotification.defaultProps = {
  showItemAdded: false,
};

export default CartNotification;
