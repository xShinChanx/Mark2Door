package com.fontys.cartservice.service.ServiceImp;

import com.fontys.cartservice.model.Cart;
import com.fontys.cartservice.model.Request.AddItemToCartRequest;
import com.fontys.cartservice.model.Request.CreateCartRequest;
import com.fontys.cartservice.model.Request.RemoveItemFromCartRequest;
import com.fontys.cartservice.repository.CartRepository;
import com.fontys.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartServiceImp implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public Cart createCart(CreateCartRequest createCartRequest) {
        Cart newCart = new Cart();
        newCart.setUserID(createCartRequest.getUserID());
        return cartRepository.save(newCart);
    }

    @Override
    public List<Cart> getListOfCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addItemsToCart(AddItemToCartRequest addItemToCartRequest) {
        Cart newItemToCart = new Cart();
        newItemToCart.setItemID(addItemToCartRequest.getItemID());
        newItemToCart.setUserID(addItemToCartRequest.getUserID());
        return cartRepository.save(newItemToCart);
    }

    @Override
    public String removeItemFromCart(RemoveItemFromCartRequest removeItemFromCartRequest) {
        //First we find the cart and the item
        Cart ItemAndCart = cartRepository.findByUserIDAndItemID(removeItemFromCartRequest.getUserID(), removeItemFromCartRequest.getItemID());
        cartRepository.delete(ItemAndCart);
        return "Deleted Item from the Cart";
    }
}
