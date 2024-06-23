import '../../css/component/itemComponent.css'

type ItemProps = {
  name: string;
  description: string;
  itemID: number;
  userID: number;  // Ensure this is a number
};

const Item = ({ name, description}: ItemProps) => {
  return (
    <div className="item-container">
      <h2 className="item-header">Name: {name}</h2>
      <p className="item-description">Description: {description}</p>
    </div>
  );
};

export default Item;
