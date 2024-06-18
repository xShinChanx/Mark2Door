package com.fontys.shopservice.service.ServiceImp;

import com.fontys.shopservice.model.Item;
import com.fontys.shopservice.repository.ItemRepository;
import com.fontys.shopservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ItemServiceImp implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getListOfItems() {
        return itemRepository.findAll();
    }

    @Override
    public Optional<Item> findItem(Long itemID) {
        return itemRepository.findById(itemID);
    }

    @Override
    public List<Item> getItemsByShopId(int shopId) {
        return itemRepository.findByShopId(shopId);
    }
}
