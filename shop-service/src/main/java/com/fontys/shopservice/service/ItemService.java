package com.fontys.shopservice.service;

import com.fontys.shopservice.model.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    Item saveItem(Item item);
    List<Item> getListOfItems();
    Optional<Item> findItem (Long itemID);
}
