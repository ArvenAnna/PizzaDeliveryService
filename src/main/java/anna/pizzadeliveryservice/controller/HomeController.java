package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.service.PizzaService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

  
    @RequestMapping(value = {"","/homepage"}, method = RequestMethod.GET)
    public String showHomePage(Map<String, Object> model){
        model.put("somePizzas", pizzaServ.chooseRandomSomePizzas());
        //fillDB();
        return "home"; //вернуть имя представления
    }
    
    private void fillDB(){
        System.out.println("filldb" + pizzaServ);
        Pizza p = new Pizza();
        p.setName("Pizza gavno");
        p.setDescription("gavno");
        p.setFoto("/foto/bla");
        p.setPrice(34);
        p.setPizzaType(Pizza.PizzaType.Meat);
        pizzaServ.addPizza(p);
        p = new Pizza();
        p.setName("Pizza sran");
        p.setDescription("sran");
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
