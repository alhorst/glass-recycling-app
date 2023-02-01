package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {


    //Look into Authorization for methods - what should be admin authorized? or purely authenticated?



    private UserDao userDao;

    public UserController(UserDao userDao){
        this.userDao = userDao;
    }



    //JdbcUserDao Methods start here **********

    @RequestMapping(path="/users", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    //can tweak path name, trying to differentiate registered recylers from all users
    @RequestMapping(path="/users/registered", method= RequestMethod.GET)
    public List<User> getAllRecyclers() {
        return userDao.listAllRecyclers();
    }

    //this might be better suited in the drivers Controller
    @RequestMapping(path="/drivers", method= RequestMethod.GET)
    public List<User> getAllDrivers() {
        return userDao.listAllDrivers();
    }

    @RequestMapping(path="/users/{id}", method= RequestMethod.GET)
    public User getUserById(@PathVariable int id) {
        User user = userDao.getUserById(id);

        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return user;
        }
    }

    @RequestMapping(path="/users/{username}", method= RequestMethod.GET)
    public User getUserByUsername(@PathVariable String username) {
        User user = userDao.findByUsername(username);

        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return user;
        }
    }

    //JdbcUserDetailsDao methods start here **********


}
