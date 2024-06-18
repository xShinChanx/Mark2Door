import axios from "axios";
import { useState, FormEvent } from "react";
import "../../css/loginform.css";
import NavBar from "../../components/navbars/ShopOwnerNavbar";

const CreateShopForm = () => {
  const [name, setName] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [error, setError] = useState<string>("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const ownerId = 1; // Hard-coded owner ID
    try {
        const response = await axios.post("http://localhost:8085/shop/createShop", { name, description, ownerId });
        console.log(response.data);
    } catch (err) {
      console.error(err);
      setError("An error occurred");
    }
  };

  return (
    <div>
      <NavBar /> 
      <div className="login-container">
        <form className="login-form" onSubmit={handleSubmit}>
          <h1>Create Shop</h1>
          {error && <p>{error}</p>}

          <input
            type="text"
            placeholder="Shop Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          
          <input
            type="text"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          
          <button type="submit">Create Shop</button>
        </form>
      </div>
    </div>
  );
};

export default CreateShopForm;