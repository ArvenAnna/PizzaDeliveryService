package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.service.PizzaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */

@Controller
public class PizzaListController {
    
    private PizzaService pizzaServ;

    @Autowired
    public PizzaListController(PizzaService pizzaServ) {
        this.pizzaServ = pizzaServ;
    }
    
    @RequestMapping(value = {"/our_pizzas"}, method = RequestMethod.GET)
    public String showPizzaList(Map<String, Object> model){
        model.put("somePizzas", pizzaServ.chooseAllAvailablePizzas());
        return "pizza_list";
    }
    
}
