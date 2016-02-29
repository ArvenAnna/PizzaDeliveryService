package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.dto.OrderDto;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.service.PizzaService;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Anna
 */

@Controller
@RequestMapping(value = "/admin/pizza")
public class AdminPizzaController {
    private PizzaService pizzaServ;
    private OrderService orderServ;

    @Autowired
    public AdminPizzaController(PizzaService pizzaServ, OrderService orderServ) {
        this.pizzaServ = pizzaServ;
        this.orderServ = orderServ;
    }
   
    @RequestMapping(value = "/menu")
    public String showMenu(Model model) {
        model.addAttribute("pizzaTypes", Pizza.PizzaType.values());
        model.addAttribute("adminPizzas", pizzaServ.chooseAllAvailablePizzas());
        return "admin/menu";
    }
    
    @RequestMapping(value = "/removePizza/{id}", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public void removePizza(@PathVariable String id) {
        pizzaServ.removePizzaFromMenu(Long.parseLong(id));
    }
    
    @RequestMapping(value = "/savePizza", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Pizza savePizzaChanges(@RequestBody Pizza pizza) {
        return pizzaServ.changePizzaInformation(pizza);
    }
    
    @RequestMapping(value = "/addNewPizza", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public Pizza addNewPizza(@RequestBody Pizza pizza) {
        System.out.println(pizza);
        return pizzaServ.addPizzaToMenu(pizza);
    }
    
}
