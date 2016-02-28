package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of order entity
 */
public interface OrderRepository extends BasicCrudOperations<Order>{
    Set<Order> findByCustomerAndStatuses(Customer customer, Order.Status ... status);
    Set<Order> findByStatuses(Order.Status ... status);
}
