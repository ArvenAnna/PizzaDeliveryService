package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.exception.TooManyPizzasException;
import anna.pizzadeliveryservice.service.CustomerService;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.validator.CustomerValidator;
import java.io.UnsupportedEncodingException;
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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            System.out.println("empty");
            order = new Order();
            orderServ.setRates(order);
        }
        if (order.getCustomer() == null) {
            if (principal != null) {
                orderServ.addCustomerToOrderByLogin(order, principal.getName());
            }
        }
        try {
            System.out.println(order);
            orderServ.addPizzasToOrder(order, Long.parseLong(id));
            System.out.println(order);
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

    @RequestMapping(value = "/removeSession", method = RequestMethod.POST)
    public String sessionInvalidate(HttpSession session) {
        session.invalidate();
        return "home";
    }

    @RequestMapping(value = "/acceptorder")
    public String acceptOrder(HttpSession session, Principal principal, Model model) {

        String view;
        Order order = (Order) session.getAttribute("order");
        System.out.println("accept_order");
        if (order != null) {
            System.out.println("order not null");
            System.out.println(order);
            if (order.getCustomer() == null && principal == null) {
                //model.addAttribute("customer", new Customer());
                view = "registration";
                System.out.println("cusomer and principal null");
            } else {
                if (order.getCustomer() == null) {
                    System.out.println("principal not null");
                    System.out.println(order);
                    orderServ.addCustomerToOrderByLogin(order, principal.getName());
                }
                System.out.println("vsyo ok");
                System.out.println(order);
                model.addAttribute("accepted", true);
                session.removeAttribute("order");
                model.addAttribute("order", orderServ.saveOrder(order));

                view = "order_accepted";
            }
        } else {
            //model.addAttribute("empty_order_saving", true);
            System.out.println("error-----------");
            //view = "error_massage";
            view = "homepage";
        }
        return view;

//        for (GrantedAuthority authority : getAuthentication().
//                getAuthorities()) {
//            if (authority.getAuthority().equals("ROLE_USER")) {
//                return false;
//            }
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public String registrateCustomer(Model model,
            @ModelAttribute("customer") Customer customer,
            BindingResult resultCustomer, HttpSession session) throws UnsupportedEncodingException {

        accountValidator.validate(customer, resultCustomer);
        if (resultCustomer.hasErrors()) {
            model.addAttribute("customer", customer);
            return "registration";
        }

        String login = customer.getAccount().getUsername();
        String password = customer.getAccount().getPassword();
        System.out.println(password);
        UsernamePasswordAuthenticationToken authRequest
                = new UsernamePasswordAuthenticationToken(login, password);

        // Authenticate the user
        //Authentication authentication = authenticationManager.authenticate(authRequest);
        //SecurityContext securityContext = SecurityContextHolder.getContext();
        //securityContext.setAuthentication(authentication);
        // Create a new session and add the security context.
        //session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        customer = customerServ.placeNewCustomer(customer);
        Order order = (Order) session.getAttribute("order");
        order.setCustomer(customer);
        session.setAttribute("order", order);
        //model.addAttribute(order);
        return "redirect:acceptorder";
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
