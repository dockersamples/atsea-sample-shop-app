import React from 'react'
import GradientBackground from '../components/GradientBackground'
import Header from '../components/Header'
import ProductsContainer from './ProductsContainer'
import CustomerContainer from './CustomerContainer'
import LoginContainer from './LoginContainer'
import CartContainer from './CartContainer'

const App = () => (
  <div>
    <GradientBackground />
    <LoginContainer />
    <CustomerContainer />
    <Header />
    <CartContainer />
    <ProductsContainer />
  </div>
)

export default App
