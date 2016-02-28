package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.dto.OrderDto;
import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.service.OrderService;
import anna.pizzadeliveryservice.service.PizzaService;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    
    @RequestMapping(value = "/changestatus", method = RequestMethod.POST,
            headers = "Accept=application/json")
    @ResponseBody
    public void changeStatus(@RequestBody OrderDto data) {
        System.out.println(data);
        orderServ.changeOrderStatus(data.getOrderId(), data.getStatus());
    }
}
