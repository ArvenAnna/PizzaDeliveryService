package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of order repository for JPA
 * @author Anna
 */
@Repository
@Transactional
public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order findById(Long id) {
        TypedQuery<Order> query = em.createNamedQuery("Order.findById", Order.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Order update(Order order) {
        return em.merge(order);
    }

    @Override
    public Order remove(Order entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addNew(Order order) {
        em.persist(order);
        return order;
    }

    @Override
    public Order addOrUpdate(Order order) {
        if (order.getId() == null) {
            return addNew(order);
        } else {
            return update(order);
        }
    }

    @Override
    public Set<Order> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<Order> findByCustomerAndStatuses(Customer customer, Order.Status... status) {
        Set<Order> orders = new HashSet<>();
        for (Order.Status st : status) {
            TypedQuery<Order> query = em.createNamedQuery("Order.findByCustomerAndStatus", Order.class);
            query.setParameter("status", st);
            query.setParameter("customer", customer);
            orders.addAll(query.getResultList());
        }
        return orders;
    }

    @Override
    public Set<Order> findByStatuses(Order.Status... status) {
        Set<Order> orders = new HashSet<>();
        for (Order.Status st : status) {
            TypedQuery<Order> query = em.createNamedQuery("Order.findByStatus", Order.class);
            query.setParameter("status", st);
            orders.addAll(query.getResultList());
        }
        return orders;
    }

}
