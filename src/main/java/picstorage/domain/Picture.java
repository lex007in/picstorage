package picstorage.domain;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
    private long id;
    private String title;
    private String description;
    private long pictureId;
    @OneToOne
    private UserInfo creator;
    private Date creationTime;
    @OneToMany
    private List<Comment> comments;

    public Picture() {
    }

    public Picture(long pictureId,
                   UserInfo creator,
                   Date creationTime) {
        this.pictureId = pictureId;
        this.creator = creator;
        this.creationTime = creationTime;
        this.comments = new ArrayList<Comment>();
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
