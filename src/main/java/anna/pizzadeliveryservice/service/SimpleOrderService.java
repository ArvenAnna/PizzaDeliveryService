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
        return orderRepository.addNew(order);
    }

    @Override
    public Order addPizzasToOrder(Order order, Long... pizzaID) {
        if (order == null) {
            throw new NoSuchEntityException(Order.class);
        }
        for (Long id : pizzaID) {      
            order.addPizza(pizzaService.find(id));
        }
        return order;
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

    @Override
    public Order addCustomerToOrderByLogin(Order order, String login) {
       Customer customer = customerService.findCustomerByLogin(login);
       order.setCustomer(customer);
       return order;
    }

    @Override
    public Order addNewCustomerToOrder(Order order, Customer customer) {
        Customer newCustomer = customerService.placeNewCustomer(customer);
        order.setCustomer(newCustomer);
        return order;
    }

}
