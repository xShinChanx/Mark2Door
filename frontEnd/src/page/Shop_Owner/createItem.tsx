import axios from "axios";
import { useState, FormEvent } from "react";
import "../../css/loginform.css";
import NavBar from "../../components/navbar";

const CreateItemForm = () => {
  const [name, setName] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [price, setPrice] = useState<number>(0.0);
  const [error, setError] = useState<string>("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const shopId = 1; // Hard-coded shop ID
    try {
        const response = await axios.post("http://localhost:8085/shop/item", { name, description, price, shopId });
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
          <h1>Create Item</h1>
          {error && <p>{error}</p>}

          <input
            type="text"
            placeholder="Item Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          
          <input
            type="text"
            placeholder="Description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          
          <input
            type="number"
            placeholder="Price"
            value={price}
            onChange={(e) => setPrice(parseFloat(e.target.value))}
          />
          
          <button type="submit">Create Item</button>
        </form>
      </div>
    </div>
  );
};

export default CreateItemForm;