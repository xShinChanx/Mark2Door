import axios from "axios";
import { useState, FormEvent } from "react";
import "../css/loginform.css";

const RegisterPage = () => {
  const [name, setName] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [role, setRole] = useState<string>("Customer"); // Default role
  const [error, setError] = useState<string>("");

  const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8085/user/createAccount", { name, email, password, role });
      console.log(response.data);
    } catch (err) {
      console.error(err);
      setError("An error occurred");
    }
  };

  return (
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
  );
};

export default RegisterPage;
