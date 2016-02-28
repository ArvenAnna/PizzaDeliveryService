package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Address;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.Set;

/**
 * @author Anna
 * Interface for service classes working with customer
 */
public interface CustomerService {
    
    Customer findCustomerByLogin(String login);
    
    Customer placeNewCustomer(Customer customer);
    
    Customer changeTelephoneByLogin(String login, String tel);
    
    Customer changeAddressByLogin(String login, Address address);
    
    Customer changeCustomersInformation(Customer customer);
 
}
