package com.techelevator.controller.dao;

import com.techelevator.model.CartItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
    public class JdbcCartItemDao implements CartItemDao {

    private final JdbcProductDao jdbcProductDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate, JdbcProductDao jdbcProductDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcProductDao = jdbcProductDao;
    }


    //GET /cart - get the user's cart (Required Use Case 4)
    @Override
    public CartItem getById(int cartItemId, int userId) {
        String sql = "SELECT * FROM cart_item WHERE cart_item = ? and user_id =?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, cartItemId, userId);
        if (rowSet.next()) {
            CartItem cartItem = mapRowToCartItem((rowSet));
            return cartItem;
        }
        return null;
    }
@Override
    public List<CartItem> getByUserId(int userId){
        List<CartItem> list = new ArrayList<>();
        String sql = "SELECT * FROM cart_item WHERE user_id = ? ORDER BY cart_item_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        while (rowSet.next()){
            CartItem item  = mapRowToCartItem(rowSet);
            list.add(item);

        }
           return list; }


   @Override
    public List<CartItem> getCartItems(int userId) {
        List<CartItem> cartItems = new ArrayList<>();

        String sql = "SELECT * FROM cart_item WHERE user_id = ? ORDER BY cart_item_id";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql,userId);
        while (rowSet.next()) {
            CartItem cartItem = mapRowToCartItem(rowSet);
            cartItems.add(cartItem);
        }
        return cartItems;
    }
    //POST /cart/items - add item to cart (Required Use Case 5)
    @Override
    public int insert (CartItem newCartItem) {
        String sql = "INSERT INTO cart_item (user_id, product_id, quantity) VALUES(?, ?, ?) RETURNING cart_item_id;";
        int cartItem = jdbcTemplate.queryForObject(sql, int.class, newCartItem.getUserId(), newCartItem.getProductId(),
        newCartItem.getQuantity());

        return cartItem;
    }
    public CartItem getByProductAndUserId(int productId, int user_id) {
        String sql = "Select * from cart_item WHERE product_id = ? AND user_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, productId, user_id);
        if (rowSet.next()) {
            CartItem cartItem = mapRowToCartItem((rowSet));
            return cartItem;
        }
        return null;
    }

    //DELETE /cart/items/{itemId} - remove item from cart (Required Use Case 6)
    public void delete (int cartItem, int userId) {
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ? AND user_id = ?";
        jdbcTemplate.update(sql, cartItem, userId);
    }

    //DELETE /cart - clear cart (Required Use Case 7)
    public void clearItemsByUserId (int userId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);

    }


    private CartItem mapRowToCartItem(SqlRowSet rowSet) {
        CartItem cartItem = new CartItem();
        cartItem.setCartItemId(rowSet.getInt("cart_item_id"));
        cartItem.setUserId(rowSet.getInt("user_id"));
        cartItem.setProductId(rowSet.getInt("product_id"));
        cartItem.setQuantity(rowSet.getInt("quantity"));

        return cartItem;

    }


}

