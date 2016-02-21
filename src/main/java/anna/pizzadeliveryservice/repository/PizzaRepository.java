package anna.pizzadeliveryservice.repository;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for repository classes of pizza entity
 */
public interface PizzaRepository extends BasicCrudOperations<Pizza>{
    Set<Pizza> findSomeRandom(int limit);
}
