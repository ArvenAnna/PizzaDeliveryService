package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.Set;

/**
 * @author Anna
 * Interface for service classes working with order
 */
public interface OrderService {

    Order saveOrder(Order order);
    
    Order addPizzasToOrder(Order order, Long... pizzaID);
    
    Order removePizzaFromOrder(Order order, Long pizzaID);
    
    void setRates(Order order);
    
    Order addCustomerToOrderByLogin(Order order, String login);
    
    Order addNewCustomerToOrder(Order order, Customer customer);
    
    Set<Order> findAllCustomersActualOrders(Customer customer);
    
    Set<Order> findAllActualOrders();
    
    Order changeOrderStatus(Long orderId, Order.Status status);
    
    Order findOrderById(Long orderId);

}
