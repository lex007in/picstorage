package picstorage.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import picstorage.domain.UserInfo;

import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface UserInfoService extends UserDetailsService {

    List<UserInfo> getAllUsers();

    UserInfo getUserInfo(String login);

    UserInfo updateUserName(String login, String userName);

    void deleteUserInfo(String login);

    UserInfo createUserInfo(UserInfo userInfo);
}
