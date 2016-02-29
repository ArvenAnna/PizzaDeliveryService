package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Set;

/**
 * @author Anna
 * Interface for service classes working with pizza
 */
public interface PizzaService {
    /**
     * Adds new pizza to menu
     * @param pizza pizza for adding
     * @return added pizza
     */
    Pizza addPizzaToMenu(Pizza pizza);
    
    /**
     * Finds pizza by it's id
     * @param id 
     * @return found pizza
     */
    Pizza find(Long id);
    
    /**
     * Finds some several pizza by random selecting of non-determinated count
     * @return set of choosen pizzas
     */
    Set<Pizza> chooseRandomSomePizzas();
    
    /**
     * Finds all pizzas
     * @return set of pizzas
     */
    Set<Pizza> chooseAllAvailablePizzas();
    
    /**
     * Deletes pizza from menu by it's id
     * @param id 
     * @return removed pizza
     */
    Pizza removePizzaFromMenu(Long id);
    
    /**
     * Changes information about given pizza
     * @param pizza pizza that must be
     * @return pizza after changing
     */
    Pizza changePizzaInformation(Pizza pizza);
}
