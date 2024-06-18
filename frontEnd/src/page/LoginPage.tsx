import axios from "axios";
import { useState, FormEvent } from "react";
import Cookies from "js-cookie";
import "../css/loginform.css";
import NavBar from "../components/navbar";

const LoginForm = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:6060/auth/signin", { email, password });
      console.log(response.data);
  
      // Extract the token, refreshToken (optional), and userId from the response
      const { token, refreshToken, userId } = response.data;
  
      // Check if userId exists in the response (optional)
      if (!userId) {
        console.error("User ID not found in response");
        setError("An error occurred (missing user ID)");
        return; // Handle missing userId gracefully (optional)
      }
  
      // Save the token, refresh token (optional), and userId as cookies
      Cookies.set("token", token, { secure: true, sameSite: 'strict' });
      Cookies.set("refreshToken", refreshToken, { secure: true, sameSite: 'strict' });
      Cookies.set("userId", userId, { secure: true, sameSite: 'strict' });
  
      // You can also handle redirection or other logic after successful login here
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
            type="text"
            placeholder="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            placeholder="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
};

export default LoginForm;
