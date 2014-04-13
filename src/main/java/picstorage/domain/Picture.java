package picstorage.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
public class Picture {

    private long id;
    private String title;
    private String description;
    private BytePicture picture;
    private byte[] thumbnail;
    private UserInfo creator;
    private LocalDateTime creationTime;
    private List<Comment> comments;

    public Picture(BytePicture picture,
                   UserInfo creator,
                   LocalDateTime creationTime) {
        this.picture = picture;
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

    public BytePicture getPicture() {
        return picture;
    }

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public UserInfo getCreator() {
        return creator;
    }

    public LocalDateTime getCreationTime() {
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
