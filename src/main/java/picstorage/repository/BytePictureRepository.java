package picstorage.repository;

import org.springframework.data.repository.CrudRepository;
import picstorage.domain.BytePicture;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface BytePictureRepository extends CrudRepository<BytePicture, Long> {
}
