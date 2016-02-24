package anna.pizzadeliveryservice.domain.rate;

import anna.pizzadeliveryservice.domain.Order;
import org.springframework.stereotype.Component;

/**
 * @author Anna
 * Represents rate according to sum on the discount card
 * Rate is confined by persent of order sum
 */

@Component
public class CardRate implements Rate{

    private final int RATE_PERCENT = 10;
    private final int MAX_ORDER_PERCENT_FOR_RATE = 30;

    public CardRate() {
    }
    
    
    @Override
    public Integer giveRate(Order order){
        int cardRate = 0;
        if(order.getCustomer()==null || order.getCustomer().getCard()==null){
            return 0;
        }
        Integer cardSum = order.getCustomer().getCard().getSum();
        if (cardSum != null) {
            cardRate = cardSum * RATE_PERCENT / 100;
            if (cardRate > order.getPureCost() * MAX_ORDER_PERCENT_FOR_RATE / 100) {
                cardRate = order.getPureCost() * MAX_ORDER_PERCENT_FOR_RATE / 100;
            }
        }
        return cardRate;
    }
}
