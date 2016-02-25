package anna.pizzadeliveryservice.service;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.domain.rate.Rate;
import anna.pizzadeliveryservice.exception.NoSuchEntityException;
import anna.pizzadeliveryservice.repository.OrderRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna
 * Implementation of service working with customer
 */

@Service
public class SimpleOrderService implements OrderService{

    private OrderRepository orderRepository;
    private PizzaService pizzaService;
    private CustomerService customerService;
    private List<Rate> rates;

    @Autowired
    public SimpleOrderService(OrderRepository orderRepository, PizzaService pizzaService, 
            CustomerService customerService, List<Rate> rates) {
        this.orderRepository = orderRepository;
        this.pizzaService = pizzaService;
        this.customerService = customerService;
        this.rates = rates;
    }

    @Override
    public Order placeNewOrder(Order order) {
        order.setStatus(Order.Status.NEW);
        order.setDate(new Date());
        return saveOrder(order);
    }
    
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.addNew(order);
    }

    @Override
    public Order addPizzasToOrder(Order order, Long... pizzaID) {
        if (order == null) {
            throw new NoSuchEntityException(Order.class);
        }
        for (Long id : pizzaID) {      
            order.addPizza(getPizzaById(id));
        }
        return order;
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

    @Override
    public void setRates(Order order) {
        order.setRates(rates);
    }

    @Override
    public Order removePizzaFromOrder(Order order, Long pizzaID) {
       order.removePizza(pizzaID);
       return order;
    }

}
