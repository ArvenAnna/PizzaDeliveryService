package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for home page
 * @author Anna
 */

@Controller
public class HomeController {
    
    private PizzaService pizzaServ;

    @Autowired
    public HomeController(PizzaService pizzaServ) {
        this.pizzaServ = pizzaServ;
    }
  
    @RequestMapping(value = {"","/homepage", "/signin"}, method = RequestMethod.GET)
    public String showHomePage(Model model){
        Set<Pizza> pzs = pizzaServ.chooseRandomSomePizzas();
        model.addAttribute("somePizzas", pzs);  
        return "home";
    }
    
    @RequestMapping(value = "/signin", method = RequestMethod.GET, params = "login_error")
    public String showErrorLoginPage(Model model){
        model.addAttribute("somePizzas", pizzaServ.chooseRandomSomePizzas());
        model.addAttribute("login_error", true);
        return "home";
    }
     
}
