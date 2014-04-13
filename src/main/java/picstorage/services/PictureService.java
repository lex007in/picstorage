package picstorage.services;

import picstorage.domain.BytePicture;
import picstorage.domain.Picture;

import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface PictureService {

    Picture addPicture(Picture picture, BytePicture bytePicture);

    Picture setTitle(long id, String title);

    Picture setDescription(long id, String description);

    void deletePicture(long id);

    List<Picture> getAllPictures();

    List<Picture> getUserPictures(String login);

    BytePicture getBytePicture(long id);
}
