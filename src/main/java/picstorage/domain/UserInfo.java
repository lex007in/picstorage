package picstorage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
@Entity
public class UserInfo {

    @Id
    private String login;
    @JsonIgnore
    private String password;
    private String name;

    public UserInfo() {
    }

    public UserInfo(String login, String name) {
        this.login = login;
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDetails toUserDetails() {
        return new User(login, password, Arrays.asList(new SimpleGrantedAuthority("USER")));
    }
}
