type ShopProps = {
  name: string;
  description: string;
};

const Shop = ({ name, description }: ShopProps) => (
  <div>
    <h2>Name: {name}</h2>
    <p>Description: {description}</p>
  </div>
);

export default Shop;
