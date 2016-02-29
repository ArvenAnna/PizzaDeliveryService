package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.exception.TooManyPizzasException;
import anna.pizzadeliveryservice.service.CustomerService;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.validator.CustomerValidator;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.security.Principal;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

/**
 * Controller for order proccessing
 * @author Anna
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

    private CustomerService customerServ;
    private OrderService orderServ;
    @Autowired
    @Qualifier("authenticationManager")
    private AuthenticationManager authenticationManager;
    private CustomerValidator accountValidator;

    @Autowired
    public OrderController(CustomerService customerServ, OrderService orderServ,
            CustomerValidator accountValidator) {
        this.customerServ = customerServ;
        this.orderServ = orderServ;
        this.accountValidator = accountValidator;
    }

    @ModelAttribute(value = "customer")
    public Customer addCustomerToModel() {
        return new Customer();
    }

    @RequestMapping(value = "/addpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> addPizzaToOrder(@PathVariable String id, HttpSession session,
            Principal principal) {
        Map<String, Object> json = new HashMap<>();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
            order = new Order();
            orderServ.setRates(order);
        }
        if (order.getCustomer() == null) {
            if (principal != null) {
                orderServ.addCustomerToOrderByLogin(order, principal.getName());
            }
        }
        try {
            orderServ.addPizzasToOrder(order, Long.parseLong(id));
            session.setAttribute("order", order);
        } catch (TooManyPizzasException e) {
            json.put("exception", "TooManyPizzaException");
        }
        json.put("order", order);
        return json;
    }

    @RequestMapping(value = "/showorder", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> showOrder(HttpSession session) {
        Map<String, Object> json = new HashMap<>();
        Order order = (Order) session.getAttribute("order");
        if (order == null) {
        } else {
            json.put("order", order);
        }
        return json;
    }

    @RequestMapping(value = "/delpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> delPizzaFromOrder(@PathVariable String id,
            Principal principal, HttpSession session) {
        Map<String, Object> json = new HashMap<>();
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            if (order.getCustomer() == null) {
                if (principal != null) {
                    orderServ.addCustomerToOrderByLogin(order, principal.getName());
                }
            }
        } else {
            json.put("exception", "DeletingWithoutCreatingOrder");
            return json;
        }
        if (order.getDetails().isEmpty()) {
            json.put("exception", "DeletingEmptyList");
        } else {
            orderServ.removePizzaFromOrder(order, Long.parseLong(id));
            session.setAttribute("order", order);
        }
        json.put("order", order);
        return json;
    }

    @RequestMapping(value = "/acceptorder")
    public String acceptOrder(HttpSession session, Principal principal, Model model,
            WebRequest request) {
        String view;
        Order order = (Order) session.getAttribute("order");
        if (order != null) {
            if (order.getCustomer() == null && principal == null) {
                view = "registration";
            } else {
                if (order.getCustomer() == null) {
                    orderServ.addCustomerToOrderByLogin(order, principal.getName());
                }
                model.addAttribute("accepted", true);
                session.removeAttribute("order");
                model.addAttribute("order", orderServ.saveOrder(order));
                view = "order_accepted";
            }
        } else {
            view = "redirect:/app/homepage";
        }
        return view;
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public String registrateCustomer(Model model,
            @ModelAttribute("customer") Customer customer,
            BindingResult resultCustomer, HttpSession session, HttpServletRequest request) {

        accountValidator.validate(customer, resultCustomer);
        if (resultCustomer.hasErrors()) {
            model.addAttribute("customer", customer);
            return "registration";
        }
        String login = customer.getAccount().getUsername();
        String password = customer.getAccount().getPassword();
        customer = customerServ.placeNewCustomer(customer);

        UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(login, password);
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication auth = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(auth);

        Order order = (Order) session.getAttribute("order");
        order.setCustomer(customer);
        session.setAttribute("order", order);

        return "redirect:/app/order/acceptorder";
    }

}
