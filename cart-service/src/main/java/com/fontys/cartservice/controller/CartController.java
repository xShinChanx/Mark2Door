package com.fontys.cartservice.controller;

import com.fontys.cartservice.model.Cart;
import com.fontys.cartservice.model.Request.AddItemToCartRequest;
import com.fontys.cartservice.model.Request.RemoveItemFromCartRequest;
import com.fontys.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping(value = "/carts")
    public ResponseEntity<List<Cart>> getListOfCarts(){
        return new ResponseEntity<>(cartService.getListOfCarts(), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/addItemToCart")
    public ResponseEntity<?> addItemToCart(@RequestBody AddItemToCartRequest addItemToCartRequest){
        return ResponseEntity.ok(cartService.addItemsToCart(addItemToCartRequest));
    }

    @DeleteMapping("/deleteItemFromCart")
    public ResponseEntity<?> removeItemFromCart(@RequestBody RemoveItemFromCartRequest removeItemFromCartRequest){
        return ResponseEntity.ok(cartService.removeItemFromCart(removeItemFromCartRequest));
    }

    @GetMapping("/getItems")
    public ResponseEntity<List<Long>> getListOfItems(@RequestParam Long userID) {
        return ResponseEntity.ok(cartService.listOfItemsInCart(userID));
    }
}
