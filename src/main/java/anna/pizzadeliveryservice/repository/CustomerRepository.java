package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of customer entity
 */
public interface CustomerRepository {
    Customer update(Customer customer);
    Customer findById(Long id);
//    Customer remove(Customer customer);
//    Set<Customer> findAll();
//    Customer add(Customer customer);
}
