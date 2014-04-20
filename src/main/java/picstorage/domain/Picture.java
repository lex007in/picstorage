package picstorage.domain;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
@Entity
public class Picture {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private long pictureId;
    @OneToOne
    private UserInfo creator;
    private Date creationTime;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Picture() {
    }

    public Picture(UserInfo creator,
                   Date creationTime) {
        this.creator = creator;
        this.creationTime = creationTime;
        this.comments = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getPictureId() {
        return pictureId;
    }

    public void setPictureId(long pictureId) {
        this.pictureId = pictureId;
    }

    public UserInfo getCreator() {
        return creator;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}
