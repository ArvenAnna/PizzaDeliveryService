package anna.pizzadeliveryservice.domain;

import anna.pizzadeliveryservice.domain.rate.Rate;
import anna.pizzadeliveryservice.exception.TooManyPizzasException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Anna Entity represents order of customer
 */
@Entity
@Table(name = "pizzaOrder")
public class Order {

    @Id
    @GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "order_ids")
    @Column(name = "id", nullable = false)
    Long id;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    List<OrderDetail> details = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ORDER_TO_CUSTOMER",
            foreignKeyDefinition = "FOREIGN KEY (customer_id) "
            + "REFERENCES public.customer (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    Customer customer;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    Status status;

    @Temporal(value = TemporalType.DATE)
    @Column(name = "date")
    Date date;

    @Transient
    private final int MAX_PIZZAS_IN_ORDER = 10;

    @JsonIgnore
    @Transient
    private List<Rate> rates;

    public Order() {
    }

    public List<OrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        checkForTooManyPizzasException((details.size()));
        this.details = details;
    }
    
    public void addPizza(Pizza pizza) {
        checkForTooManyPizzasException(details.size() + 1);
            details.add(new OrderDetail(pizza.price, pizza));
    }
    
    public void removePizza(Long id) {
        for (OrderDetail det : details) {
            if(det.pizza.id.equals(id)){
                details.remove(det);
                return;
            }
        }
    }

    public Integer getPureCost() {
        Integer sum = 0;
        for (OrderDetail det : details) {
            sum += det.price;
        }
        return sum;
    }

    public Integer getRateCost() {
        Integer cost = getPureCost();
        if (rates != null) {
            for (Rate rate : rates) {
                cost = cost - rate.giveRate(this);
            }
        }
        return cost;
    }

    private void checkForTooManyPizzasException(Integer pizzaCount) {
        if (pizzaCount > MAX_PIZZAS_IN_ORDER) {
            throw new TooManyPizzasException() {
                @Override
                public String getMessage() {
                    return "Order can't has more then " + MAX_PIZZAS_IN_ORDER + " pizzas";
                }
            };
        }
    }

    
    public Long getId() {
        return id;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.details);
        hash = 29 * hash + Objects.hashCode(this.customer);
        hash = 29 * hash + Objects.hashCode(this.status);
        hash = 29 * hash + Objects.hashCode(this.date);
        hash = 29 * hash + Objects.hashCode(this.rates);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.details, other.details)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.rates, other.rates)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", details=" + details + ", customer="
                + customer + ", status=" + status + ", date=" + date + ", rates="
                + rates + '}';
    }

    public enum Status {
        NEW, IN_PROGRSS, CANCELED, DONE
    }

}
