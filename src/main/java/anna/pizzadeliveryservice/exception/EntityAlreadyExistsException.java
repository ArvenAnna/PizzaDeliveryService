package anna.pizzadeliveryservice.exception;

/**
 *
 * @author Anna
 * Exception occurs if you are trying to add existing in the repository entity
 */
public class EntityAlreadyExistsException extends RuntimeException{
    
    private Class entity;

    public EntityAlreadyExistsException(Class entity) {
        super();
        this.entity = entity;   
    }

    @Override
    public String getMessage() {
        return "You are trying to add existing entity of class " + 
                entity.getSimpleName() + " to repository";
    } 
  
}
