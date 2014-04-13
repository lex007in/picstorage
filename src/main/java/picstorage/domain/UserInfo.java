package picstorage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
@Entity
public class UserInfo {

    @Id
    private String login;
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

    public String getName() {
        return name;
    }
}
