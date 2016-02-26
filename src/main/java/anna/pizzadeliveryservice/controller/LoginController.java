package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */
@Controller
public class LoginController {
    
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String showLoginPage(){
        return "login"; 
    }
    
    @RequestMapping(value = "/signin", method = RequestMethod.GET, params = "login_error")
    public String showErrorLoginPage(Map<String, Object> model){
        model.put("login_error", true);
        return "login"; 
    }
    
}
