package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Anna
 */

@Controller
public class OrderController {
    
    private PizzaService pizzaServ;

    @Autowired
    public OrderController(PizzaService pizzaServ) {
        this.pizzaServ = pizzaServ;
    }    
    
    @RequestMapping(value = "/addpizza", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> ajaxTest(@RequestBody Map<String, String> dulya) {
        System.out.println(dulya);
        System.out.println(dulya.get("id"));
        return dulya;
    }
}
