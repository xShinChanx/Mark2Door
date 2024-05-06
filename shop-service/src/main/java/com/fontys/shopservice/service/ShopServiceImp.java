package com.fontys.shopservice.service;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ShopServiceImp implements ShopService {
    @Autowired
    ShopRepository shopRepository;

    @Override
    public Shop saveShopData(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public List<Shop> getListOfShops() {
        return shopRepository.findAll();
    }
}
