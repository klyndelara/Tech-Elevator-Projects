package com.techelevator.controller.dao;

import com.techelevator.model.CartItem;

import java.util.List;

public interface CartItemDao {



    CartItem getById(int cartItemId, int userId);


    List<CartItem> getByUserId(int userId);

    List<CartItem> getCartItems(int userId);

    int insert (CartItem newCartItem);

    //CartItem create(int newCartItem);
}
