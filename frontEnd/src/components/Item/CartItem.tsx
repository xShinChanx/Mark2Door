import '../../css/component/itemComponent.css'

type ItemProps = {
  name: string;
  description: string;
  itemID: number;
  userID: number;
  inCart: boolean; // Indicates whether the item is already in cart
};

const Item = ({ name, description, itemID, userID, inCart }: ItemProps) => {

  const removeFromCart = async () => {
    try {
      const response = await fetch('http://localhost:8886/cart/deleteItemFromCart', {
        method: 'DELETE',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userID: userID,
          itemID: itemID,
        }),
      });
      if (response.ok) {
        alert('Item removed from cart successfully');
        // Optionally, you may want to update the UI after successful removal
      } else {
        console.error('Failed to remove item from cart');
        alert('Failed to remove item from cart');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('Error occurred while removing item from cart');
    }
  };

  return (
    <div className="item-container">
      <h2 className="item-header">Name: {name}</h2>
      <p className="item-description">Description: {description}</p>
      {inCart && (
        <div className="remove-button" onClick={removeFromCart}>Remove From Cart</div>
      )}
    </div>
  );
};

export default Item;