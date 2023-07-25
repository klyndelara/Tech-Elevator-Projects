package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    private static JdbcTemplate jdbcTemplate;

    public JdbcProductDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }




   /* GET /products - get the list of products (Required Use Case 1)
   As an unauthenticated user, I can see a list of products for sale.*/
    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

            String sql = "SELECT * FROM product";
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
            while (rowSet.next()) {
                Product product = mapRowToProduct(rowSet);
                products.add(product);
            }
            return products;
        }




    /*GET /products?sku={product_sku}&name={product_name} - search for products (Required Use Case 2)
    As an unauthenticated user, I can search for a list of products by name or SKU.*/
public List<Product> getProductNameAndSku(String sku, String name){
        List<Product> list = new ArrayList<>();
        String sql = "Select * FROM product WHERE name = ? AND product_sku = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, sku, name);
        while (rowSet.next()) {
            Product product = mapRowToProduct(rowSet);
            list.add(product);
        }
        return list;
        }


    /*GET /products/{id} - get a single product (Required Use Case 3)
     As an unauthenticated user, I can view additional information about a specific product (product detail).*/


    public Product getProductDetails(int productId) {
        Product product = null;
        String sql = "Select * From product Where product_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, productId);
        if (rowSet.next()) {
            product = mapRowToProduct(rowSet);

        }

        return product;
    }






    private static Product mapRowToProduct(SqlRowSet rowSet) {
        Product product = new Product();
        product.setProduct(rowSet.getInt("product_id"));
        product.setProductSku(rowSet.getString("product_sku"));
        product.setName(rowSet.getString("name"));
        product.setDescription(rowSet.getString("description"));
        product.setPrice(rowSet.getDouble("price"));
        product.setImageName(rowSet.getString("image_name"));


        return product;
    }


}


