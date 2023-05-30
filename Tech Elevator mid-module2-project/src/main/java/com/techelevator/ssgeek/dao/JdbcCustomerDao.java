package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcCustomerDao implements CustomerDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcCustomerDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Customer getCustomer(int customerId) {
        Customer customer = null;
        String sql = "Select * From customer Where customer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, customerId);
        if (rowSet.next()) {
            customer = mapRowToCustomer(rowSet);

        }

        return customer;
    }

    @Override
    public List<Customer> getCustomers() {
        String sql = "SELECT * FROM customer";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<Customer> customers = new ArrayList<>();
        while (rowSet.next()) {
            Customer customer = mapRowToCustomer(rowSet);
            customers.add(customer);
        }

        return customers;
    }

    @Override
    public Customer createCustomer(Customer newCustomer) {
         /*String sql = "INSERT INTO customer (name, street_address1, street_address2, city, state, zip_code) " +
                 "VALUES (?, ?, ?, ?, ?, ?) RETURNING customer_id;";

         int customerId = jdbcTemplate.queryForObject(sql, int.class,
                    newCustomer.getName(),
                    newCustomer.getStreetAddress1(),
                    newCustomer.getStreetAddress2(),
                    newCustomer.getCity(),
                    newCustomer.getState(),
                    newCustomer.getZipCode());

        return newCustomer;*/
        String sql = "INSERT INTO customer (name, street_address1, street_address2, city, state, zip_code) " +
                "VALUES (?, ?, ?, ?, ?, ?) RETURNING customer_id;";

        int customerId = jdbcTemplate.queryForObject(sql, int.class,
                newCustomer.getName(),
                newCustomer.getStreetAddress1(),
                newCustomer.getStreetAddress2(),
                newCustomer.getCity(),
                newCustomer.getState(),
                newCustomer.getZipCode());


        return getCustomer(customerId);
    }

    @Override
    public void updateCustomer(Customer updatedCustomer) {
        String sql = "UPDATE customer SET name = ?, street_address1 = ?, street_address2 = ?, " +
                "city = ?, state = ?, zip_code = ?  WHERE customer_id = ?";
        jdbcTemplate.update(sql,
                updatedCustomer.getName(),
                updatedCustomer.getStreetAddress1(),
                updatedCustomer.getStreetAddress2(),
                updatedCustomer.getCity(),
                updatedCustomer.getState(),
                updatedCustomer.getZipCode(),
                updatedCustomer.getCustomerId());

    }

    @Override
    public void deleteCustomer(int customerId) {
        String sql = "DELETE FROM customer WHERE customer_id = ?;";
        jdbcTemplate.update(sql, customerId);

    }


    private Customer mapRowToCustomer(SqlRowSet rowSet) {
        Customer customer = new Customer();
        customer.setCustomerId(rowSet.getInt("customer_id"));
        customer.setName(rowSet.getString("name"));
        customer.setStreetAddress1(rowSet.getString("street_address1"));
        customer.setStreetAddress2(rowSet.getString("street_address2"));
        customer.setCity(rowSet.getString("city"));
        customer.setState(rowSet.getString("state"));
        customer.setZipCode(rowSet.getString("zip_code"));
        return customer;

    }
}



