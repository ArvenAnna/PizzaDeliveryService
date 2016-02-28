package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Anna
 */

@Controller
public class AdminPizzaController {
    private PizzaService pizzaServ;
    private OrderService orderServ;

    @Autowired
    public AdminPizzaController(PizzaService pizzaServ, OrderService orderServ) {
        this.pizzaServ = pizzaServ;
        this.orderServ = orderServ;
    }

   
    
    @RequestMapping(value = "/adminmenu")
    public String showMenu(Model model) {
        model.addAttribute("adminPizzas", pizzaServ.chooseAllAvailablePizzas());
        return "admin/menu";
    }
    
    @RequestMapping(value = "/adminorders")
    public String showOrders(Model model) {
        model.addAttribute("adminOrders",orderServ.findAllActualOrders());
        Set<Order.Status> statuses = new HashSet<>();
        statuses.add(Order.Status.IN_PROGRSS);
        statuses.add(Order.Status.DONE);
        statuses.add(Order.Status.CANCELED);
        
        model.addAttribute("statuses", statuses);
        
        return "admin/orders";
    }
}
