package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of order entity
 */
public interface OrderRepository {
    Order save(Order order);
    Order findById(Long id);
    Order update(Order order);
//    Order remove(Order order);
//    Order add(Order order);
//    Set<Pizza> findAll();
}
