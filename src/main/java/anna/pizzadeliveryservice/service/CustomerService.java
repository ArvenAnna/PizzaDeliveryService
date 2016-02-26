package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;

/**
 * @author Anna
 * Interface for service classes working with customer
 */
public interface CustomerService {
    
    Customer findCustomerByLogin(String login);
}
