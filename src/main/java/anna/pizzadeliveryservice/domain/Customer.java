package anna.pizzadeliveryservice.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Anna
 * Entity represents customer of pizza service
 */

@Entity 
@Table(name = "customer")
public class Customer {
    
    @Id 
    @GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "customer_ids")
    @Column(name = "id", nullable = false) 
    Long id;
    
    @Column(name = "name")
    String name;
    
    @OneToMany(mappedBy = "customer")
    Set<Address> address = new HashSet<>();
    
    @OneToOne
    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_TO_CARD", 
            foreignKeyDefinition = "FOREIGN KEY (card_id) " +
            "REFERENCES public.card (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    Card card;
    
    @OneToOne
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_CUSTOMER_TO_ACCOUNT", 
            foreignKeyDefinition = "FOREIGN KEY (account_id) " +
            "REFERENCES public.account (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))
    Account account;
    
    public Customer() {
    }

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Customer(String name) {
        this.name = name;
    }
    
    public void addSumToCard(int sum){
        if(card != null){
            card.sum = card.sum + sum;
        }  
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.address);
        hash = 83 * hash + Objects.hashCode(this.card);
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
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.card, other.card)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", address=" + 
                address + ", card=" + card + '}';
    }    
}
