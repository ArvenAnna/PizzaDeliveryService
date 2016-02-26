package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Controller for home page
 * @author Anna
 */

@Controller
public class HomeController {
    
    private PizzaService pizzaServ;

    @Autowired
    public HomeController(PizzaService pizzaServ) {
        this.pizzaServ = pizzaServ;
    }
  
    @RequestMapping(value = {"","/homepage", "/signin"}, method = RequestMethod.GET)
    public String showHomePage(Model model){
        model.addAttribute("somePizzas", pizzaServ.chooseRandomSomePizzas());
        //fillDB();
        return "home"; //вернуть имя представления
    }
    
    @RequestMapping(value = "/signin", method = RequestMethod.GET, params = "login_error")
    public String showErrorLoginPage(Model model){
        model.addAttribute("somePizzas", pizzaServ.chooseRandomSomePizzas());
        model.addAttribute("login_error", true);
        return "home";
        //return "redirect:homepage"; 
    }
    
    private void fillDB(){
        System.out.println("filldb" + pizzaServ);
        Pizza p = new Pizza();
        p.setName("Margarita");
        p.setDescription("Очень вкусная пицца");
        p.setFoto("margarita.png");
        p.setPrice(34);
        p.setPizzaType(Pizza.PizzaType.Meat);
        pizzaServ.addPizza(p);
        p = new Pizza();
        p.setName("Pizza sssss");
        p.setDescription("sssss");
        p.setFoto("/foto/ffff");
        p.setPrice(45);
        p.setPizzaType(Pizza.PizzaType.Sea);
        pizzaServ.addPizza(p);  
        p = new Pizza();
        p.setName("Pizza ggoooo");
        p.setDescription("gogo");
        p.setFoto("/foto/gogo");
        p.setPrice(56);
        p.setPizzaType(Pizza.PizzaType.Vegetarian);
        pizzaServ.addPizza(p); 
        p = new Pizza();
        p.setName("Pizza yyuuo");
        p.setDescription("yuyuyuyu");
        p.setFoto("/foto/yuyuyu");
        p.setPrice(89);
        p.setPizzaType(Pizza.PizzaType.Vegetarian);
        pizzaServ.addPizza(p); 
    }
}
