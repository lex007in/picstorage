package picstorage.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private UserInfo creator;
    private Date creationTime;
    private String text;

    public Comment() {
    }

    public Comment(UserInfo creator, Date creationTime, String text) {
        this.creator = creator;
        this.creationTime = creationTime;
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public UserInfo getCreator() {
        return creator;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }
}
