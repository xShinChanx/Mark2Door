package com.fontys.shopservice.controller;

import com.fontys.shopservice.model.Item;
import com.fontys.shopservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<?> createItem(@RequestBody Item item) {
        itemService.saveItem(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/items")
    public ResponseEntity<List<Item>> getListOfItems() {
        List<Item> items = itemService.getListOfItems();
        return new ResponseEntity<>(items, HttpStatus.OK); // Use HttpStatus.OK
    }

    @GetMapping("/findItemById")
    public ResponseEntity<Item> findItem(@RequestParam Long itemID) {
        Optional<Item> itemOptional = itemService.findItem(itemID);
        if (itemOptional.isPresent()) {
            return ResponseEntity.ok(itemOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/findItemsbyShopId")
    public List<Item> getItemsByShopId(@RequestParam int shopId) {
        return itemService.getItemsByShopId(shopId);
    }

    @DeleteMapping("/shop/{shopId}")
    public ResponseEntity<String> deleteItemsByShopId(@PathVariable int shopId) {
        String result = itemService.deleteItemsByShopId(shopId);
        if (result.startsWith("All items")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }


}





