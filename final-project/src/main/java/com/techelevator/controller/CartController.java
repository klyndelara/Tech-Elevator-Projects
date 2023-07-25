package com.techelevator.controller;

import com.techelevator.Service.TaxService;
import com.techelevator.dao.*;
import com.techelevator.model.CartDto;
import com.techelevator.model.CartItem;
import com.techelevator.model.Product;
import com.techelevator.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
//@RequestMapping(path = "/cart")


public class CartController {

    private JdbcCartItemDao jdbcCartItemDao;
    private JdbcUserDao jdbcUserDao;
    private JdbcProductDao jdbcProductDao;
    private TaxService taxService;

    public CartController(JdbcCartItemDao jdbcCartItemDao, JdbcUserDao jdbcUserDao, JdbcProductDao jdbcProductDao) {
        this.jdbcCartItemDao = jdbcCartItemDao;
        this.jdbcUserDao = jdbcUserDao;
    }

    /*GET /cart - get the user's cart (Required Use Case 4)
    * As a user, I can view my shopping cart and see the following details:
    - The list of products, quantities, and prices in my cart
    - The subtotal of all products in my cart
    - The tax amount (in U.S. dollars) charged for my state
    - Obtain the tax rate from an external API using the URL: https://teapi.netlify.app/api/statetax?state=[state-code].
    - The state code is part of the user address information.
    - The tax rate returned from the API is a percentage. Convert this to a decimal value to use in calculating the tax amount.
    - The cart total, which is the subtotal plus the amount of tax*/
@RequestMapping(path = "/cart", method = RequestMethod.GET)
    public CartDto getCart(Principal principal){
    /*String username = principal.getName();
    User user = jdbcUserDao.findByUsername(username);
    CartDto cartDto = new CartDto();

    cartDto.setCartItems(jdbcCartItemDao.getByUserId(user.getId()));
    BigDecimal subTotal = new BigDecimal(0);

    for(CartItem cartItem: cartDto.getCartItems()){
        Product product = cartItem.getProduct();
        BigDecimal price = BigDecimal.valueOf(product.getPrice());
        BigDecimal quantity = new BigDecimal(cartItem.getQuantity());
        BigDecimal costOfitem = price.multiply(quantity);

        subTotal = subTotal.add(price.multiply(quantity));


    }
    cartDto.setSubTotal(subTotal);
// Tax rate and total
    RestTemplate restTemplate = new RestTemplate();
    TaxResponseDto taxResponseDto =
        restTemplate.getForObject("https://teapi.netlify.app/api/statetax?state=" + user.getStateCode(), TaxResponseDto.class);
    BigDecimal taxRate = taxResponseDto.getSalesTax().divide(new BigDecimal(100));


    BigDecimal tax = subTotal.multiply(taxRate);
    cartDto.setTax(tax);
    BigDecimal total = subTotal.add(tax);
    cartDto.setTotal(total);*/


    return null;
}



    /*POST /cart/items - add item to cart (Required Use Case 5)
    * As a user, I can add a product to my shopping cart.
    - If the product is already in my cart, increase the quantity appropriately.
    - The quantity added must be positive.*/
    @RequestMapping(path = "/cart/items", method = RequestMethod.POST)
    public void addItemToCart(@RequestBody CartItem cartItem, Principal principal) {
        String username = principal.getName();
        User user = jdbcUserDao.findByUsername(username);

        CartItem existingEntry = (CartItem) jdbcCartItemDao.getByProductAndUserId(cartItem.getProductId(),user.getId());

        if(existingEntry == null) {
            //insert new record
            jdbcCartItemDao.insert(cartItem);
        }else {
            //update the quantity
            int currentQuantity = existingEntry.getQuantity();
            int newQuantity = cartItem.getQuantity();

            existingEntry.setQuantity(currentQuantity + newQuantity);
            jdbcCartItemDao.insert(existingEntry);
        }



    }
    /*DELETE /cart/items/{itemId} - remove item from cart (Required Use Case 6)
    * As a user, I can remove a product from my shopping cart. This removes the item from the cart entirely, regardless of the quantity in the cart.*/
    @RequestMapping(path = "/cart/items/{itemId}", method = RequestMethod.DELETE)
    public void removeItemFromCart(@PathVariable int itemId, Principal principal) {
        String username = principal.getName();
        User user = jdbcUserDao.findByUsername(username);

        jdbcCartItemDao.delete(itemId, user.getId());

    }

    /*DELETE /cart - clear cart (Required Use Case 7)
    * As a user I can clear my cart, removing all the items from the cart.*/
    @RequestMapping (path = "/cart", method = RequestMethod.DELETE)
    public void clearCart(Principal principal){
        String username = principal.getName();
        User user = jdbcUserDao.findByUsername(username);

        jdbcCartItemDao.clearItemsByUserId(user.getId());
    }
}
