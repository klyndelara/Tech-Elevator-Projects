package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class JdbdProductDaoTest extends BaseDaoTests {
    private static final Product PRODUCT_1 = new Product(1,"Product 1",
            "Description 1", new BigDecimal("9.99"), "product-1.png");
    private static final Product PRODUCT_2 = new Product(2, "Product 2",
            "Description 2", new BigDecimal("19.00"), "product-2.png");
    private static final Product PRODUCT_3 = new Product(3, "Product 3",
            "Description 3", BigDecimal.valueOf(123.45), "product-3.png");
    private static final Product PRODUCT_4 = new Product(4, "Product 4",
            "Description 4",   BigDecimal.valueOf(.99), "product-4.png");



    private JdbcProductDao dao;
    private Product testProduct;

    @Before
    public void setup() {
        testProduct = new Product(1, " ",
                " ", new BigDecimal("5.00"), " ");
        dao = new JdbcProductDao(dataSource);
    }

    @Test
    public void getProduct_returns_correct_product_for_id(){
        Product product = dao.getProduct(1);
        Assert.assertNotNull(product);
        assertProductsMatch(PRODUCT_1, product);

    }
    @Test
    public void getProducts_returns_list_of_products(){
        List<Product> products = dao.getProducts();
        Assert.assertEquals(4, products.size());
        assertProductsMatch(PRODUCT_1, products.get(0));
        assertProductsMatch(PRODUCT_2, products.get(1));
        assertProductsMatch(PRODUCT_3, products.get(2));
        assertProductsMatch(PRODUCT_4, products.get(3));
    }
    @Test
    public void created_product_has_expected_values_when_retrieved() {
        Product createProduct = dao.createProduct(testProduct);
        Integer newId = createProduct.getProductId();
        Product retrievedProduct = dao.getProduct(newId);

        assertProductsMatch(createProduct, retrievedProduct);
    }



    private void assertProductsMatch(Product expected, Product actual){
        Assert.assertEquals(expected.getProductId(), actual.getProductId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getDescription(), actual.getDescription());
        Assert.assertEquals(expected.getPrice(), actual.getPrice());
        Assert.assertEquals(expected.getImageName(),actual.getImageName());
    }
}
