import axios from "axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../../css/profilepage.css"; // Adjust path as needed
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import NavBar from "../../components/navbars/CustomerNavbar"



interface UserData {
  address: string;
  houseNo: string;
}

function Profile() {
  const [address, setAddress] = useState<string>('');
  const [houseNo, setHouseNo] = useState<string>('');
  const navigate = useNavigate();

  const userId = Cookies.get("userId");


  useEffect(() => {
    getUser();
  }, []);

  async function getUser() {
    try {
      const response = await axios.get<UserData>(`https://new-gateway-6jhcj4ol.ew.gateway.dev/user/${userId}`);
      setAddress(response.data.address);
      setHouseNo(response.data.houseNo);
      console.log(response.data);
    } catch (error) {
      console.error(error);
    }
  }

  const logOut = () => {
    navigate("/");
  };

  const deleteAccount = () => {
    axios.get<UserData>(`https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/customerDelete/${userId}`);
    navigate("/");
  }

  return (
    <div>
      <NavBar /> 
      <div className="profileContainer">
        <div className="profileBox">
          <h2 className="profileItems">Profile</h2>
          <p>Address: {address}</p>
          <p>House Number: {houseNo}</p>
        
          <button className="logOutButton" onClick={logOut}>Log Out</button>
          <button className="logOutButton" onClick={deleteAccount}>Delete Account</button>
        </div>
      </div>
    </div>
  );
}

export default Profile;
