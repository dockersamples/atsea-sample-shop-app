import React from 'react'
import GradientBackground from '../components/GradientBackground'
import TopNav from '../components/TopNav'
import Footer from '../components/Footer'
import Header from '../components/Header'
import TitleContainer from './TitleContainer'
import ProductsContainer from './ProductsContainer'

const App = () => (
  <div>
    <GradientBackground />
    <TopNav />
    <Header />
    <TitleContainer />
    <ProductsContainer />
    <Footer />
  </div>
)

export default App
