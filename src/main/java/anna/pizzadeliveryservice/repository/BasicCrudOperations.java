package anna.pizzadeliveryservice.repository;

import java.util.Set;

/**
 * Interface for common CRUD operation for Entity of type T
 * @author Anna
 */
public interface BasicCrudOperations<T> {
    
    /**
     * Read entity by it's id
     * @param id given id
     * @return entity
     */
    T findById(Long id);
    
    /**
     * Delete entity
     * @param entity given entity
     * @return removed entity
     */
    T remove(T entity);
    
    /**
     * Insert new entity
     * @param entity given entity
     * @return created entity
     */
    T addNew(T entity);
    
    /**
     * Insert entity if it hasn't id or update otherwise
     * @param entity given entity
     * @return created or updated entity
     */
    T addOrUpdate(T entity);
    
    /**
     * Update entity
     * @param entity given entity
     * @return updated entity
     */
    T update(T entity);
    
    /**
     * Read all records in entity table
     * @return all entities of type T as Set
     */
    Set<T> findAll();
}
