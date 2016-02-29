package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.repository.PizzaRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of service working with customer
 * @author Anna 
 */
@Service
public class SimplePizzaService implements PizzaService {

    private PizzaRepository pizzaRepository;
    private final int MIN_RANDOM_PIZZA_AMOUNT = 2;
    private final int MAX_RANDOM_PIZZA_AMOUNT = 4;

    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.findById(id);
    }

    @Override
    public Pizza addPizzaToMenu(Pizza pizza) {
        return pizzaRepository.addNew(pizza);
    }

    @Override
    public Set<Pizza> chooseRandomSomePizzas() {
        int limit = MIN_RANDOM_PIZZA_AMOUNT + (int) (Math.random()
                * ((MAX_RANDOM_PIZZA_AMOUNT - MIN_RANDOM_PIZZA_AMOUNT) + 1));
        return pizzaRepository.findSomeRandom(limit);
    }

    @Override
    public Set<Pizza> chooseAllAvailablePizzas() {
        return pizzaRepository.findAll();
    }

    @Override
    public Pizza removePizzaFromMenu(Long id) {
        Pizza pizza = pizzaRepository.findById(id);
        return pizzaRepository.remove(pizza);
    }

    @Override
    public Pizza changePizzaInformation(Pizza pizza) {
        return pizzaRepository.update(pizza);
    }
}
