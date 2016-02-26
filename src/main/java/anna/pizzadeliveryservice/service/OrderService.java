package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;

/**
 * @author Anna
 * Interface for service classes working with order
 */
public interface OrderService {

    Order placeNewOrder(Order order);
    
    Order addPizzasToOrder(Order order, Long... pizzaID);
    
    Order removePizzaFromOrder(Order order, Long pizzaID);
    
    void setRates(Order order);
    
    Order addCustomerToOrderByLogin(Order order, String login);

}
