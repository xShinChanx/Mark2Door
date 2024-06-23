import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginPage from './page/LoginPage';
import RegisterPage from './page/RegisterPage';
import ListOfShops from './page/Customer/ListOfShops';
import ListOfItems from './page/Customer/ListOfItems';
import Cart from './page/Customer/Cart';
import CreateShop from './page/Shop_Owner/createShop'
import CreateItem from './page/Shop_Owner/createItem'
import HomePageCustomer from './page/Customer/HomepageCustomer'
import HomePageShopOwner from './page/Shop_Owner/HomepageShopOwner'
import HomePage from './page/Homepage'
import MyShop from './page/Shop_Owner/myshop'
import AddAddress from './page/Customer/addAddress'
import Profile from './page/Customer/ProfilePage'
import PublicItem from './page/PublicListOfItems'


import TestPage from './page/TestPage';

function App() {
  return (
    <Router>
      <div className="page">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/homepageCustomer" element={<HomePageCustomer />} />
          <Route path="/homepageShopOwner" element={<HomePageShopOwner />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/signup" element={<RegisterPage />} />
          <Route path="/shops" element={<ListOfShops />} />
          <Route path="/items" element={<ListOfItems />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/createShop" element={<CreateShop />} />
          <Route path="/createItem" element={<CreateItem />} />
          <Route path="/myshop" element={<MyShop />} />
          <Route path="/updateAddress" element={<AddAddress />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/itemss" element={<PublicItem />} />
          <Route path="/test" element={<TestPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
