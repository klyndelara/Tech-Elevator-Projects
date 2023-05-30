package com.techelevator.ssgeek.dao;

import com.techelevator.ssgeek.model.Customer;

import com.techelevator.ssgeek.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class JdbcCustomerDaoTests extends BaseDaoTests {
    private static final Customer CUSTOMER_1 = new Customer(1, "Customer 1",
            "Addr 1-1", "Addr 1-2", "City 1", "S1", "11111");
    private static final Customer CUSTOMER_2 = new Customer(2, "Customer 2",
            "Addr 2-1", "Addr 2-2", "City 2", "S2", "22222");
    private static final Customer CUSTOMER_3 = new Customer(3, "Customer 3",
            "Addr 3-1", "Addr 3-2", "City 2", "S2", "33333");
    private static final Customer CUSTOMER_4 = new Customer(4, "Customer 4", "Addr 4-1",
            null, "City 4", "S4", "44444");


    private JdbcCustomerDao dao;
    private Customer testCustomer;

    @Before
    public void setup() {
        testCustomer = new Customer(1, "gfdgd", "dfgfg", "dsgds", "dsgsf", "ds", "fsfds");
        dao = new JdbcCustomerDao(dataSource);
    }

    @Test
    public void getCustomer_returns_correct_customer_id() {
        Customer customer = dao.getCustomer(1);
        Assert.assertNotNull(customer);
        assertCustomersMatch(CUSTOMER_1, customer);
    }

    @Test
    public void getCustomers_returns_list_of_correct_customer() {
        List<Customer> customers = dao.getCustomers();
        Assert.assertEquals(4, customers.size());
        assertCustomersMatch(CUSTOMER_1, customers.get(0));
        assertCustomersMatch(CUSTOMER_2, customers.get(1));
    }

    @Test
    public void createCustomer_return_customer_with_id_and_expected_values() {
        /*Customer createdCustomer = dao.createCustomer(testCustomer);

        Assert.assertNotNull("createCustomer returned null", createdCustomer);

        int newId = createdCustomer.getCustomerId();
        Assert.assertTrue("createCustomer failed to return a customer with an id", newId > 0);

        testCustomer.setCustomerId(newId);
        assertCustomersMatch(testCustomer, createdCustomer);*/

        /*Customer createdCustomer = dao.createCustomer(testCustomer);

        Integer newCustomerId = createdCustomer.getCustomerId();
        Assert.assertTrue("createCustomer failed to return a customer with an id",
                newCustomerId > 0);
        testCustomer.setCustomerId(newCustomerId);
        assertCustomersMatch(testCustomer, createdCustomer);*/
        Customer createdCustomer = dao.createCustomer(testCustomer);

        Assert.assertNotNull("createCustomer returned null", createdCustomer);

        int newId = createdCustomer.getCustomerId();
        Assert.assertTrue("createCustomer failed to return a customer with an id", newId > 0);

        testCustomer.setCustomerId(newId);
        assertCustomersMatch(testCustomer, createdCustomer);
    }

    @Test
    public void update_customer_has_expected_values_when_retrieved() {
        Customer updateCustomer = dao.getCustomer(1);
        updateCustomer.setCustomerId(updateCustomer.getCustomerId());
        updateCustomer.setName(updateCustomer.getName());
        updateCustomer.setStreetAddress1(updateCustomer.getStreetAddress1());
        updateCustomer.setStreetAddress2(updateCustomer.getStreetAddress2());
        updateCustomer.setCity(updateCustomer.getCity());
        updateCustomer.setState(updateCustomer.getState());
        updateCustomer.setZipCode(updateCustomer.getZipCode());

    }





    /*@Test
    public void deleted_customer_cant_be_retrieved() {
        dao.deleteCustomer(CUSTOMER_1.getCustomerId());
        Customer retrievedCustomer = dao.getCustomer(CUSTOMER_1.getCustomerId());
        Assert.assertNull(retrievedCustomer);*/


    private void assertCustomersMatch(Customer expected, Customer actual) {
        Assert.assertEquals(expected.getCustomerId(), actual.getCustomerId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getStreetAddress1(), actual.getStreetAddress1());
        Assert.assertEquals(expected.getStreetAddress2(), actual.getStreetAddress2());
        Assert.assertEquals(expected.getCity(), actual.getCity());
        Assert.assertEquals(expected.getState(), actual.getState());
        Assert.assertEquals(expected.getZipCode(), actual.getZipCode());

    }
}
