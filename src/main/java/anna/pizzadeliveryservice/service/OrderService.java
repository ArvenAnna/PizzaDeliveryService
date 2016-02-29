package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.Set;

/**
 * @author Anna
 * Interface for service classes working with order
 */
public interface OrderService {

    /**
     * Saved order new or already existed
     * @param order given order
     * @return saved order
     */
    Order saveOrder(Order order);
    
    /**
     * Adds pizzas by thier id's to order
     * @param order given order
     * @param pizzaID pizza id's
     * @return 
     */
    Order addPizzasToOrder(Order order, Long... pizzaID);
    
    /**
     * Removes pizza by it's id from order
     * @param order given order
     * @param pizzaID id of pizza
     * @return 
     */
    Order removePizzaFromOrder(Order order, Long pizzaID);
    
    /**
     * Sets all available rates to order 
     * @param order order for rate setting
     */
    void setRates(Order order);
    
    /**
     * Adds customer by account login to order
     * @param order given order
     * @param login customer's account login
     * @return changed order
     */
    Order addCustomerToOrderByLogin(Order order, String login);
    
    /**
     * Adds customer to order 
     * @param order given order
     * @param customer given customer
     * @return changed order
     */
    Order addNewCustomerToOrder(Order order, Customer customer);
    
    /**
     * Finds all actual orders by given customer
     * @param customer given customer
     * @return order set
     */
    Set<Order> findAllCustomersActualOrders(Customer customer);
    
    /**
     * Fins all actual orders
     * @return order set
     */
    Set<Order> findAllActualOrders();
    
    /**
     * Changes status of order by it's id
     * @param orderId order id
     * @param status status for replacement
     * @return changed order
     */
    Order changeOrderStatus(Long orderId, Order.Status status);
    
    /**
     * Finds order by it's id
     * @param orderId order id
     * @return found order
     */
    Order findOrderById(Long orderId);

}
