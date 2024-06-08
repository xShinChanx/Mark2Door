package com.fontys.cartservice.service;

import com.fontys.cartservice.model.Cart;
import com.fontys.cartservice.model.Request.AddItemToCartRequest;
import com.fontys.cartservice.model.Request.RemoveItemFromCartRequest;

import java.util.List;

public interface CartService {
    List<Cart> getListOfCarts();
    Cart addItemsToCart (AddItemToCartRequest addItemToCartRequest);
    String removeItemFromCart (RemoveItemFromCartRequest removeItemFromCartRequest);
    List <Long> listOfItemsInCart (Long userID);
}
