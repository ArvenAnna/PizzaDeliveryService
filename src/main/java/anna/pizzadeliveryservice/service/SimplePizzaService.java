package anna.pizzadeliveryservice.service;


import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.repository.PizzaRepository;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Anna
 * Implementation of service working with customer
 */

@Service
public class SimplePizzaService implements PizzaService{
    
    private PizzaRepository pizzaRepository;
    private final int MIN_RANDOM_PIZZA_AMOUNT = 2;
    private final int MAX_RANDOM_PIZZA_AMOUNT = 4;
    
    @Autowired
    public SimplePizzaService(PizzaRepository pizzaRepository){
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza find(Long id) {
        return pizzaRepository.findById(id);
    } 
    
    
    
    @PostConstruct
    public void init(){
        System.out.println("I'm postconstruct");
    }

    @Override
    public void addPizza(Pizza pizza) {
        pizzaRepository.addNew(pizza);
    }

    @Override
    public Set<Pizza> chooseRandomSomePizzas() {
        int limit = MIN_RANDOM_PIZZA_AMOUNT + (int)(Math.random() * 
                ((MAX_RANDOM_PIZZA_AMOUNT - MIN_RANDOM_PIZZA_AMOUNT) + 1));
        return pizzaRepository.findSomeRandom(limit);
    }
}