package picstorage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import picstorage.domain.Picture;

import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface PictureRepository extends CrudRepository<Picture, Long> {

    @Query("select p from Picture p where p.creator.login = :login")
    List<Picture> findByUser(@Param("login")String login);
}
