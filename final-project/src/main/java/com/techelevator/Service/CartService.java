package com.techelevator.Service;

import com.techelevator.dao.*;
import com.techelevator.model.*;
import org.springframework.stereotype.Component;

@Component
public class CartService {
    private JdbcCartItemDao jdbcCartItemDao;
    private JdbcUserDao jdbcUserDao;
    private JdbcProductDao jdbcProductDao;
    private TaxService taxService;

    public void CartDto (JdbcCartItemDao jdbcCartItemDao, JdbcUserDao jdbcUserDao, JdbcProductDao jdbcProductDao, TaxService taxService){
        this.jdbcCartItemDao = jdbcCartItemDao;
        this.jdbcUserDao = jdbcUserDao;
        this.jdbcProductDao = jdbcProductDao;
    }
   // public CartDto getUserCart (String username){






    //getusercart method
    //clearcart method

}