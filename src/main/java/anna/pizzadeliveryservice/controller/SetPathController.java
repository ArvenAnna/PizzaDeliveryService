package anna.pizzadeliveryservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

/**
 * Controller advise for setting context path to model
 * @author Anna
 */
@ControllerAdvice
public class SetPathController {
    
    @ModelAttribute
    public void setPath(Model model, WebRequest request) {
       model.addAttribute("path", request.getContextPath());
    }
    
}
