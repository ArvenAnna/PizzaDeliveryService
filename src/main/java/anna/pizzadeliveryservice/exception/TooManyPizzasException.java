package anna.pizzadeliveryservice.exception;

/**
 * @author Anna
 * Exception occurs if order is created with more then maximal pizza's count
 */
public class TooManyPizzasException extends RuntimeException{

    @Override
    public String getMessage() {
        return "Order can't has so many pizzas";
    }

    public TooManyPizzasException() {
        super();
    }
    
}
