import '../../css/component/itemComponent.css'

type ItemProps = {
    name: string;
    description: string;
    count: number;
};

const Item = ({ name, description, count }: ItemProps) => (
    <div className="item-container">
    <h2 className="item-header">Name: {name}</h2>
    <p className="item-description">Description: {description}</p>
    <p className="item-count">Count: {count}</p>
    <div className="remove-button">Remove From Cart</div>
  </div>
);

export default Item