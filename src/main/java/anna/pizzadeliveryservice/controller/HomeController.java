package anna.pizzadeliveryservice.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Anna
 */

@Controller
public class HomeController {

    public HomeController() {
    }
    
    @RequestMapping(value = {"","/home"}, method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model){
        return "home"; //вернуть имя представления
    }
}
