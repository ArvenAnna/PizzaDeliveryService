package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Anna
 */

@Repository
public class JpaOrderRepository implements OrderRepository{

    @Override
    public Order save(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order update(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
