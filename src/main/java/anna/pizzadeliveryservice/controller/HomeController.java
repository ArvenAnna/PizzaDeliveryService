package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.Map;
import java.util.Set;
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
        Set<Pizza> pzs = pizzaServ.chooseRandomSomePizzas();
        model.addAttribute("somePizzas", pzs);  
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
        p.setFoto("Margarita.png");
        p.setPrice(50);
        p.setPizzaType(Pizza.PizzaType.Meat);        
        pizzaServ.addPizza(p);
        
        p = new Pizza();
        p.setName("Labradu");
        p.setDescription("С фасолью и специями");
        p.setFoto("Labradu.png");
        p.setPrice(45);
        p.setPizzaType(Pizza.PizzaType.Vegetarian);       
        pizzaServ.addPizza(p);  
        
        p = new Pizza();
        p.setName("Solina");
        p.setDescription("Натуральные раки и крабы");
        p.setFoto("Solina.png");
        p.setPrice(56);
        p.setPizzaType(Pizza.PizzaType.Sea);
        pizzaServ.addPizza(p); 
        
        p = new Pizza();
        p.setName("Razzya");
        p.setDescription("Загадочная пицца");
        p.setFoto("Razzya.png");
        p.setPrice(130);
        p.setPizzaType(Pizza.PizzaType.Sea);
        pizzaServ.addPizza(p);
        
        p = new Pizza();
        p.setName("Lubu");
        p.setDescription("Крайне малокалорийная");
        p.setFoto("Lubu.png");
        p.setPrice(75);
        p.setPizzaType(Pizza.PizzaType.Vegetarian);
        pizzaServ.addPizza(p);
        
         p = new Pizza();
        p.setName("Polya");
        p.setDescription("Несравненный вкус и аромат");
        p.setFoto("Polya.png");
        p.setPrice(45);
        p.setPizzaType(Pizza.PizzaType.Meat);
        pizzaServ.addPizza(p);
        
         p = new Pizza();
        p.setName("Missardis");
        p.setDescription("Полузакрытый вариант");
        p.setFoto("Misardis.png");
        p.setPrice(63);
        p.setPizzaType(Pizza.PizzaType.Sea);
        pizzaServ.addPizza(p);
        
         p = new Pizza();
        p.setName("Furia");
        p.setDescription("Пицца небывалого размера");
        p.setFoto("Furia.png");
        p.setPrice(130);
        p.setPizzaType(Pizza.PizzaType.Meat);
        pizzaServ.addPizza(p);
        
         p = new Pizza();
        p.setName("Sokoko");
        p.setDescription("С особым секретным ингридиентом");
        p.setFoto("Sokoko.png");
        p.setPrice(48);
        p.setPizzaType(Pizza.PizzaType.Meat);
        pizzaServ.addPizza(p);
        
         p = new Pizza();
        p.setName("Pro");
        p.setDescription("Пицца для особо занятых");
        p.setFoto("Pro.png");
        p.setPrice(54);
        p.setPizzaType(Pizza.PizzaType.Vegetarian);
        pizzaServ.addPizza(p);
    }
}
