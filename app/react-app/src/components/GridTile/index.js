import React, { Component, PropTypes } from 'react'
import FlatButton from 'material-ui/FlatButton'
import AddIcon from './AddIcon'
import './styles.css'

export default class GridTile extends Component {
  addToCart = () => {
    const { onAddToCartClicked, productId } = this.props
    onAddToCartClicked(productId)
  }

  render() {
    const { price, name, description, image } = this.props
    const image2 = (
      <img
        alt="Logo"
        src={process.env.PUBLIC_URL + image}
      />
    )
    return (
      <div className="tile">
        <div className="tileImage">
          {image2}
        </div>
        <div className="tileTitle">
          {name}
        </div>
        <div className="tileDescription">
          {description}
        </div>
        <div className="titleBottom">
          <div className="tilePrice">
            {price}
          </div>
          <div className="tileAdd">
            <FlatButton
              onClick={this.addToCart}
              labelStyle={{ color: "#099CEC" }}
              label="Add"
              labelPosition="before"
              icon={<AddIcon />}
            />
          </div>
        </div>
      </div>
    )
  }
}

GridTile.propTypes = {
  productId: PropTypes.number,
  price: PropTypes.string,
  name: PropTypes.string,
  description: PropTypes.string,
  image: PropTypes.string,
  onAddToCartClicked: PropTypes.func.isRequired
}
