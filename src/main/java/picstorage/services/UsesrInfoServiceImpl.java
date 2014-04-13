package picstorage.services;

import picstorage.domain.UserInfo;
import picstorage.repository.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * User: ivannik
 * Date: 13.04.2014
 */
public class UsesrInfoServiceImpl implements UserInfoService {

    UserInfoRepository repository;

    public UsesrInfoServiceImpl(UserInfoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        List<UserInfo> ui = new ArrayList<>();
        for (UserInfo u : repository.findAll()) {
            ui.add(u);
        }
        return ui;
    }

    @Override
    public UserInfo getUserInfo(String login) {
        return repository.findOne(login);
    }

    @Override
    public UserInfo updateUserName(String login, String userName) {
        return repository.save(new UserInfo(login, userName));
    }

    @Override
    public void deleteUserInfo(String login) {
        repository.delete(login);
    }

    @Override
    public UserInfo createUserInfo(UserInfo userInfo) {
        return repository.save(userInfo);
    }
}
