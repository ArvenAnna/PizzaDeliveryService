package anna.pizzadeliveryservice.controller;

import anna.pizzadeliveryservice.domain.Order;
import anna.pizzadeliveryservice.domain.OrderDetail;
import anna.pizzadeliveryservice.domain.Pizza;
import anna.pizzadeliveryservice.exception.TooManyPizzasException;
import anna.pizzadeliveryservice.service.OrderService;
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
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.WebRequest;

/**
 *
 * @author Anna
 */
@Controller
public class OrderController {

    private PizzaService pizzaServ;
    private OrderService orderServ;

    @Autowired
    public OrderController(PizzaService pizzaServ, OrderService orderServ) {
        this.pizzaServ = pizzaServ;
        this.orderServ = orderServ;
    }

//    @RequestMapping(value = "/addpizza", method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/xml, application/json")
//    @ResponseBody
//    public String ajaxTest(@RequestBody Map<String, String> dulya) throws JsonProcessingException {
//        System.out.println(dulya);
//        System.out.println(dulya.get("id"));
//        String sran = objectToJson(dulya);
//        System.out.println(sran);
//        return sran;
//    }
//
//    public String objectToJson(Object obj) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        String jsonObject = mapper.writeValueAsString(obj);
//        return jsonObject;
//    }
//
//    public Map<String, Object> jsonToMap(String json) throws IOException {
//        ObjectMapper mapper = new ObjectMapper();
//        //Object obj = mapper.readValue(json, Map.class);
//        Map<String, Object> map = new HashMap<String, Object>();
//        // convert JSON string to Map
//        map = mapper.readValue(json, new TypeReference<Map<String, String>>() {
//        });
//        return map;
//    }

    @RequestMapping(value = "/addpizza/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> addPizzaToOrder(@PathVariable String id, HttpSession session) {
        Map<String, Object> json = new HashMap<>();
        Order order;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
        } else {
            order = new Order();
            orderServ.setRates(order);
        }
        try {
            orderServ.addPizzasToOrder(order, Long.parseLong(id));
            session.setAttribute("order", order);
            
        } catch (TooManyPizzasException e) {
            json.put("exception", "TooManyPizzaException");
        }
        json.put("order", order);
        json.put("session_id", session.getId());
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/delpizza/{id}", method = RequestMethod.GET,
            headers = "Accept=application/json")
    @ResponseBody
    public Map<String, Object> delPizzaFromOrder(@PathVariable String id, HttpSession session) {
        Map<String, Object> json = new HashMap<>();
        Order order;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
        } else {
            json.put("exception", "DeletingWithoutCreatingOrder");
            return json;
        }
        if (order.getDetails().isEmpty()) {
            json.put("exception", "DeletingEmptyList");
        } else {
            orderServ.removePizzaFromOrder(order, Long.parseLong(id));
        }
        json.put("order", order);
        json.put("session_id", session.getId());
        return json;
    }

    @RequestMapping(value = "/removeSession", method = RequestMethod.GET)
    public void showHomePage(HttpSession session) {
        session.invalidate();
    }
    
    @RequestMapping(value = "/accept_order", method = RequestMethod.GET)
    public String acceptOrder(Map<String, Object> model, HttpSession session) {
//        Map<String, Object> json = new HashMap<>();
        Order order;
        if (session.getAttribute("order") != null) {
            order = (Order) session.getAttribute("order");
            if(order.getCustomer()==null){
                return "redirect:/registration/";
            }else{
                model.put("accepted", true);
                model.put("order", orderServ.placeNewOrder(order));
            }
        } else {
            model.put("error", "Empty order saving");
            return "error_massage";
            
        }
        return "order_accepted";
    }


    //public ResponseEntity<String> method(HttpEntity<String> entity) {…}
//    @RequestMapping(method = RequestMethod.POST, value = "/emp")
//    public @ResponseBody
//    Employee addEmp(@RequestBody Employee e) {
//        employeeDS.add(e);
//        return e;
//    }
//
//    @RequestMapping(method = RequestMethod.PUT, value = "/emp/{id}")
//    public @ResponseBody
//    Employee updateEmp(
//            @RequestBody Employee e, @PathVariable String id) {
//        employeeDS.update(e);
//        return e;
//    }
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/emp/{id}")
//    public @ResponseBody
//    void removeEmp(@PathVariable String id) {
//        employeeDS.remove(Long.parseLong(id));
//    }
}
