import React from 'react'
import ProductsContainer from './ProductsContainer'
import CartContainer from './CartContainer'
import CustomerContainer from './CustomerContainer'
import LoginContainer from './LoginContainer'

const App = () => (
  <div>
    <h2>Shopping Cart Example</h2>
    <LoginContainer />
    <CustomerContainer />
    <hr/>
    <ProductsContainer />
    <hr/>
    <CartContainer />
  </div>
)

export default App
