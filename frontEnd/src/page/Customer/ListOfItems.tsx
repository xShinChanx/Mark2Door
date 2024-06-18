import { useEffect, useState } from "react";
import Item from '../../components/Item/item'
import NavBar from "../../components/navbars/CustomerNavbar"
import Cookies from 'js-cookie'; // Assuming you're using js-cookie

type ItemType = {
    id: number;
    name: string;
    description: string;
    shopid: number;
  };
  
  const ListOfItems = () => {
    const [items, setItems] = useState<ItemType[]>([]);
    const userIDString = Cookies.get("userId");
    const userID = userIDString ? parseInt(userIDString, 10) : null;
  
    useEffect(() => {
      const fetchItems = async () => {
        try {
          const token = Cookies.get("token");
          const response = await fetch('http://localhost:8085/shop/items', {
            headers: {
              Authorization: `Bearer ${token}`, // Assuming token format is Bearer + token
            },
          });
          const data = await response.json();
          setItems(data);
        } catch (error) {
          console.error(error);
        }
      };
      fetchItems();
    }, []);
  
    if (userID === null) {
      return <div>Error: User ID is not available</div>;
    }
  
    return (
      <div>
        <NavBar />
        <div>
          {items.map((item) => (
            <Item
              key={item.id}
              name={item.name}
              description={item.description}
              itemID={item.id}
              userID={userID}
            />
          ))}
        </div>
      </div>
    );
  };
  
  export default ListOfItems;