package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.service.CustomerService;
import java.security.Principal;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */
@Controller
public class CustomerController {

    private CustomerService customerServ;

    public CustomerController(CustomerService customerServ) {
        this.customerServ = customerServ;
    }

    @RequestMapping(value = "/account")
    public String showCustomersInformation(Model model, Principal principal) {
        model.addAttribute("customer", customerServ.findCustomerByLogin(principal.getName()));
        return "account";
    }
}
