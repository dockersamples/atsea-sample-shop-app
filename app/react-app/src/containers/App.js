import React from 'react'
import ProductsContainer from './ProductsContainer'
import CartContainer from './CartContainer'
import CustomerContainer from './CustomerContainer'

const App = () => (
  <div>
    <h2>Shopping Cart Example</h2>
    <CustomerContainer />
    <hr/>
    <ProductsContainer />
    <hr/>
    <CartContainer />
  </div>
)

export default App
