package com.fontys.cartservice.controller;

import com.fontys.cartservice.model.Cart;
import com.fontys.cartservice.model.Request.AddItemToCartRequest;
import com.fontys.cartservice.model.Request.CreateCartRequest;
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

    @PostMapping("/createCart")
    public ResponseEntity<?> createCart(@RequestBody CreateCartRequest createCartRequest){
        return ResponseEntity.ok(cartService.createCart(createCartRequest));
    }

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
}
