package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anna
 */

@Repository
public class JpaCustomerRepository implements CustomerRepository{

    @Override
    public Customer update(Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
