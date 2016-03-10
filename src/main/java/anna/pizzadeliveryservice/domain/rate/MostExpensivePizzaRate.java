package anna.pizzadeliveryservice.domain.rate;

import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import org.springframework.stereotype.Component;

/**
 * @author Anna
 * Represents rate for order which has more then defined pizzas amount
 * Rate can be applied to the one of the most expensive pizza in order only
 */

@Component
public class MostExpensivePizzaRate implements Rate{
    
    private final int MIN_PIZZAS_COUNT = 4;
    private final int RATE_PERCENT = 30;
    
    private String name = "most expensive pizza rate";

    public MostExpensivePizzaRate() {
    }

    @Override
    public Integer giveRate(Order order) {
       int max = 0;
        if (order.getDetails().size() > MIN_PIZZAS_COUNT) {
            for (OrderDetail det : order.getDetails()) {
                if (max < det.getPrice()) {
                    max = det.getPrice();
                }
            }
        }
        return (max * RATE_PERCENT / 100);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "MostExpensivePizzaRate{" + "name=" + name + '}';
    } 
}
