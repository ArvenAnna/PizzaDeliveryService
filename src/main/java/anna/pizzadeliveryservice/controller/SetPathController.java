/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anna.pizzadeliveryservice.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Anna
 */
@ControllerAdvice
public class SetPathController {
    
    @ModelAttribute
    public void addCustomerToModel(Model model, WebRequest request) {
       model.addAttribute("path", request.getContextPath());
    }
    
}
