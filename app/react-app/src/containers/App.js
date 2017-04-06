import React from 'react'
import ProductsContainer from './ProductsContainer'
import CustomerContainer from './CustomerContainer'
import LoginContainer from './LoginContainer'
import CartContainer from './CartContainer'

const App = () => (
  <div>
    <h2>Docker Art Store</h2>
    <LoginContainer />
    <CustomerContainer />
    <CartContainer />
    <ProductsContainer />
  </div>
)

export default App
