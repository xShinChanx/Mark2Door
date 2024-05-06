package com.fontys.shopservice.controller;

import com.fontys.shopservice.model.Item;
import com.fontys.shopservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<?> savedShop(@RequestBody Item item) {
        itemService.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> getListOfItems(){
        return new ResponseEntity<>(itemService.getListOfItems(), HttpStatusCode.valueOf(200));
    }

}
