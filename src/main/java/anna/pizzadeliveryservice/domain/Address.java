package anna.pizzadeliveryservice.domain;

import java.util.Objects;
import javax.annotation.PreDestroy;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Anna
 * Entity represents address of customer
 */

import javax.persistence.ForeignKey;
@Component
@Entity 
@Table(name = "address")
public class Address {

    @Id 
    @GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "address_ids")
    @Column(name = "id", nullable = false) 
    Long id;
    
    @Value("Kiev")
    @Column(name = "city")
    String city;
    
    @Column(name = "street")
    String street;
    
    @Column(name = "house")
    String house;
    
    @Column(name = "apartment")
    Integer apartment;
    
    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ADDRESS_TO_CUSTOMER", 
            foreignKeyDefinition = "FOREIGN KEY (customer_id) REFERENCES public.customer (id) "
                    + "MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    Customer customer;

    public Address() {
    }

    public Address(String city) {
        this.city = city;
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destroy Address");
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Integer getApartment() {
        return apartment;
    }

    public void setApartment(Integer apartment) {
        this.apartment = apartment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.street);
        hash = 67 * hash + Objects.hashCode(this.house);
        hash = 67 * hash + Objects.hashCode(this.apartment);
        hash = 67 * hash + Objects.hashCode(this.customer);
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
        final Address other = (Address) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.house, other.house)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.apartment, other.apartment)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Address{" + "id=" + id + ", city=" + city + ", street=" + 
                street + ", house=" + house + ", apartment=" + apartment + 
                ", customer=" + customer.id + '}';
    }
    
}
