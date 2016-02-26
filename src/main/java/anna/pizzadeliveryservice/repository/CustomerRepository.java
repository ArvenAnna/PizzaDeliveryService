package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.UserRole;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of customer entity
 */
public interface CustomerRepository extends BasicCrudOperations<Customer>{

    Customer findByAccountLogin(String login);
    /**
     * Finds UserRole object using name of role
     * @param roleName - name of role
     * @return UserRole object corresponding to name of role
     */
    UserRole findUserRole(String roleName);
}
