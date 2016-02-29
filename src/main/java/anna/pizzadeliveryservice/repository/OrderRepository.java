package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of order entity
 */
public interface OrderRepository extends BasicCrudOperations<Order>{
    
    /**
     * Finds orders of given customer, which have one of given statuses
     * @param customer given customer
     * @param status order statuses for seeking
     * @return order set
     */
    Set<Order> findByCustomerAndStatuses(Customer customer, Order.Status ... status);
    
    /**
     * Finds all orders having one of given statuses
     * @param status given statuses
     * @return order set
     */
    Set<Order> findByStatuses(Order.Status ... status);
}
