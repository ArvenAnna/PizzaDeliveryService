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
    public void giveCard(Customer customer) {
        if (customerRepository.findById(customer.getId()) != null) {
            customer.setCard(new Card());
        } else {
            throw new NoSuchEntityException(Customer.class);
        }
        customerRepository.update(customer);
    }

    @Override
    public Customer addSumToCard(Customer customer, int sum) {
        customer.addSumToCard(sum);
        return customerRepository.update(customer);
    }

    public Customer createCustomer(String name) {
        return new Customer(3L, name);
    }
}
