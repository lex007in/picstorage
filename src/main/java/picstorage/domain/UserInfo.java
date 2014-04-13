package picstorage.domain;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
public class UserInfo {

    private String login;
    private String name;

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
