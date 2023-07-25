package com.techelevator.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productSku;
    private String name;
    private String description;
    private double price;
    private String imageName;





    public Product(int productId, String productSku, String name, String description, double price, String imageName) {
        this.productId = productId;
        this.productSku = productSku;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageName = imageName;
    }

    public Product() {

    }

    public int getProduct() {
        return productId;
    }

    public void setProduct(int productId) {
        this.productId = productId;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = this.price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }


    public Product(String productSku, String name, String description, BigDecimal price, String imageName){

    }
}
