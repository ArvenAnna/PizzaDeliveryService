package anna.pizzadeliveryservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Anna
 * Controller for static content
 */

@Controller
@RequestMapping(value = "/static")
public class StaticContentController {

    public StaticContentController() {
    }
    
    @RequestMapping(value = {"/delivery"}, method = RequestMethod.GET)
    public String showDeliveryPage(){
        return "delivery";
    }
    
    @RequestMapping(value = {"/discount"}, method = RequestMethod.GET)
    public String showDiscountPage(){
        return "discount";
    }
    
    @RequestMapping(value = {"/contacts"}, method = RequestMethod.GET)
    public String showContactsPage(){
        return "contacts";
    }
}
