package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Card;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.exception.NoSuchEntityException;
import anna.pizzadeliveryservice.repository.CustomerRepository;
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
}
