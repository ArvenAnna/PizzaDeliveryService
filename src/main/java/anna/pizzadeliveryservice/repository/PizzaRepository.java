package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of pizza entity
 */
public interface PizzaRepository extends BasicCrudOperations<Pizza>{
    
    /**
     * Finds pizzas ordered by random and given count
     * @param limit count of found pizzas
     * @return pizza set
     */
    Set<Pizza> findSomeRandom(int limit);
}
