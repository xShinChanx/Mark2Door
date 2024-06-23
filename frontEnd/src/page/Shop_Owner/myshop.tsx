import React from 'react';
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';
import './../../css/loginform.css'; // Ensure this path is correct based on your project structure
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import Item from '../../components/Item/ShopItem'
import NavBar from "../../components/navbars/ShopOwnerNavbar";

type ItemType = {
  id: number;
  name: string;
  description: string;
  price: number | null;
  shopId: number;
};

const MyShop: React.FC = () => {
  const navigate = useNavigate();
  const [shopId, setShopId] = useState<number | null>(null); // State to hold the shopId
  const [items, setItems] = useState<ItemType[]>([]); // State to hold items fetched by shopId
  
  const userId = Cookies.get("userId");
  const token = Cookies.get("token");

  useEffect(() => {
    const fetchShopId = async () => {

      try {
        const response = await fetch(`https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/shopId/${userId}`, {
          headers: {
            Authorization: `Bearer ${token}`
          }
        });
      
        if (response.ok) {
          const data = await response.json();
          if (typeof data === 'number') {
            setShopId(data);
          } else {
            console.log("Invalid shopId format");
          }
        } else {
          console.error('Failed to fetch shopId');
        }
      } catch (error) {
        console.error('Error fetching shopId:', error);
      }
    };

    fetchShopId();
  }, []);

  useEffect(() => {
    const fetchItems = async () => {
      if (shopId !== null) {
        try {
          const response = await fetch(`https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/findItemsbyShopId?shopId=${shopId}`
          //   , {
          //   headers: {
          //     Authorization: `Bearer ${token}`
          //   }
          // }
        );
        
          if (response.ok) {
            const data = await response.json();
            setItems(data);
          } else {
            console.error('Failed to fetch items');
          }
        } catch (error) {
          console.error('Error fetching items:', error);
        }
      }
    };

    fetchItems();
  }, [shopId]);

  const handleCreateShop = () => {
    navigate('/createShop');
  };

  return (
<div>
  <NavBar />
<div>
      <div>
        <h1 className='MyShop'>My Shop</h1>
        {shopId === null && (
          <button className='CreateShop' onClick={handleCreateShop}>Create a Shop</button>
        )}
         </div>

        {shopId !== null && (
          <div className="items-container">
            <h2 className='MyShop'>Items in Shop</h2>
            {items.map(item => (
              <Item
                key={item.id}
                name={item.name}
                description={item.description}
                price={item.price || 0} // Default to 0 if price is null
              />
            ))}
          </div>
        )}
      </div>
      </div>
   
  );
};

export default MyShop;