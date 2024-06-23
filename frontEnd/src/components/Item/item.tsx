import '../../css/component/itemComponent.css'
import axios from "axios";


type ItemProps = {
  name: string;
  description: string;
  itemID: number;
  userID: number;  // Ensure this is a number
};

const Item = ({ name, description, itemID, userID }: ItemProps) => {
  const addToCart = async () => {
    try {
      const response = await axios.post(
        'https://cart-service-jl4ebnk3lq-ez.a.run.app/cart/addItemToCart', 
        { 
          userID: userID, 
          itemID: itemID 
        }, 
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      );
      console.log('Response:', response.data);
    } catch (error) {
      console.error('Error adding item to cart:', error);
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
