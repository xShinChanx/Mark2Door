package com.fontys.shopservice.service.ServiceImp;

import com.fontys.shopservice.model.Item;
import com.fontys.shopservice.repository.ItemRepository;
import com.fontys.shopservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
