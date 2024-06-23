import { useEffect, useState } from 'react';
import Shop from '../../components/shop';
import NavBar from "../../components/navbars/CustomerNavbar"
import Cookies from 'js-cookie'; // Assuming you're using js-cookie


type ShopType = {
  id: number;
  name: string;
  description: string;
};

const ListOfShops = () => {
  const [shops, setShops] = useState<ShopType[]>([]);

  useEffect(() => {
    const fetchShops = async () => {
      try {
        const token = Cookies.get("token");
        console.log(token);

        const response = await fetch('https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/shops'
        //   , {
        //   headers: {
        //     Authorization: `Bearer ${token}`, // Assuming token format is Bearer + token
        //   },
        // }
      );

        const data = await response.json();
        setShops(data);
      } catch (error) {
        console.error('Error fetching shops:', error);
      }
    };

    fetchShops();
  }, []);

  return (
    <div>
      <NavBar /> 
      <div>
        {shops.map((shop) => (
          <Shop key={shop.id} name={shop.name} description={shop.description} />
        ))}
      </div>
    </div>
  );
};

export default ListOfShops;
