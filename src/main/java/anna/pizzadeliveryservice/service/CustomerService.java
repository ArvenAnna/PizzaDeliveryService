package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Address;
import anna.pizzadeliveryservice.domain.Customer;

/**
 * @author Anna
 * Interface for service classes working with customer
 */
public interface CustomerService {
    
    /**
     * Finds customer by account login, i.e customer email
     * @param login customer's login
     * @return found customer
     */
    Customer findCustomerByLogin(String login);
    
    /**
     * Saves information about new customer 
     * @param customer new customer
     * @return saved customer
     */
    Customer placeNewCustomer(Customer customer);
    
    /**
     * Changes customer's telephone by account login
     * @param login given login
     * @param tel new telephone
     * @return customer with changed data
     */
    Customer changeTelephoneByLogin(String login, String tel);
    
    /**
     * Changes customer's address by account login
     * @param login given login
     * @param address new address
     * @return customer with changed data
     */
    Customer changeAddressByLogin(String login, Address address);
    
    /**
     * Changes any customer's information
     * @param customer customer who must be
     * @return changed customer
     */
    Customer changeCustomersInformation(Customer customer);
 
}
