import '../../css/component/itemComponent.css'

type ItemProps = {
  name: string;
  description: string;
  itemID: number;
  userID: number;  // Ensure this is a number
};

const Item = ({ name, description, itemID, userID }: ItemProps) => {
  const addToCart = async () => {
    const axios = require('axios');
    try {
      const response = await axios.post('https://new-gateway-6jhcj4ol.ew.gateway.dev/cart/addItemToCart', {
        // Include your request payload here
        userID: userID,
        itemID: itemID
      });
      if (response.ok) {
        alert('Item added to cart successfully');
      } else {
        console.error('Failed to add item to cart');
        alert('Failed to add item to cart');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error occurred while adding item to cart');
    }
  };

  return (
    <div className="item-container">
      <h2 className="item-header">Name: {name}</h2>
      <p className="item-description">Description: {description}</p>
      <div className="button" onClick={addToCart}>Add to cart</div>
    </div>
  );
};

export default Item;
