package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.LineItem;

import com.techelevator.ssgeek.model.Sale;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;






import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcLineItemDao implements LineItemDao {
    /**
     * JDBC template used to access database throughout this DAO
     */
    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor
     *
     * @param dataSource Data source from main application
     */

    public JdbcLineItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }



    @Override
    public List<LineItem> getLineItemsBySale(int saleId) {
        String sql = "Select line_item_id, sale_id, line_item.product_id, quantity, product.name as product_name, price  " +
                "from line_item " +
                "join product on line_item.product_id = product.product_id " +
                "where sale_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, saleId);
        List <LineItem> lineItems = new ArrayList<>();
        while(rowSet.next()) {
            LineItem lineItem = mapRowToLineItem(rowSet);
            lineItems.add(lineItem);
        }
        return lineItems;
    }


    private LineItem mapRowToLineItem (SqlRowSet rowSet) {
        LineItem lineItem = new LineItem();
        lineItem.setLineItemId(rowSet.getInt("line_item_id"));
        lineItem.setSaleId(rowSet.getInt("sale_id"));
        lineItem.setProductId(rowSet.getInt("product_id"));
        lineItem.setQuantity(rowSet.getInt("quantity"));
        lineItem.setPrice(rowSet.getBigDecimal("price"));
        lineItem.setProductName(rowSet.getString("product_name"));
        return lineItem;
    }


    /**
     * Get all line items associated with a sale, ordered by line_item_id.
     *
     * @param saleId The id of the sale to get line items from.
     * @return All line items for a sale as LineItem objects in a List.
     */



}
