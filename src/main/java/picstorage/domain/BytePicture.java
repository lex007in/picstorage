package picstorage.domain;

import javax.persistence.*;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
@Entity
public class BytePicture {

    @Id
    @GeneratedValue
    private long id;
    private String fileName;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] thumbnail;

    public BytePicture() {
    }

    public BytePicture(String fileName, byte[] picture, byte[] thumbnail) {
        this.fileName = fileName;
        this.picture = picture;
        this.thumbnail = thumbnail;
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

    public byte[] getThumbnail() {
        return thumbnail;
    }
}
