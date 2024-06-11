import '../css/component/itemComponent.css'

type ItemProps = {
    name: string;
    description: string;
};

const Item = ({ name, description }: ItemProps) => (
    <div className="item-container">
    <h2 className="item-header">Name: {name}</h2>
    <p className="item-description">Description: {description}</p>
    <div className="button">Add to cart</div>
  </div>
);

export default Item