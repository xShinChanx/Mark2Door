import { NavLink } from "react-router-dom"
import '../../css/navbar.css'


function NavBar() {
  const links = [
    {
      id: 1,
      path: "/",
      text: "Home"
    },
    {
      id: 2,
      path: "/items",
      text: "Items"
    },
    {
      id: 3,
      path: "/shops",
      text: "Shops"
    },
    {
      id: 4,
      path: "/profile",
      text: "Profile"
    }
  ];
  
  return (
    <nav className="menubar">
      <ul>
        <div className="logo">Market2Door</div>
        {links.map(link => {
          return (
            <li key={link.id}>
              <NavLink to={link.path}>{link.text}</NavLink>
            </li>
          );
        })}
      </ul>
    </nav>
    );
}
  
export default NavBar;