import { useEffect, useState } from "react";
import Cookies from 'js-cookie'; // Assuming you're using js-cookie
import Item from '../../components/Item/CartItem'

const Shop = () => {
    const [cart, setCart] = useState<number[]>([]);
    const [items, setItems] = useState<{ id: number, name: string, description: string, count: number }[]>([]);
    
    const userId = Cookies.get("userId");
    const token = Cookies.get("token");
    console.log(userId);

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch(`http://localhost:8085/cart/getItems?userID=${userId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`, // Assuming token format is Bearer + token
                    },
                });
                const data = await response.json();
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
                    fetch(`http://localhost:8085/shop/findItemById?itemID=${itemID}`, {
                        headers: {
                            Authorization: `Bearer ${token}`, // Include the Authorization header
                        },
                    })
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
            {items.length > 0 ? (
                items.map(item => (
                    <Item
                        key={item.id}
                        name={item.name}
                        description={item.description}
                        count={item.count}
                    />
                ))
            ) : (
                <p>Loading items...</p>
            )}
        </div>
    );
};

export default Shop;
