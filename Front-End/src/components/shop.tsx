import '../css/component/shopComponent.css'

type ShopProps = {
  name: string;
  description: string;
};

const Shop = ({ name, description }: ShopProps) => (
  <div className="shop-container">
    <h2 className="shop-header">Name: {name}</h2>
    <p className="shop-description">Description: {description}</p>
    <div className="button">Learn More</div>
  </div>
);

export default Shop;