package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;



import javax.sql.DataSource;
import java.util.List;

public class JdbcProductDao implements ProductDao {
    /**
     * JDBC template used to access database throughout this DAO
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     *
     * @param dataSource Data source from main application
     */

    public JdbcProductDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Product getProduct(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, productId);

        if (rowSet.next()) {
            product = mapRowToProduct(rowSet);
        }
        return product;
    }

    @Override
    public List<Product> getProducts() {
        String sql = "SELECT * From product;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<Product> products = new ArrayList<>();
        while (rowSet.next()) {
            Product product = mapRowToProduct(rowSet);

            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductsWithNoSales() {
        String sql = "SELECT * from product WHERE product_id NOT IN (SELECT product_id FROM line_item);";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<Product> products = new ArrayList<>();
        while (rowSet.next()) {
            Product product = mapRowToProduct(rowSet);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product createProduct(Product newProduct) {
        String sql = "INSERT INTO product (name, description, price, image_name) VALUES(?, ?, ? ,?) " +
                    " RETURNING product_id;";
        int productId = jdbcTemplate.queryForObject(sql, Integer.class, newProduct.getName(), newProduct.getDescription(),
                        newProduct.getPrice(), newProduct.getImageName());
                newProduct.setProductId(productId);

        return newProduct;
    }

    @Override
    public void updateProduct(Product updatedProduct) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, image_name = ? " +
                    "WHERE product_id = ?;";
        jdbcTemplate.update(sql,updatedProduct.getName(), updatedProduct.getDescription(), updatedProduct.getPrice(),
                updatedProduct.getPrice(), updatedProduct.getImageName());

    }

    @Override
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?;";
        jdbcTemplate.update(sql, productId);

    }
    private Product mapRowToProduct (SqlRowSet rowSet) {
        Product product = new Product();
        product.setProductId(rowSet.getInt("product_id"));
        product.setName(rowSet.getString("name"));
        product.setDescription(rowSet.getString("description"));
        product.setPrice(rowSet.getBigDecimal("price"));
        product.setImageName(rowSet.getString("image_name"));

        return product;
    }
    /**
     * Get a product from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param productId The id of the product.
     * @return A filled out Product object, or null if the id is invalid.
     */
    /**
     * Get all products from the datastore, ordered by product_id.
     *
     * @return All products as Product objects in a List.
     */

    }

