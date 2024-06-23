import axios from "axios";
import { useState, FormEvent } from "react";
import "../../css/loginform.css";
import NavBar from "../../components/navbars/ShopOwnerNavbar";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import { useNavigate } from "react-router-dom";


const CreateShopForm = () => {
  const [name, setName] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [error, setError] = useState<string>("");

  const navigate = useNavigate();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const ownerId = Cookies.get("userId");
    const token = Cookies.get("token");

    try {
      const response = await axios.post(
        "https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/createShop",
        { 
          name, 
          description, 
          ownerId 
        }
        ,
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
      console.log(response.data);
      navigate('/homepageShopOwner'); // Redirect to /homepageShopOwner
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