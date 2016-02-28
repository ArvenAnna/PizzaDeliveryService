package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Address;
import anna.pizzadeliveryservice.domain.Card;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.UserRole;
import anna.pizzadeliveryservice.exception.NoSuchEntityException;
import anna.pizzadeliveryservice.repository.CustomerRepository;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna
 * Implementation of service working with customer
 */

@Service
public class SimpleCustomerService implements CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public SimpleCustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer findCustomerByLogin(String login) {
        return customerRepository.findByAccountLogin(login);
    }

    @Override
    public Customer placeNewCustomer(Customer customer) {
        customer.setCard(new Card());
        customer.getCard().setSum(0);
        customer.getAccount().setAvailability(true);
        Set<UserRole> roles = new HashSet<>();
        UserRole role = customerRepository.findUserRole("ROLE_CUSTOMER");
        roles.add(role);
        customer.getAccount().setRoles(roles);
        return customerRepository.addNew(customer);
    }

    @Override
    public Customer changeTelephoneByLogin(String login, String tel) {
        Customer customer = findCustomerByLogin(login);
        customer.setTel(tel);
        return customerRepository.update(customer);
    }

    @Override
    public Customer changeAddressByLogin(String login, Address address) {
        Customer customer = findCustomerByLogin(login);
        customer.setAddress(address);
        return customerRepository.update(customer);
    }

}
