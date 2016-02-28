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
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Anna Implementation of service working with customer
 */
@Service
public class SimpleOrderService implements OrderService {

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
    public Order saveOrder(Order order) {
        order.setStatus(Order.Status.NEW);
        order.setDate(new Date());
        return orderRepository.addOrUpdate(order);
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

    @Override
    public Set<Order> findAllCustomersActualOrders(Customer customer) {
        Set<Order> orders = orderRepository.findByCustomerAndStatuses(customer, Order.Status.NEW, Order.Status.IN_PROGRSS);
        for (Order order : orders) {
            setRates(order);
        }
        return orders;
    }

    @Override
    public Set<Order> findAllActualOrders() {
        Set<Order> orders = orderRepository.findByStatuses(Order.Status.NEW, Order.Status.IN_PROGRSS);
        for (Order order : orders) {
            setRates(order);
        }
        return orders;
    }

    @Override
    public Order changeOrderStatus(Long orderId, Order.Status status) {
        
        Order order = orderRepository.findById(orderId);
        
        if(status.equals(Order.Status.DONE)){
            Customer customer = order.getCustomer();
            customer.getCard().setSum(customer.getCard().getSum() + order.getRateCost());
            customerService.changeCustomersInformation(customer);
            order.setCost(order.getRateCost());
        }
        
        order.setStatus(status);
        order = orderRepository.update(order);
        setRates(order);
        return order;
    }

    @Override
    public Order findOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        setRates(order);
        return order;
    }

}
