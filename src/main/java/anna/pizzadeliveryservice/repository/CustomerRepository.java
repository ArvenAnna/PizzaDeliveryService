package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of customer entity
 */
public interface CustomerRepository extends BasicCrudOperations<Customer>{

    Customer findByAccountLogin(String login);
}
