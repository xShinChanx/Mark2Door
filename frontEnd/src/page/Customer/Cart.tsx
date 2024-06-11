import { useEffect, useState } from "react";


const Shop = () => {
    const [cart, setCart] = useState<number[]>([]);
    const [items, setItems] = useState<{ id: number, name: string, description: string, count: number }[]>([]);

    // Hard-coded userID
    const userID = 1;

    useEffect(() => {
        const fetchItems = async () => {
            try {
                const response = await fetch(`http://localhost:8085/cart/getItems?userID=${userID}`);
                const data = await response.json();
                setCart(data);
            } catch (error) {
                console.error(error);
            }
        };

        fetchItems();
    }, [userID]);

    useEffect(() => {
        const fetchItemDetails = async () => {
            try {
                const itemDetailsPromises = cart.map(itemID =>
                    fetch(`http://localhost:8085/shop/findItemById?itemID=${itemID}`)
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
                    <div key={item.id} className="item-container">
                        <h2 className="item-header">Name: {item.name}</h2>
                        <p className="item-description">Description: {item.description}</p>
                        <p className="item-count">Count: {item.count}</p>
                        <div className="remove-button">Remove From Cart</div>
                    </div>
                ))
            ) : (
                <p>Loading items...</p>
            )}
        </div>
    );
};

export default Shop;