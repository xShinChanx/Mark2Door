import axios from "axios";
import { useState, FormEvent } from "react";
import "../css/loginform.css";
import NavBar from "../components/navbar"

const LoginForm = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
        const response = await axios.post("http://localhost:8085/user/login", { email, password });
        console.log(password)
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
