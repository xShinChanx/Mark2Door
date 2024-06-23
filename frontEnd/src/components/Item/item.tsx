import '../../css/component/itemComponent.css'
import axios from "axios";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie


type ItemProps = {
  name: string;
  description: string;
  itemID: number;
  userID: number;  // Ensure this is a number
};

const Item = ({ name, description, itemID, userID }: ItemProps) => {
  const addToCart = async () => {
    try {
      const token = Cookies.get("token");
  
      const response = await axios.post(
        'https://new-gateway-6jhcj4ol.ew.gateway.dev/cart/addItemToCart',
        {
          userID: userID,
          itemID: itemID,
        },
        {
          headers: {
            'Content-Type': 'application/json',
            Authorization: `Bearer ${token}`,
          },
        }
      );
  
      if (response.status === 200) {
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
