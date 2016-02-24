package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;

/**
 * @author Anna
 * Interface for service classes working with order
 */
public interface OrderService {

    Order placeNewOrder(Customer customer, Long... pizzaID);

    Order saveOrder(Order order);
    
    Order addPizzasToOrder(Order order, Long... pizzaID);
    
    void payForOrder(Order order);
    
    void setRates(Order order);
}
