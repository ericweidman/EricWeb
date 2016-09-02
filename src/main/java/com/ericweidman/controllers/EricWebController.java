package com.ericweidman.controllers;

import com.ericweidman.entities.User;
import com.ericweidman.services.UserRepository;
import com.ericweidman.utils.PasswordStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by ericweidman on 8/26/16.
 */
@RestController
public class EricWebController {
    @Autowired
    UserRepository users;

    @RequestMapping(path = "create-user", method = RequestMethod.POST)
    public User createUser(@RequestBody User newUser) throws Exception {
        User user = users.findByUserName(newUser.getUserName());
        if (user == null) {
            user = new User(newUser.getUserName(), PasswordStorage.createHash(newUser.getPasswordHash()));
            users.save(user);
            System.out.println("User added.");
            return null;
        } else {
            throw new Exception("Username already taken");
        }
    }

    @RequestMapping(path = "login-user", method = RequestMethod.POST)
    public String loginUser(@RequestBody User existingUser, HttpSession session) throws Exception {
        User user = users.findByUserName(existingUser.getUserName());
        if(existingUser.getPasswordHash() == null || existingUser.getPasswordHash() == null){
            throw new Exception("Username does not exist");
        }
        else if(!PasswordStorage.verifyPassword(existingUser.getPasswordHash(), user.getPasswordHash())){
            throw new Exception("Invalid Password!");
        }
        else{
            session.setAttribute("userName", user.getUserName());
            return user.getUserName();

        }

    }
}
