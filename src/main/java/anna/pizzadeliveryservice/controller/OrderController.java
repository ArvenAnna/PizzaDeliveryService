package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.service.PizzaService;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping(value = "/addpizza", method = RequestMethod.GET)
    @ResponseBody
    public Set<String> ajaxTest() {
        Set<String> records = new HashSet<String>();
        records.add("Record #1");
        records.add("Record #2");
        return records;
    }
}
