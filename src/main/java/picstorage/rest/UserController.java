package picstorage.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import picstorage.domain.UserInfo;
import picstorage.services.UserInfoService;

import java.util.List;

/**
 * User: ivannik
 * Date: 16.04.2014
 */
@Controller
@RequestMapping(value="/rest")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value="/users", method= RequestMethod.GET)
    @ResponseBody
    public List<UserInfo> getAllUsers() {
        return userInfoService.getAllUsers();
    }

    @RequestMapping(value="/users/{id}", method= RequestMethod.GET)
    @ResponseBody
    public UserInfo getUserInfo(@PathVariable String id) {
        return userInfoService.getUserInfo(id);
    }

    @ResponseBody
    @RequestMapping(value="/reg", method= RequestMethod.POST)
    public UserInfo createUser(@RequestBody UserInfo userInfo) {
        UserInfo newUser = new UserInfo(userInfo.getLogin(), userInfo.getName());
        newUser.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        return userInfoService.createUserInfo(newUser);
    }
}
