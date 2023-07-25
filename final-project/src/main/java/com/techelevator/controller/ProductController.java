package com.techelevator.controller;

import com.techelevator.dao.ProductDao;
import com.techelevator.model.Product;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PropertyResourceBundle;

@RestController
@RequestMapping("/products")
public class ProductController {
    JdbcTemplate jdbcTemplate;

    private ProductDao productDao;

    /*public ProductController(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/m2_final_project");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        jdbcTemplate = new JdbcTemplate(dataSource);
    }*/


    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    // GET /products - get the list of products (Required Use Case 1)
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> list() {

        return productDao.getProducts();
    }

    //GET /products?sku={product_sku}&name={product_name} - search for products (Required Use Case 2)
   /* @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Product> productNameAndSku(@RequestParam(required = false) String sku, @RequestParam(required = false) String name) {
        return productDao.getProductNameAndSku(sku, name);
    }
   /* @RequestMapping( path = "", method = RequestMethod.GET)
    public List<Auction> list(@RequestParam(defaultValue = "") String title_like, @RequestParam(defaultValue = "0") double currentBid_lte) {

        if( !title_like.equals("") ) {
            return dao.searchByTitle(title_like);
        }
        if(currentBid_lte > 0) {
            return dao.searchByPrice(currentBid_lte);
        }

        return dao.list();
    }*/


    //GET /products/{id} - get a single product (Required Use Case 3)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) {
        Product product = productDao.getProductDetails(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found");
        } else {
            return productDao.getProductDetails(id);
        }

    }
    /*GET /products/{id} - get a single product (Required Use Case 3)
    @RequestMapping(path = "/products/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int productId) {
         SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM Product where product_id = ?", productId);
        if (rowSet.next()) {
            Product product = new Product();
            product.setProduct(rowSet.getInt("product_id"));
            product.setProductSku(rowSet.getString("product_sku"));
            product.setName(rowSet.getString("name"));
            product.setDescription(rowSet.getString("description"));
            product.setPrice(rowSet.getDouble("price"));
            product.setImageName(rowSet.getString("image_name"));
                   return product;
            } else {
                    return null;  }*/


}


