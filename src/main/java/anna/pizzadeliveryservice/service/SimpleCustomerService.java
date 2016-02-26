package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Card;
import anna.pizzadeliveryservice.domain.Customer;
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

    public SimpleCustomerService() {
    }

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
        Card card = new Card();
        card.setSum(0);
        customer.setCard(card);
        customer.getAccount().setAvailability(true);
        Set<UserRole> roles = new HashSet<>();
        UserRole role = customerRepository.findUserRole("ROLE_USER");
        roles.add(role);
        customer.getAccount().setRoles(roles);
        return customerRepository.addNew(customer);
    }
}
