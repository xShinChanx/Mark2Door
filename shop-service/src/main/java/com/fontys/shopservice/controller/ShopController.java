package com.fontys.shopservice.controller;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShopController {
    @Autowired
    ShopService shopService;

    @PostMapping("/shop")
    public ResponseEntity<?> savedShop(@RequestBody Shop shop) {
        shopService.saveShopData(shop);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/shops")
    public ResponseEntity<List<Shop>> getUserData(){
        return new ResponseEntity<>(shopService.getListOfShops(), HttpStatusCode.valueOf(200));
    }
}
