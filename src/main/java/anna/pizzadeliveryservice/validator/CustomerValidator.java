package anna.pizzadeliveryservice.validator;

import anna.pizzadeliveryservice.domain.Customer;
import anna.pizzadeliveryservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Primary validator for registration form. Checks if such login exists.
 *
 * @author Anna
 */
@Component
public class CustomerValidator implements Validator {

    CustomerService customerServ;

    @Autowired
    public CustomerValidator(CustomerService customerServ) {
        this.customerServ = customerServ;
    }

    @Override
    public boolean supports(Class<?> type) {
        return Customer.class.isAssignableFrom(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.username", "label.validate.usernameEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "account.password", "label.validate.passwordEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tel", "label.validate.telEmpty");
        Customer customer = (Customer) o;
        String username = customer.getAccount().getUsername();
        if (customerServ.findCustomerByLogin(username) != null) {
            errors.rejectValue("account.username", "username.exist", "Username already exists");
        }
    }

}
