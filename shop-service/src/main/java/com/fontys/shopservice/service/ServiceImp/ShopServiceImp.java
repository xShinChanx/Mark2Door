package com.fontys.shopservice.service.ServiceImp;

import com.fontys.shopservice.model.Shop;
import com.fontys.shopservice.repository.ShopRepository;
import com.fontys.shopservice.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
