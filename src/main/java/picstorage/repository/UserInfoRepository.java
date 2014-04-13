package picstorage.repository;

import org.springframework.data.repository.CrudRepository;
import picstorage.domain.UserInfo;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

}
