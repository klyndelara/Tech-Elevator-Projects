package com.techelevator.model;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    private List<CartItem> cartItems;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal total;

    public CartDto() {

    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public CartDto(List<CartItem> cartItems, BigDecimal subTotal, BigDecimal tax, BigDecimal total) {
        this.cartItems = cartItems;
        this.subTotal = subTotal;
        this.tax = tax;
        this.total = total;
    }
}
