package com.fontys.shopservice.service;

import com.fontys.shopservice.model.Item;
import com.fontys.shopservice.model.Shop;

import java.util.List;

public interface ItemService {
    Item saveItem(Item item);
    List<Item> getListOfItems();
}
