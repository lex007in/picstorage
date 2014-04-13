package picstorage.domain;

import java.time.LocalDateTime;

/**
 * User: ivannik
 * Date: 10.04.2014
 */
public class Comment {
    private long id;
    private UserInfo creator;
    private LocalDateTime creationTime;
    private String text;

    public Comment(UserInfo creator, LocalDateTime creationTime, String text) {
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

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public String getText() {
        return text;
    }
}
