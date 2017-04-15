import React, { PropTypes } from 'react'
import Cart from '../Cart'
import FlatButton from 'material-ui/FlatButton'
import { Link } from 'react-router'

import './styles.css'
import '../globalStyles.css'

const Title = ({ products, total, totalProducts }) => (
    <div className='globalContainer'>
        <div className='titleBar'>
            <div className='productsSection'>
                Products
            </div>
             <div className="checkout-button">
                <FlatButton
                    label="Checkout"
                    containerElement={<Link to="checkout"> Checkout </Link>}
                />
            </div>
            <Cart
                products={products}
                total={total}
                totalProducts={totalProducts}   
            />
        </div>
    </div>
)

// Title.propTypes = {
//     products: PropTypes.array,
//     total: PropTypes.string,
//     totalProducts: PropTypes.string,
// }


export default Title