package picstorage.services;

import picstorage.domain.BytePicture;
import picstorage.domain.Picture;
import picstorage.repository.BytePictureRepository;
import picstorage.repository.PictureRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public class PictureServiceImpl implements PictureService {

    PictureRepository pictureRepository;
    BytePictureRepository bytePictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository, BytePictureRepository bytePictureRepository) {
        this.pictureRepository = pictureRepository;
        this.bytePictureRepository = bytePictureRepository;
    }

    @Override
    public Picture addPicture(Picture picture, BytePicture bytePicture) {
        BytePicture savedBytePicture = bytePictureRepository.save(bytePicture);
        picture.setPictureId(savedBytePicture.getId());
        return pictureRepository.save(picture);
    }

    @Override
    public Picture setTitle(long id, String title) {
        Picture pic = pictureRepository.findOne(id);
        pic.setTitle(title);
        return pictureRepository.save(pic);
    }

    @Override
    public Picture setDescription(long id, String description) {
        Picture pic = pictureRepository.findOne(id);
        pic.setDescription(description);
        return pictureRepository.save(pic);
    }

    @Override
    public void deletePicture(long id) {
        bytePictureRepository.delete(pictureRepository.findOne(id).getPictureId());
        pictureRepository.delete(id);
    }

    @Override
    public List<Picture> getAllPictures() {
        List<Picture> pics = new ArrayList<>();
        for (Picture pic : pictureRepository.findAll()) {
            pics.add(pic);
        }
        return pics;
    }

    @Override
    public List<Picture> getUserPictures(String login) {
        return pictureRepository.findByUser(login);
    }

    @Override
    public BytePicture getBytePicture(long id) {
        return bytePictureRepository.findOne(pictureRepository.findOne(id).getPictureId());
    }
}
