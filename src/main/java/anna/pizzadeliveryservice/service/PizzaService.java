package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for service classes working with pizza
 */
public interface PizzaService {
    void addPizza(Pizza pizza);
    Pizza find(Long id);
    Set<Pizza> chooseRandomSomePizzas();
    Set<Pizza> chooseAllAvailablePizzas();
}
