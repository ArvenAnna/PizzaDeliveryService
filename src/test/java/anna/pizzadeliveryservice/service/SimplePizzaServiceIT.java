package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Pizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integrational test for pizza service
 * @author Anna
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/repositoryContext.xml",
    "classpath:/applicationContext.xml"})
@Transactional()
@Rollback
public class SimplePizzaServiceIT extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private PizzaService pserv;
    @PersistenceContext
    private EntityManager em;

    public SimplePizzaServiceIT() {
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
     * Test of find method, of class SimplePizzaService.
     */
    @Test
    public void testFind() {
        System.out.println("find");
        final String sql = "INSERT INTO pizza (name, price) VALUES ('Vasya', 123)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                return cnctn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        long id = (Long) keyHolder.getKeys().get("id");
        Pizza result = pserv.find(id);
        assertNotNull(result);
    }

    /**
     * Test of addPizzaToMenu method, of class SimplePizzaService.
     */
    @Test
    public void testAddPizzaToMenu() {
        System.out.println("addPizzaToMenu");
        Pizza pizza = new Pizza();
        pizza.setName("Margarita");
        Pizza result = pserv.addPizzaToMenu(pizza);
        em.flush();
        assertNotNull(result.getId());
    }

    /**
     * Test of chooseRandomSomePizzas method, of class SimplePizzaService.
     */
    @Test
    public void testChooseRandomSomePizzas() {
        System.out.println("chooseRandomSomePizzas");
        Set<Pizza> result = pserv.chooseRandomSomePizzas();
        assertTrue(result.size() > 1);
    }

    /**
     * Test of chooseAllAvailablePizzas method, of class SimplePizzaService.
     */
    @Test
    public void testChooseAllAvailablePizzas() {
        System.out.println("chooseAllAvailablePizzas");
        final String sql = "SELECT * FROM pizza";
        List<Pizza> pizzas;
        pizzas = jdbcTemplate.query(sql,
                new RowMapper<Pizza>() {
            @Override
            public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
                System.out.println(rowNum);
                return new Pizza(rs.getLong("id"), rs.getString("name"),
                        Pizza.PizzaType.valueOf(rs.getString("pizzaType")),
                        rs.getInt("price"), rs.getString("description"), rs.getString("foto"));
            }
        });
        Set<Pizza> expResult = new HashSet(pizzas);
        Set<Pizza> result = pserv.chooseAllAvailablePizzas();
        assertEquals(expResult, result);
    }

    /**
     * Test of removePizzaFromMenu method, of class SimplePizzaService.
     */
    @Test(expected = NoResultException.class)
    public void testRemovePizzaFromMenu() {
        System.out.println("removePizzaFromMenu");
        final String sql = "INSERT INTO pizza (name, price) VALUES ('Vasya', 123)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection cnctn) throws SQLException {
                return cnctn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, keyHolder);
        long id = (Long) keyHolder.getKeys().get("id");
        pserv.removePizzaFromMenu(id);
        Pizza result = pserv.find(id);
    }

    /**
     * Test of changePizzaInformation method, of class SimplePizzaService.
     */
    @Test
    public void testChangePizzaInformation() {
        System.out.println("changePizzaInformation");
        Pizza pizza = new Pizza();
        String expResult = "Furia";
        pizza.setName("Vasya");
        pizza = pserv.addPizzaToMenu(pizza);
        long id = pizza.getId();
        pizza.setName(expResult);
        pizza = pserv.changePizzaInformation(pizza);
        String result = pserv.find(id).getName();
        assertEquals(expResult, result);
    }
}
