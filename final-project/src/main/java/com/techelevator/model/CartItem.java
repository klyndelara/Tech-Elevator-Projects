package com.techelevator.model;

import org.springframework.stereotype.Component;

@Component
public class CartItem {
    private int cartItemId;
    private int userId;
    private int productId;
    private Product product;
    private int quantity;



    public CartItem(int cartitemId, int userId, Product product, int productId, int quantity) {
        this.cartItemId = cartitemId;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.product = product;
    }

    public CartItem() {

    }


    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
