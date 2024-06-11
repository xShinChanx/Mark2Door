package com.fontys.shopservice.controller;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @GetMapping("/test")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Welcome to shop-service");
    }

    @PostMapping("/shop")
    public ResponseEntity<?> savedShop(@RequestBody Shop shop) {
        shopService.saveShopData(shop);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/shops")
    public ResponseEntity<List<Shop>> getUserData() {
        List<Shop> shops = shopService.getListOfShops();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }
}
