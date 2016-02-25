package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.domain.Order;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */

@Controller
public class RegistrationController {
    
   @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute Customer customer, HttpSession session) {  
        //validation if such login/password exists
        //save customer and put it to session
        return "redirect:/accept_order/";
    }
}
