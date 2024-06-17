import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './page/LoginPage';
import RegisterPage from './page/RegisterPage';
import ListOfShops from './page/ListOfShops';
import ListOfItems from './page/ListOfItems';
import Cart from './page/Customer/Cart';
import CreateShop from './page/Shop_Owner/createShop'
import CreateItem from './page/Shop_Owner/createItem'
import TestPage from './page/TestPage';

function App() {
  return (
    <Router>
      <div className="page">
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/listOfShops" element={<ListOfShops />} />
          <Route path="/items" element={<ListOfItems />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/createShop" element={<CreateShop />} />
          <Route path="/createItem" element={<CreateItem />} />
          <Route path="/test" element={<TestPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
