package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcSaleDao implements SaleDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSaleDao(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Sale getSale(int saleId) {
        Sale sale = null;
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name as customer_name " +
                "FROM sale JOIN customer on customer.customer_id = sale.customer_id " +
                "where sale_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, saleId);
        if (rowSet.next()) {
            sale = mapRowToSale(rowSet);
        }
        return sale;
    }

    @Override
    public List<Sale> getSalesUnshipped() {
        String sql = "SELECT sale_id FROM sale WHERE ship_date = null;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<Sale> sales = new ArrayList<>();
        while (rowSet.next()) {
            Sale sale = mapRowToSale(rowSet);
            sales.add(sale);
        }
        return sales;
    }


    @Override
    public List<Sale> getSalesByCustomerId(int customerId) {
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name as customer_name FROM sale " +
                "join customer on sale.customer_id = customer.customer_id where sale.customer_id = ?";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, customerId);
        List<Sale> sales = new ArrayList<>();
        while (rowSet.next()) {
            Sale sale = mapRowToSale(rowSet);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public List<Sale> getSalesByProductId(int productId) {
        String sql = "SELECT sale_id, customer.customer_id, sale_date, ship_date, customer.name as customer_name from sale JOIN customer on sale.customer_id = customer.customer_id " +
                "WHERE sale_id in (SELECT distinct sale_id FROM line_item where product_id = ?);";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, productId);
        List<Sale> sales = new ArrayList<>();
        while (rowSet.next()) {
            Sale sale = mapRowToSale(rowSet);
            sales.add(sale);
        }
        return sales;
    }

    @Override
    public Sale createSale(Sale newSale) {
        String sql = "INSERT INTO sale (customer_id, sale_date, ship_date) " +
                "VALUES (?, ?, ?) RETURNING sale_id";

        int saleId = jdbcTemplate.queryForObject(sql, Integer.class, newSale.getCustomerName(),
                newSale.getSaleDate(), newSale.getShipDate());
        newSale.setSaleId(saleId);
        return newSale;
    }

    @Override
    public void updateSale(Sale updatedSale) {
        String sql = "UPDATE sale SET customer_id = ?, sale_date = ?, ship_date = ? " +
                "WHERE sale_id = ?;";
        jdbcTemplate.update(sql, updatedSale.getCustomerName(), updatedSale.getSaleDate(),
                updatedSale.getShipDate());

    }

    @Override
    public void deleteSale(int saleId) {
        String sql = "DELETE FROM sale where sale_id = ?;";
        jdbcTemplate.update(sql, saleId);


    }

    private Sale mapRowToSale(SqlRowSet rowSet) {
        Sale sale = new Sale();
        sale.setSaleId(rowSet.getInt("sale_id"));
        sale.setCustomerId(rowSet.getInt("customer_id"));
        sale.setCustomerName(rowSet.getString("customer_name"));
        //if (rowSet.getDate("sale_date") != null);
        sale.setSaleDate(rowSet.getDate("sale_date").toLocalDate());


        if (rowSet.getDate("ship_date") != null) {
            sale.setSaleDate(rowSet.getDate("ship_date").toLocalDate());
        }
        //sale.setCustomerName(rowSet.getString("customer_name"));
        return sale;

    }
}
