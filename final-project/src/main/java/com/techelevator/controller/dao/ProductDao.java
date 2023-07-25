package com.techelevator.controller.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {



    List<Product> getProducts();

    List<Product> getProductNameAndSku(String sku, String name);


    Product getProductDetails(int productId);


}

