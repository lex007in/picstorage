package picstorage.services;

import picstorage.domain.UserInfo;

import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public interface UserInfoService {

    List<UserInfo> getAllUsers();

    UserInfo getUserInfo(String login);

    UserInfo updateUserName(String userName);

    void deleteUserInfo(String login);

    UserInfo createUserInfo(UserInfo userInfo);
}
