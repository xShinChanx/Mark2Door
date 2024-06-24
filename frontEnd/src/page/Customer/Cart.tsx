import { useEffect, useState } from "react";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import Item from '../../components/Item/CartItem'
import '../../css/homepage.css';
import axios from "axios";
import NavBar from "../../components/navbars/CustomerNavbar"



const Shop = () => {
    const [cart, setCart] = useState<number[]>([]);
    const [items, setItems] = useState<{ id: number, name: string, description: string, count: number }[]>([]);
    
    const userId = Cookies.get("userId");
    // const token = Cookies.get("token");
    console.log(userId);

    useEffect(() => {
        const fetchItems = async () => {
          try {
            const response = await axios.get(`https://new-gateway-6jhcj4ol.ew.gateway.dev/cart/getItems`, {
              params: {
                userID: userId,
              },
            });
            const data = response.data;
            setCart(data);
          } catch (error) {
            console.error(error);
          }
        };
    
        fetchItems();
      }, [userId]);

    useEffect(() => {
        const fetchItemDetails = async () => {
            try {
                const itemDetailsPromises = cart.map(itemID =>
                    fetch(`https://new-gateway-6jhcj4ol.ew.gateway.dev/shop/findItemById?itemID=${itemID}`
                    //     , {
                    //     headers: {
                    //         Authorization: `Bearer ${token}`, // Include the Authorization header
                    //     },
                    // }
                )
                        .then(response => response.json())
                );
                const itemsData = await Promise.all(itemDetailsPromises);

                // Count the occurrence of each item ID
                const itemCounts = itemsData.reduce((acc, item) => {
                    acc[item.id] = (acc[item.id] || 0) + 1;
                    return acc;
                }, {} as { [key: number]: number });

                // Merge items with counts
                const uniqueItems = Array.from(new Set(cart)); // get unique item IDs
                const itemsWithCounts = uniqueItems.map(itemID => ({
                    ...itemsData.find(item => item.id === itemID),
                    count: itemCounts[itemID]
                }));

                setItems(itemsWithCounts);
            } catch (error) {
                console.error(error);
            }
        };

        if (cart.length > 0) {
            fetchItemDetails();
        }
    }, [cart]);

    return (
        <div>
        <NavBar /> 

        <div>
            {items.length > 0 ? (
                <>
                    {items.map(item => (
                        <Item
                            key={item.id}
                            name={item.name}
                            description={item.description}
                            count={item.count}
                        />
                    ))}
                    <button className="checkout-button">
                        Checkout
                    </button>
                </>
            ) : (
                <p>Loading items...</p>
            )}
        </div>
        </div>
    );
};

export default Shop;
