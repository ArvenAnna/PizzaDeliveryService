package anna.pizzadeliveryservice.domain;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Anna
 */
@Entity 
@Table(name = "account")
public class Account {
    
    @Id
    @GeneratedValue(generator = "sequenceGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "sequenceGenerator", sequenceName = "account_ids")
    @Column(name = "id", nullable = false) 
    Long id;
    
    @Column(name = "username")
    String username;
    
    @Column(name = "password")
    String password;
    
    @Column(name = "availability")
    Boolean availability;
    

    @ManyToMany
        @JoinTable(name = "userRole_account",
            joinColumns
            = {
                @JoinColumn(name = "userRole_id", foreignKey = @ForeignKey(name = "FK_USERROLE_TO_ACCOUNT_MANY", 
            foreignKeyDefinition = "FOREIGN KEY (userRole_id) REFERENCES public.account (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))},
            inverseJoinColumns
            = {
                @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_ACCOUNT_TO_USERROLE_MANY", 
            foreignKeyDefinition = "FOREIGN KEY (account_id) REFERENCES public.userRole (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE"))}) 
    Set<UserRole> roles;
    
    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.username);
        hash = 97 * hash + Objects.hashCode(this.password);
        hash = 97 * hash + Objects.hashCode(this.availability);
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
        final Account other = (Account) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.availability, other.availability)) {
            return false;
        }
        return true;
    }
    
    
}
