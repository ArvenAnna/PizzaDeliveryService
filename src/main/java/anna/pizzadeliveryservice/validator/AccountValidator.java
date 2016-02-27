package anna.pizzadeliveryservice.validator;

import anna.pizzadeliveryservice.domain.Account;
import anna.pizzadeliveryservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Anna
 */
@Component
public class AccountValidator implements Validator {

    CustomerService customerServ;

    @Autowired
    public AccountValidator(CustomerService customerServ) {
        this.customerServ = customerServ;
    }

    @Override
    public boolean supports(Class<?> type) {
        return Account.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "label.validate.usernameEmpty");
        //ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "label.validate.passwordEmpty");
        Account account = (Account) o;
        System.out.println("kkkkk");
        String username = account.getUsername();
        System.out.println(username);
        System.out.println(customerServ.findCustomerByLogin(username));
        if (customerServ.findCustomerByLogin(username)!=null) {
            System.out.println("jjjjjjjjjjjj");
            errors.rejectValue("username", "username.exist", "Username already exists");
        }
        System.out.println("llllllllllllll");
    }

}
