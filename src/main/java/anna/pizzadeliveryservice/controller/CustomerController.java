package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Address;
import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.service.CustomerService;
import anna.pizzadeliveryservice.service.OrderService;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Anna
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private CustomerService customerServ;
    private OrderService orderServ;

    @Autowired
    public CustomerController(CustomerService customerServ, OrderService orderServ) {
        this.customerServ = customerServ;
        this.orderServ = orderServ;
    }

    @RequestMapping(value = "/continueshoping")
    public String continueCustomersOrder(@RequestParam String orderId, HttpSession session) {
        Order order = orderServ.findOrderById(Long.parseLong(orderId));
        session.setAttribute("order", order);
        return "redirect:our_pizzas";
    }

    @RequestMapping(value = "/account")
    public String showCustomersInformation(Model model, Principal principal) {
        Customer customer = customerServ.findCustomerByLogin(principal.getName());
        Set<Order> orders = orderServ.findAllCustomersActualOrders(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("orders", orders);
        return "account";
    }

    @RequestMapping(value = "/cancelorder")
    public String cancelCustomersOrder(@RequestParam String orderId) {
        orderServ.changeOrderStatus(Long.parseLong(orderId), Order.Status.CANCELED);
        return "redirect:account";
    }

    @RequestMapping(value = "/changetel", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public void changeTel(@RequestBody String tel, Principal principal) {
        customerServ.changeTelephoneByLogin(principal.getName(), tel);
        System.out.println(tel);
    }

    @RequestMapping(value = "/changeaddress", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public void changeAddress(@RequestBody Address address, Principal principal) {
        customerServ.changeAddressByLogin(principal.getName(), address);
        System.out.println(address);
    }

}
