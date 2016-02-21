package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.exception.NoSuchEntityException;
import anna.pizzadeliveryservice.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna
 * Implementation of service working with customer
 */

@Service
public class SimpleOrderService implements OrderService{

    private static Long orderCount = 0L;

    private OrderRepository orderRepository;
    private PizzaService pizzaService;
    private CustomerService customerService;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.customerService = customerService;
    }

    @Override
    public Order placeNewOrder(Customer customer, Long... pizzaID) {
        orderCount++;

        List<OrderDetail> details = new ArrayList<>();
        
        for (Long id : pizzaID) {
            details.add(new OrderDetail(getPizzaById(id)));
        }

        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setDetails(details);
        newOrder.setId(orderCount);
        newOrder.setStatus(Order.Status.NEW);
        
        saveOrder(newOrder);
        return newOrder;
    }
    
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addPizzasToOrder(Order order, Long... pizzaID) {
        if (order == null) {
            throw new NoSuchEntityException(Order.class);
        }

        List<Pizza> pizzas = new ArrayList<Pizza>();
        int i =0;
        for (Long id : pizzaID) {
            
            pizzas.add(getPizzaById(id));
            System.out.println(pizzas.get(i));
            i++;
        }
        
        order.addMorePizzas(pizzas);
        order.setStatus(Order.Status.IN_PROGRSS);
        return orderRepository.update(order);
    }
    
    @Override
    public void payForOrder(Order order){
        order.setStatus(Order.Status.DONE);
        orderRepository.update(order);
        if(order.getCustomer().getCard()!=null){
            customerService.addSumToCard(order.getCustomer(), order.getPureCost());
        }     
    }

    private Pizza getPizzaById(Long id) {
        return pizzaService.find(id);
    }

}
