package anna.pizzadeliveryservice.exception;

/**
 * @author Anna
 * Exception occurs if there is no given entity in repositry but need to work with him
 */
public class NoSuchEntityException extends RuntimeException{
    
    private Class entity;

    public NoSuchEntityException(Class entity) {
        super();
        this.entity = entity; 
    }
    
    @Override
    public String getMessage() {
        return "You are trying to do something with non-existing entity of class " + 
                entity.getSimpleName();
    }
}
