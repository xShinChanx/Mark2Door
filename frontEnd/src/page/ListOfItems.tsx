import { useEffect, useState } from "react";
import Item from '../components/item'
import NavBar from "../components/navbars/ShopOwnerNavbar"


type ItemType = {
    id: number;
    name: string;
    description: string;
    shopid: number;
  };


const ListOfItems = () => {
    const [item, setItems] = useState<ItemType[]>([]);

    useEffect(() =>{
        const fetchItems = async () =>{
            try{
                const response = await fetch('http://localhost:8085/shop/items');
                const data = await response.json();
                setItems(data)
            }   catch (error) {
                console.error(error)
            }
        };
        fetchItems();
    }, []);

    return (
        <div>
            <NavBar /> 
            <div>
                {item.map((item) => (
                    <Item key={item.id} name={item.name} description={item.description}/>
                ))}
            </div>
        </div>

    );
};

export default ListOfItems;