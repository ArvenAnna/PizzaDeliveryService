package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;

/**
 * @author Anna
 * Interface for service classes working with customer
 */
public interface CustomerService {
    
    void giveCard(Customer customer);
    
    Customer addSumToCard(Customer customer, int sum);
    
    Customer createCustomer(String name);
}
