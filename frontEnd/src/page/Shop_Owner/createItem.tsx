import axios from "axios";
import { useEffect, useState, FormEvent } from "react";
import "../../css/loginform.css";
import NavBar from "../../components/navbars/ShopOwnerNavbar";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import { useNavigate } from 'react-router-dom'; // Import useNavigate


const CreateItemForm = () => {
  const [name, setName] = useState<string>("");
  const [description, setDescription] = useState<string>("");
  const [price, setPrice] = useState<number>(0.0);
  const [error, setError] = useState<string>("");
  const [shopId, setShopId] = useState<number | null>(null); // State to hold the shopId
  const navigate = useNavigate(); // Hook for navigation

  const token = Cookies.get("token");

  useEffect(() => {
    const fetchShopId = async () => {
      const userId = Cookies.get("userId");

      try {
        const response = await fetch(`https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/shopId/${userId}`
        //   , {
        //   headers: {
        //     Authorization: `Bearer ${token}`
        //   }
        // }
      );
      
        if (response.ok) {
          const data = await response.json();
          if (typeof data === 'number') {
            setShopId(data);
          } else {
            console.log("Invalid shopId format");
          }
        } else {
          console.error('Failed to fetch shopId');
        }
      } catch (error) {
        console.error('Error fetching shopId:', error);
      }
    };

    fetchShopId();
  }, []);

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "https://shop-service-jl4ebnk3lq-ez.a.run.app/shop/item",
        { name, description, price, shopId }
        // ,
        // {
        //   headers: {
        //     Authorization: `Bearer ${token}`
        //   }
        // }
      );
      console.log(response.data);
      // Redirect to /myshop after successful item creation
      navigate('/myshop');
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