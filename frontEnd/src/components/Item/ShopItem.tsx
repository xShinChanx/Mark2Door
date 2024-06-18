import '../../css/component/itemComponent.css'

type ItemProps = {
    name: string;
    description: string;
    price: number;
};

const Item = ({ name, description, price }: ItemProps) => (
  <div className="item-container">
    <h2 className="item-header">Name: {name}</h2>
    <p className="item-description">Description: {description}</p>
    <p className="item-count">Price: {price}</p>
    <div className="remove-button">Delete Item from Shop</div>
  </div>
);

export default Item