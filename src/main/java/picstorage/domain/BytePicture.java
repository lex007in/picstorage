package picstorage.domain;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public class BytePicture {

    private long id;
    private String fileName;
    private byte[] picture;

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
