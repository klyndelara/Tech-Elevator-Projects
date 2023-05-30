package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;
import com.techelevator.ssgeek.model.Sale;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class JdbcSaleDaoTest extends  BaseDaoTests{

    private static final Sale SALE_1 = new Sale (1, 1,
            LocalDate.parse("2022-01-01"), null);
    private static final Sale SALE_2 = new Sale (2,1,
            LocalDate.parse("2022-02-01"),LocalDate.parse("2022-02-02"));
    private static final Sale SALE_3 = new Sale (3, 2,
            LocalDate.parse("2022-03-01"), null);
    private static final Sale SALE_4 = new Sale(4, 2,
            LocalDate.parse("2022-01-02"), LocalDate.parse("2022-01-02"));

    private JdbcSaleDao dao;
    private Sale testSale;

    @Before
    public void setup () {
        testSale = new Sale (1 , 1 , LocalDate.parse("2023-03-30"), LocalDate.parse("2023-02-02"));
        dao = new JdbcSaleDao(dataSource);
    }
    @Test
    public void getSale_returns_correct_sale_id() {
        Sale sale = dao.getSale(1);
        Assert.assertNotNull(sale);
        assertSaleMatch(SALE_1, sale);

    }

private void assertSaleMatch(Sale expected, Sale actual) {
        Assert.assertEquals(expected.getSaleId(),actual.getSaleId());
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());

        Assert.assertEquals(expected.getSaleId(), actual.getSaleId());
        Assert.assertEquals(expected.getSaleDate(),actual.getSaleDate());
        Assert.assertEquals(expected.getShipDate(), actual.getShipDate());

}
}