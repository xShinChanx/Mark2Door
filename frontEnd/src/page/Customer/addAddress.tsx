import axios from "axios";
import { useState, FormEvent } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import "../../css/loginform.css";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import NavBar from "../../components/navbars/CustomerNavbar"



const AddAddressPage = () => {
  const [address, setAddress] = useState<string>("");
  const [city, setCity] = useState<string>("");
  const [houseNo, setHouseNo] = useState<string>("");
  const [error, setError] = useState<string>("");
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const userID = Cookies.get("userId");


  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const token = Cookies.get("token");
    
      const response = await axios.post(
        "https://new-gateway-6jhcj4ol.ew.gateway.dev/user/create",
        {
          address,
          city,
          houseNo,
          userID
        },
        {
          headers: {
            Authorization: `Bearer ${token}`
          }
        }
      );
    
      console.log(response.data);
    
      // Redirect to profile page or another appropriate page after successful address addition
      navigate("/profile");
    } catch (error) {
      // Handle error
      console.error('Error adding address:', error);
    }
  };

  return (
    <div>
    <NavBar /> 
      <div className="login-container">
        <form className="login-form" onSubmit={handleSubmit}>
          <h1>Add Address</h1>
          {error && <p>{error}</p>}

          <input
            type="text"
            placeholder="Address"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />

          <input
            type="text"
            placeholder="City"
            value={city}
            onChange={(e) => setCity(e.target.value)}
          />

          <input
            type="text"
            placeholder="House No"
            value={houseNo}
            onChange={(e) => setHouseNo(e.target.value)}
          />

          <button type="submit">Add Address</button>
        </form>
      </div>
    </div>
  );
};

export default AddAddressPage;