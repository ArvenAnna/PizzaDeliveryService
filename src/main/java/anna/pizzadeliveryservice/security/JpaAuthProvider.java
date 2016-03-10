package anna.pizzadeliveryservice.security;

import anna.pizzadeliveryservice.domain.Account;
import anna.pizzadeliveryservice.domain.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Custom authentication provider for Spring security
 * @author Anna
 */

@Component
public class JpaAuthProvider implements AuthenticationProvider {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JpaAuthProvider(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getName();
        String passwd = auth.getCredentials().toString();
        Account account = findAccount(name);
        if (account == null || !account.getPassword().equals(passwd)) {
            System.out.println(account.getUsername());
            System.out.println(account.getPassword());
            System.out.println(account.getAvailability());
            System.out.println(account.getId());
            System.out.println(account.getRoles());
            throw new BadCredentialsException("Bad Credentials");
        } else {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for(UserRole role:account.getRoles()){
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }    
            return new UsernamePasswordAuthenticationToken(name, passwd, authorities);
        }
    }

    @Override
    public boolean supports(Class<?> type) {
        return type.equals(UsernamePasswordAuthenticationToken.class);
    }

    private Account findAccount(String name) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("username", name);
        final String sql = "select ur.rolename, a.id, a.username, a.password, "
                + "a.availability from public.account as a, "
                + "public.userrole as ur, public.userrole_account as ua "
                + "where  a.id = ua.account_id and ur.id = ua.userrole_id "
                + "and username= ?";
        final Account account = new Account();
        List<Account> results = jdbcTemplate.query(sql,
                new RowMapper<Account>() {
            @Override
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                account.getRoles().add(new UserRole(rs.getString("rolename")));
                account.setId(rs.getLong("id"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                account.setAvailability(rs.getBoolean("availability"));
                return account;
            }
        }, name);
        return results.isEmpty() ? null : results.get(0);
    }

}

