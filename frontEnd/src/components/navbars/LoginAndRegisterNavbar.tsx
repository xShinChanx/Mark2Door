import { NavLink } from "react-router-dom"
import '../../css/navbar.css'


function NavBar() {
  const links = [
    {
      id: 1,
      path: "/",
      text: "Home"
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