package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.UserRole;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 *
 * @author Anna
 */

@Repository
@Transactional
public class JpaCustomerRepository implements CustomerRepository{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Customer findByAccountLogin(String login) {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByLogin", Customer.class);
        query.setParameter("login", login);
        List<Customer> elementList = query.getResultList();
        return CollectionUtils.isEmpty(elementList) ? null : elementList.get(0);
    }

    @Override
    public Customer findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer remove(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer addNew(Customer customer) {
        em.persist(customer);
        return customer;
    }

    @Override
    public Customer addOrUpdate(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Customer update(Customer entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Customer> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRole findUserRole(String roleName) {
        TypedQuery<UserRole> query = em.createNamedQuery("UserRole.findByRoleName", UserRole.class);
        query.setParameter("role", roleName);
        return query.getSingleResult();
    }

    
}
