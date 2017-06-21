import React from 'react';
import './styles.css'

const Header = () => {
  const title = 'Welcome to the atsea shop'
  const subtitle = ''
  return (
    <div className='headerSection'>
      <div className='headerTitle'>
        {title}
      </div>
      <div className='headerSubtitle'>
        {subtitle}
      </div>
    </div>
  );
}

export default Header;
