package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Account;
import anna.pizzadeliveryservice.domain.Address;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integrational test for customer service
 *
 * @author Anna
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryContext.xml",
    "classpath:/applicationContext.xml"})
@Transactional()
@Rollback
public class SimpleCustomerServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private CustomerService cServ;
    @PersistenceContext
    private EntityManager em;

    public SimpleCustomerServiceIT() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findCustomerByLogin method, of class SimpleCustomerService.
     */
    @Test
    public void testFindCustomerByLogin() {
        System.out.println("findCustomerByLogin");
        String login = "vas";

        final String sql = "INSERT INTO account (id, username) VALUES "
                + "(nextval('account_ids'),'" + login + "')";
        Long accountId = returnInsertedIdKey(sql);

        final String sql2 = "INSERT INTO customer (id, account_id) VALUES "
                + "(nextval('customer_ids'), " + accountId + ")";
        Long expResult = returnInsertedIdKey(sql2);

        Customer customer = cServ.findCustomerByLogin(login);

        Long result = customer.getId();

        assertEquals(expResult, result);
    }

    /**
     * Test of placeNewCustomer method, of class SimpleCustomerService.
     */
    @Test
    public void testPlaceNewCustomer() {
        System.out.println("placeNewCustomer");
        Customer customer = new Customer();
        customer.setAccount(new Account());

        cServ.placeNewCustomer(customer);
        em.flush();
        
        final String sql = "SELECT * FROM customer c WHERE c.id = " + customer.getId();
        Customer result = jdbcTemplate.query(sql, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Customer customer = new Customer();
                rs.next();
                customer.setId(rs.getLong("id"));
                return customer;
            }
        });
        
        assertNotNull(result);
    }

    /**
     * Test of changeTelephoneByLogin method, of class SimpleCustomerService.
     */
    @Test
    public void testChangeTelephoneByLogin() {
        System.out.println("changeTelephoneByLogin");
        String tel = "300400";
        String expResult = "3490";
        String login = "vas";

        final String sql = "INSERT INTO account (id, username) VALUES "
                + "(nextval('account_ids'),'" + login + "')";
        Long accountId = returnInsertedIdKey(sql);

        final String sql2 = "INSERT INTO customer (id, account_id, tel) VALUES "
                + "(nextval('customer_ids'), " + accountId + ", '" + tel + "')";
        Long customerId = returnInsertedIdKey(sql2);

        Customer c = cServ.changeTelephoneByLogin(login, expResult);

        String result = c.getTel();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeAddressByLogin method, of class SimpleCustomerService.
     */
    @Test
    public void testChangeAddressByLogin() {
        System.out.println("changeAddressByLogin");

        Address address = new Address();
        String city = "Town";
        String expResult = "Kiev";
        address.setCity(expResult);
        String login = "vas";

        final String sql = "INSERT INTO account (id, username) VALUES "
                + "(nextval('account_ids'),'" + login + "')";
        Long accountId = returnInsertedIdKey(sql);
        final String sql2 = "INSERT INTO address (id, city) VALUES "
                + "(nextval('address_ids'), '" + city + "')";
        Long addressId = returnInsertedIdKey(sql2);
        final String sql3 = "INSERT INTO customer (id, account_id, address_id) VALUES "
                + "(nextval('customer_ids'), " + accountId + ", " + addressId + ")";
        Long customerId = returnInsertedIdKey(sql3);

        Customer c = cServ.changeAddressByLogin(login, address);

        String result = c.getAddress().getCity();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeCustomersInformation method, of class
     * SimpleCustomerService.
     */
    @Test
    public void testChangeCustomersInformation() {
        System.out.println("changeCustomersInformation");
        String tel = "2333";
        String expResult = "6060";

        final String sql = "INSERT INTO customer (id, tel) VALUES "
                + "(nextval('customer_ids'), '" + tel + "')";
        Long customerId = returnInsertedIdKey(sql);

        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setTel(expResult);

        cServ.changeCustomersInformation(customer);
        em.flush();

        final String sql2 = "SELECT * FROM customer c WHERE c.id = " + customerId;
        Customer c = jdbcTemplate.query(sql2, new ResultSetExtractor<Customer>() {
            @Override
            public Customer extractData(ResultSet rs) throws SQLException, DataAccessException {
                Customer customer = new Customer();
                rs.next();
                customer.setTel(rs.getString("tel"));
                return customer;
            }
        });

        String result = c.getTel();
        assertEquals(expResult, result);
    }

    private Long returnInsertedIdKey(final String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                return cnctn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        return (Long) keyHolder.getKeys().get("id");
    }

}
