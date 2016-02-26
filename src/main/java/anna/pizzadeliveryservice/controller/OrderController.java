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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Anna
 */
@Controller
public class OrderController {

    private CustomerService customerServ;
    private OrderService orderServ;

    @Autowired
    public OrderController(CustomerService customerServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.orderServ = orderServ;
    }

    @RequestMapping(value = "/addpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> addPizzaToOrder(@PathVariable String id, HttpSession session,
            Principal principal) {
        Map<String, Object> json = new HashMap<>();
        Order order;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
        } else {
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
        json.put("session_id", session.getId());
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/delpizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> delPizzaFromOrder(@PathVariable String id, HttpSession session,
            Principal principal) {
        Map<String, Object> json = new HashMap<>();
        Order order;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
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
        json.put("session_id", session.getId());
        return json;
    }

    @RequestMapping(value = "/removeSession", method = RequestMethod.POST)
    public String sessionInvalidate(HttpSession session) {
        session.invalidate();
        return "home";
    }

    @RequestMapping(value = "/accept_order", method = RequestMethod.POST)
    public String acceptOrder(Map<String, Object> model, HttpSession session, Principal principal) {
        String view;
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            if (order.getCustomer() == null && principal == null) {
                view = "registration";
            } else {
                if (order.getCustomer() == null) {
                    orderServ.addCustomerToOrderByLogin(order, principal.getName());
                }
                model.put("accepted", true);
                model.put("order", orderServ.placeNewOrder(order));
                session.removeAttribute("order");
                view = "order_accepted";
            }
        } else {
            model.put("empty_order_saving", true);
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
    public String registrateCustomer(@ModelAttribute Customer customer, Map<String, Object> model,
            HttpSession session) {
        Order order = (Order) session.getAttribute("order");
        order.setCustomer(customer);
        session.setAttribute("order", order);
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
