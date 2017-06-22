import React, { PropTypes } from 'react'
import { connect } from 'react-redux'
import {
  getTotalProducts,
  itemJustAddedSelector,
} from '../reducers'

import Title from '../components/Title'

const TitleContainer = ({ totalProducts, showItemAdded }) => (
  <Title
    totalProducts={totalProducts}
    showItemAdded={showItemAdded}
   />
)

TitleContainer.propTypes = {
  totalProducts: PropTypes.number,
  showItemAdded: PropTypes.bool,
}

const mapStateToProps = (state) => ({
  totalProducts: getTotalProducts(state),
  showItemAdded: itemJustAddedSelector(state),
})

export default connect(mapStateToProps)(TitleContainer)
