package anna.pizzadeliveryservice.domain;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Anna
 * Entity represents discount card of customer
 */

@Entity 
@Table(name = "card")
public class Card {
    
    @Id 
    @GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "card_ids")
    @Column(name = "id", nullable = false) 
    Long id;
    
    @Column(name = "sum")
    Integer sum;

    public Card() {
    }

    public Card(Long id, Integer sum) {
        this.id = id;
        this.sum = sum;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + Objects.hashCode(this.sum);
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
        final Card other = (Card) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.sum, other.sum)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Card{" + "id=" + id + ", sum=" + sum + '}';
    }
    
}
