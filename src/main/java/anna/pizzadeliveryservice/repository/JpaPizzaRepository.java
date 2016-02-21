package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

//перевести енам из джавы в БД


/**
 * Implementation of pizza repository for JPA
 * @author Anna
 */
@Repository
@Transactional
public class JpaPizzaRepository implements PizzaRepository{
    
    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly = true)
    @Override
    public Pizza findById(Long id) {
        TypedQuery<Pizza> query = em.createNamedQuery("Pizza.findById", Pizza.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Pizza remove(Pizza pizza) {
        Pizza p = em.merge(pizza);
        em.remove(p);
        return pizza;
    }

    @Override
    public Pizza addOrUpdate(Pizza pizza) {
        if(pizza.getId() == null){
            em.persist(pizza);
        }else{
            em.merge(pizza);
        }
        return pizza;
    }

    @Override
    public Pizza update(Pizza pizza) {
        return em.merge(pizza);
    }

    @Transactional(readOnly = true)
    @Override
    public Set<Pizza> findAll() {
        TypedQuery<Pizza> query = em.createNamedQuery("Pizza.findAll", Pizza.class);
        return new HashSet<Pizza>(query.getResultList());
    }

    @Override
    public Pizza addNew(Pizza pizza) {
        em.persist(pizza);
        return pizza;
    }

    @Override
    public Set<Pizza> findSomeRandom(int limit) {
        TypedQuery<Pizza> query = em.createNamedQuery("Pizza.findSomeRandom", Pizza.class);
        query.setMaxResults(limit);
        return new HashSet<Pizza>(query.getResultList());
    }
    
}
