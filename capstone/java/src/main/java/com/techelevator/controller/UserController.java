package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UserDetailsDao;
import com.techelevator.model.User;
import com.techelevator.model.UserDetails;
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
    private UserDetailsDao userDetailsDao;

    public UserController(UserDao userDao, UserDetailsDao userDetailsDao){
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }



    //JdbcUserDao Methods start here **********

    //this might be better suited in the drivers Controller
    @RequestMapping(path="/drivers", method= RequestMethod.GET)
    public List<User> getAllDrivers() {
        return userDao.listAllDrivers();
    }

    @RequestMapping(path="/users", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    //can tweak path name, trying to differentiate registered recylers from all users
    @RequestMapping(path="/users/registered", method= RequestMethod.GET)
    public List<User> getAllRecyclers() {
        return userDao.listAllRecyclers();
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

    @RequestMapping(path="users/details", method= RequestMethod.GET)
    public List<UserDetails> getAllUserDetails() {
        return userDetailsDao.findAllUserDetails();
    }

    @RequestMapping(path="users/{id}/details", method= RequestMethod.GET)
    public UserDetails getUserDetailByAccountId(@PathVariable int id) {
        UserDetails userDetail = userDetailsDao.findUserDetailsByAccountId(id);

        if (userDetail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return userDetail;
        }
    }

    @RequestMapping(path="users/{username}/details", method= RequestMethod.GET)
    public UserDetails getUserDetailByAccountId(@PathVariable String username) {
        UserDetails userDetail = userDetailsDao.findUserDetailsByUsername(username);

        if (userDetail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return userDetail;
        }
    }

    //needs work ------------------------------------------
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="users/details", method= RequestMethod.POST)
    public UserDetails addUserDetails(@RequestBody UserDetails newUserDetail) {
        return null;
    }


    @RequestMapping(path="users/{id}/details", method= RequestMethod.PUT)
    public void updateUserDetails(@RequestBody UserDetails userDetails, @PathVariable int id) {
        if (userDetails.getAccount_id() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Account Id provided does not match the UserDetail you're trying to update");
        } else {
            userDetailsDao.updateUserDetails(userDetails);
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="users/{id}/details", method= RequestMethod.DELETE)
    public void deleteUserDetails(@PathVariable int id) {
        if (userDetailsDao.findUserDetailsByAccountId(id) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such Account Id exists");
        } else {
            userDetailsDao.deleteUserDetails(id);
        }
    }

    @RequestMapping(path="users/{username}/address", method= RequestMethod.GET)
    public String getFullAddressByUsername(@PathVariable String username) {
        if (userDetailsDao.findUserDetailsByUsername(username) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such username exists");
        } else {
            return userDetailsDao.getFullAddressByUsername(username);
        }
    }

    //still need:

    // create user details

    // getTotalGlassRecycled

    // getCreditBalance

    //getCreditsRedeemed


}
