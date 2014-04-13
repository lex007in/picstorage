package picstorage.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
@Entity
public class BytePicture {

    @Id
    private long id;
    private String fileName;
    private byte[] picture;

    public BytePicture() {
    }

    public BytePicture(String fileName, byte[] picture) {
        this.fileName = fileName;
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getPicture() {
        return picture;
    }
}
