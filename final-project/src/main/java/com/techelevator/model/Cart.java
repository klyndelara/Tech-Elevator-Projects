package com.techelevator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
 private List<CartItem> list;

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public Cart (List <CartItem> list) {
     this.list = list;
 }

    public Cart (){
        this.list = new ArrayList<>();
}




}
