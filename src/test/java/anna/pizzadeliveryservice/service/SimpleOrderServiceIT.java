package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Account;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import anna.pizzadeliveryservice.domain.Pizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Set;
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
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integrational test for order service
 * @author Anna
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryContext.xml",
    "classpath:/applicationContext.xml"})
@Transactional()
@Rollback
public class SimpleOrderServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private OrderService oServ;
    @PersistenceContext
    private EntityManager em;

    public SimpleOrderServiceIT() {
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
     * Test of saveOrder method, of class SimpleOrderService.
     */
    @Test
    public void testSaveOrder() {
        System.out.println("saveOrder");
        Order order = new Order();

        Long orderId = oServ.saveOrder(order).getId();

        final String sql = "SELECT id FROM pizzaorder o WHERE o.id = " + orderId;
        Order result = jdbcTemplate.query(sql, new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                Order order = new Order();
                rs.next();
                order.setId(rs.getLong("id"));
                return order;
            }
        });    
        assertNotNull(result);
    }

    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService.
     */
    @Test
    public void testAddPizzasToOrder() {
        System.out.println("addPizzasToOrder");
        Order order = new Order();
        final String sql = "INSERT INTO pizza (id) VALUES "
                + "(nextval('pizza_ids'))";
        Long pizzaID = returnInsertedIdKey(sql);
        
        Order resOrder = oServ.addPizzasToOrder(order, pizzaID);
        List<OrderDetail> result = resOrder.getDetails();
        assertNotNull(result);
    }

    /**
     * Test of setRates method, of class SimpleOrderService.
     */
    @Test
    public void testSetRates() {
        System.out.println("setRates");
        Order order = new Order();
        oServ.setRates(order);
        assertNotNull(order.getRates());
    }

    /**
     * Test of removePizzaFromOrder method, of class SimpleOrderService.
     */
    @Test
    public void testRemovePizzaFromOrder() {
        System.out.println("removePizzaFromOrder");
        Order order = new Order();
        Long pizzaID = 23L;
        order.addPizza(new Pizza(pizzaID, "Vas"));

        Order expResult = new Order();
        
        Order result = oServ.removePizzaFromOrder(order, pizzaID);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of addCustomerToOrderByLogin method, of class SimpleOrderService.
     */
    @Test
    public void testAddCustomerToOrderByLogin() {
        System.out.println("addCustomerToOrderByLogin");

        Order order = new Order();
        String login = "vas";

        final String sql = "INSERT INTO account (id, username) VALUES "
                + "(nextval('account_ids'),'" + login + "')";
        Long accountId = returnInsertedIdKey(sql);
        final String sql2 = "INSERT INTO customer (id, account_id) VALUES "
                + "(nextval('customer_ids'), " + accountId + ")";
        Long customerId = returnInsertedIdKey(sql2);
        final String sql3 = "INSERT INTO pizzaorder (id, customer_id) VALUES "
                + "(nextval('order_ids'), " + customerId + ")";
        Long orderId = returnInsertedIdKey(sql3);

        Order resOrder = oServ.addCustomerToOrderByLogin(order, login);
        Customer result = resOrder.getCustomer();
        assertNotNull(result);
    }


    /**
     * Test of addNewCustomerToOrder method, of class SimpleOrderService.
     */
    @Test
    public void testAddNewCustomerToOrder() {
        System.out.println("addNewCustomerToOrder");

        Customer customer = new Customer();
        Account account = new Account();
        customer.setAccount(account);
        customer.setName("Vasya");

        Order expResult = new Order();
        expResult.setCustomer(customer);

        Order result = new Order();
        oServ.addNewCustomerToOrder(result, customer);

        assertEquals(expResult, result);
    }

    /**
     * Test of findAllCustomersActualOrders method, of class SimpleOrderService.
     */
    @Test
    public void testFindAllCustomersActualOrders() {
        System.out.println("findAllCustomersActualOrders");
        
        final String sql = "INSERT INTO customer (id, name, tel) VALUES "
                + "(nextval('customer_ids'), 'Vasya', 3243214)";
        Long custId = returnInsertedIdKey(sql);

        final String sql2 = "INSERT INTO pizzaorder (id, customer_id, status) VALUES "
                + "(nextval('order_ids'), " + custId + ", 'NEW')";
        Long expectedOrderId = returnInsertedIdKey(sql2);

        int expSize = 1;
        Customer customer = new Customer();
        customer.setId(custId);
        Set<Order> orders = oServ.findAllCustomersActualOrders(customer);
        int resultSize = orders.size();
        Long result = null;
        for (Order ord : orders) {
            result = ord.getId();
        }
        assertEquals(result, expectedOrderId);
        assertEquals(expSize, resultSize);
    }

    /**
     * Test of findAllActualOrders method, of class SimpleOrderService.
     */
    @Test
    public void testFindAllActualOrders() {
        System.out.println("findAllActualOrders");
        final String sql = "SELECT * FROM pizzaorder o WHERE o.status = 'IN_PROGRSS' OR o.status = 'NEW' ";
        List<Order> orders;
        orders = jdbcTemplate.query(sql,
                new RowMapper<Order>() {
            @Override
            public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rowNum);
                return new Order(rs.getLong("id"));
            }
        });
        int expResult = orders.size();
        int result = oServ.findAllActualOrders().size();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeOrderStatus method, of class SimpleOrderService.
     */
    @Test
    public void testChangeOrderStatus() {
        System.out.println("changeOrderStatus");
        final String sql = "INSERT INTO pizzaorder (id, status) VALUES (nextval('order_ids'), 'NEW')";
        Long orderId = returnInsertedIdKey(sql);

        Order.Status expResult = Order.Status.IN_PROGRSS;
        oServ.changeOrderStatus(orderId, expResult);
        em.flush();

        final String sql2 = "SELECT * FROM pizzaorder o WHERE o.id = " + orderId;

        Order order = jdbcTemplate.query(sql2, new ResultSetExtractor<Order>() {
            @Override
            public Order extractData(ResultSet rs) throws SQLException, DataAccessException {
                Order order = new Order();
                rs.next();
                order.setStatus(Order.Status.valueOf(rs.getString("status")));
                return order;
            }
        });

        Order.Status result = order.getStatus();
        assertEquals(expResult, result);
    }

    /**
     * Test of findOrderById method, of class SimpleOrderService.
     */
    @Test
    public void testFindOrderById() {
        System.out.println("findOrderById");
        final String sql = "INSERT INTO pizzaorder (id) VALUES (nextval('order_ids'))";       
        long id = returnInsertedIdKey(sql);
        Order result = oServ.findOrderById(id);
        assertNotNull(result);
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
