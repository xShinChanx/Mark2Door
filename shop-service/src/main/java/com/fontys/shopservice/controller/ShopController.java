package com.fontys.shopservice.controller;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;

    @GetMapping("/test")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Welcome to shop-service");
    }

    @PostMapping("/createShop")
    public ResponseEntity<String> savedShop(@RequestBody Shop shop) {
        String result = shopService.saveShopData(shop);
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "/shops")
    public ResponseEntity<List<Shop>> getUserData() {
        List<Shop> shops = shopService.getListOfShops();
        return new ResponseEntity<>(shops, HttpStatus.OK);
    }

    @GetMapping("/shopId/{ownerId}")
    public Optional<Long> getShopIdByOwnerId(@PathVariable Long ownerId) {
        return shopService.getShopIdByOwnerId(ownerId);
    }

    @DeleteMapping("/delete/{ownerId}")
    public ResponseEntity<String> deleteShopByOwnerId(@PathVariable Long ownerId) {
        String deletionResult = shopService.deleteShopByOwnerId(ownerId);
        return ResponseEntity.status(HttpStatus.OK).body(deletionResult);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
