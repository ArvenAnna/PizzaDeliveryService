package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.exception.TooManyPizzasException;
import anna.pizzadeliveryservice.service.CustomerService;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.service.PizzaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Anna
 */
@Controller
@SessionAttributes("order")
public class OrderController {

    private CustomerService customerServ;
    private OrderService orderServ;

    @Autowired
    public OrderController(CustomerService customerServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.orderServ = orderServ;
    }
    
    @ModelAttribute ("order")
    public Order addOrder () {
        return new Order();    
    }

    @RequestMapping(value = "/addpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> addPizzaToOrder(@PathVariable String id, @ModelAttribute Order order,
            Principal principal, Model model) {
        Map<String, Object> json = new HashMap<>();
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
            model.addAttribute("order", order);
        } catch (TooManyPizzasException e) {
            json.put("exception", "TooManyPizzaException");
        }
        json.put("order", order);
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/delpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> delPizzaFromOrder(@PathVariable String id, @ModelAttribute Order order,
            Principal principal, Model model) {
        Map<String, Object> json = new HashMap<>();
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
            model.addAttribute("order", order);
        }
        json.put("order", order);
        return json;
    }

    @RequestMapping(value = "/removeSession", method = RequestMethod.POST)
    public String sessionInvalidate(HttpSession session) {
        session.invalidate();
        return "home";
    }

    @RequestMapping(value = "/accept_order", method = RequestMethod.POST)
    public String acceptOrder(@ModelAttribute Order order, WebRequest request,
            Principal principal, Model model) {
        String view;
        System.out.println("accept_order");
        if (order != null) {
            System.out.println("order not null");
            if (order.getCustomer() == null && principal == null) {
                view = "registration";
                System.out.println("cusomer and principal null");
            } else {
                if (order.getCustomer() == null) {
                    System.out.println("principal not null");
                    orderServ.addCustomerToOrderByLogin(order, principal.getName());
                }
                System.out.println("vsyo ok");
                model.addAttribute("accepted", true);
                model.addAttribute("order", orderServ.placeNewOrder(order));
                
                request.removeAttribute("order", WebRequest.SCOPE_SESSION);
                view = "order_accepted";
            }
        } else {
            model.addAttribute("empty_order_saving", true);
            System.out.println("error-----------");
            view = "error_massage";
        }
        return view;

//        for (GrantedAuthority authority : getAuthentication().
//                getAuthorities()) {
//            if (authority.getAuthority().equals("ROLE_USER")) {
//                return false;
//            }
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public String registrateCustomer(@ModelAttribute Customer customer, Model model,
            @ModelAttribute Order order) {
        order.setCustomer(customer);
        model.addAttribute(order);
        return "redirect:accept_order";
    }

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    
    //public ResponseEntity<String> method(HttpEntity<String> entity) {…}
//    @RequestMapping(method = RequestMethod.POST, value = "/emp")
//    public @ResponseBody
//    Employee addEmp(@RequestBody Employee e) {
//        employeeDS.add(e);
//        return e;
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/emp/{id}")
//    public @ResponseBody
//    Employee updateEmp(
//            @RequestBody Employee e, @PathVariable String id) {
//        employeeDS.update(e);
//        return e;
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/emp/{id}")
//    public @ResponseBody
//    void removeEmp(@PathVariable String id) {
//        employeeDS.remove(Long.parseLong(id));
//    }
}
