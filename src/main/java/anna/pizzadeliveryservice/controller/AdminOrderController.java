
package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.dto.OrderDto;
import anna.pizzadeliveryservice.service.OrderService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for order administration
 * @author Anna
 */

@Controller
@RequestMapping(value = "/admin/order")
public class AdminOrderController {
    
    private OrderService orderServ;

    @Autowired
    public AdminOrderController(OrderService orderServ) {
        this.orderServ = orderServ;
    }

    @RequestMapping(value = "/orders")
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
