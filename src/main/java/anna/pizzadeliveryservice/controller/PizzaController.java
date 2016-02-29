package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for pizza displaying
 * @author Anna
 */

@Controller
@RequestMapping(value = "/pizza")
public class PizzaController {
    
    private PizzaService pizzaServ;

    @Autowired
    public PizzaController(PizzaService pizzaServ) {
        this.pizzaServ = pizzaServ;
    }
    
    @RequestMapping(value = {"/our_pizzas"})
    public String showPizzaList(Model model){
        model.addAttribute("somePizzas", pizzaServ.chooseAllAvailablePizzas());
        return "pizza_list";
    }
   
}
