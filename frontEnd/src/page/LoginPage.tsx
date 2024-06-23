import axios from "axios";
import { useState} from "react";
import Cookies from "js-cookie";
import "../css/loginform.css";
import { useNavigate, Link } from "react-router-dom"; // Updated to useNavigate
import NavBar from "../components/navbars/LoginAndRegisterNavbar"


const LoginForm = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post("https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/signin", { email, password });
      console.log(response.data);
  
      // Extract the token, refreshToken (optional), userId, and role from the response
      const { token, refreshToken, userId, role } = response.data;
  
      // Check if userId and role exist in the response
      if (!userId || !role) {
        console.error("User ID or role not found in response");
        setError("An error occurred (missing user ID or role)");
        return; // Handle missing userId or role gracefully
      }
  
      // Save the token, refresh token (optional), and userId as cookies
      Cookies.set("token", token, { secure: true, sameSite: 'strict' });
      Cookies.set("refreshToken", refreshToken, { secure: true, sameSite: 'strict' });
      Cookies.set("userId", userId, { secure: true, sameSite: 'strict' });
  
      // Redirect based on user role
      if (role === "ShopOwner") {
        navigate("/homepageShopOwner");
      } else if (role === "Customer") {
        navigate("/homepageCustomer");
      } else {
        console.error("Unknown user role");
        setError("An error occurred (unknown user role)");
      }

      console.log("Login successful, tokens and user ID saved as cookies");
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
          <h1>Login</h1>
          {error && <p>{error}</p>}
          <input
            id="email"
            type="text"
            placeholder="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            id="password"
            type="password"
            placeholder="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit" id="ButtonLogin">Login</button>
          <p style={{ margin: "5px 0 0", fontSize: "14px" }}>
          Don't have an account?{" "}
          <Link to="/signup" style={{ fontSize: "14px", color: "black" }}>Register</Link>
        </p>
        </form>

      </div>
    </div>
  );
};

export default LoginForm;