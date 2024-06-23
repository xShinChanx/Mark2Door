import axios from "axios";
import { useState, FormEvent } from "react";
import { useNavigate } from "react-router-dom"; // Import useNavigate
import "../css/loginform.css";

const RegisterPage = () => {
  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [role, setRole] = useState<string>("Customer"); // Default role
  const [error, setError] = useState<string>("");
  const navigate = useNavigate(); // Initialize the useNavigate hook

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post("https://spring-security-service-jl4ebnk3lq-ez.a.run.app/auth/signup", { name, email, password, role });
      console.log(response.data);
      
      // Redirect to /login after successful registration
      navigate("/login");
    } catch (err) {
      console.error(err);
      setError("An error occurred");
    }
  };

  return (
    <div>
      <div className="login-container">
        <form className="login-form" onSubmit={handleSubmit}>
          <h1>Register</h1>
          {error && <p>{error}</p>}

          <input
            type="text"
            placeholder="name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

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

          <div className="role-selection">
            <label>
              <input
                type="radio"
                value="Customer"
                checked={role === "Customer"}
                onChange={(e) => setRole(e.target.value)}
              />
              <span>Customer</span>
            </label>
            <label>
              <input
                type="radio"
                value="ShopOwner"
                checked={role === "ShopOwner"}
                onChange={(e) => setRole(e.target.value)}
              />
              <span>Shop Owner</span>
            </label>
          </div>

          <button type="submit">Register</button>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
