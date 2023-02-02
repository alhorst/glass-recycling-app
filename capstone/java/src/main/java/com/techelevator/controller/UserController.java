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


    //To-do:
    ///// Look into Authorization for methods - what needs to be Admin Authorized? Only authenticated? and public?


    private UserDao userDao;
    private UserDetailsDao userDetailsDao;

    public UserController(UserDao userDao, UserDetailsDao userDetailsDao){
        this.userDao = userDao;
        this.userDetailsDao = userDetailsDao;
    }



    //UserDao Methods start here **********

    //get all users from users table (recyclers, admins, drivers)
    @RequestMapping(path="/users", method= RequestMethod.GET)
    public List<User> getAllUsers() {
        return userDao.findAllUsers();
    }


    //get all drivers from users table
    @RequestMapping(path="/drivers", method= RequestMethod.GET)
    public List<User> getAllDrivers() {
        return userDao.listAllDrivers();
    }


    //can tweak path name, trying to differentiate registered recylers from all users. "path=/recyclers" ?
    @RequestMapping(path="/registeredUsers", method= RequestMethod.GET)
    public List<User> getAllRecyclers() {
        return userDao.listAllRecyclers();
    }

    //get user from users table, by user_id
    @RequestMapping(path="/users/{userId}", method= RequestMethod.GET)
    public User getUserById(@PathVariable int userId) {
        User user = userDao.getUserById(userId);

        if (user == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return user;
        }
    }

    //UserDetailsDao methods start here **********

    //get all user details from user_details table
    @RequestMapping(path="/users/details", method= RequestMethod.GET)
    public List<UserDetails> getAllUserDetails() {
        return userDetailsDao.findAllUserDetails();
    }

    //Get user detail from user_details table, using account_id
    @RequestMapping(path="/users/details/{accountId}", method= RequestMethod.GET)
    public UserDetails getUserDetailByAccountId(@PathVariable int accountId) {
        UserDetails userDetail = userDetailsDao.findUserDetailsByAccountId(accountId);

        if (userDetail == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such user exists");
        } else {
            return userDetail;
        }
    }

    //add new user detail to user_details table --- registering a user after they've completed information form
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/users/details", method= RequestMethod.POST)
    public UserDetails addNewUserDetails(@RequestBody UserDetails newUserDetail) {
        if (newUserDetail == userDetailsDao.findUserDetailsByAccountId(newUserDetail.getAccount_id())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "That user account already exists.");
        } else {
            return userDetailsDao.createUserDetails(newUserDetail);
        }
    }

    //update a user detail on the user_details table --- UserDetails object in Request body & account_id
    @RequestMapping(path="/users/details/{accountId}", method= RequestMethod.PUT)
    public void updateUserDetails(@RequestBody UserDetails userDetails, @PathVariable int accountId) {
        if (userDetails.getAccount_id() != accountId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The Account Id provided does not match the UserDetail you're trying to update");
        } else {
            userDetailsDao.updateUserDetails(userDetails);
        }
    }

    //deleting a user detail from user_details table
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/users/details/{accountId}", method= RequestMethod.DELETE)
    public void deleteUserDetails(@PathVariable int accountId) {
        if (userDetailsDao.findUserDetailsByAccountId(accountId) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such Account Id exists");
        } else {
            userDetailsDao.deleteUserDetails(accountId);
        }
    }

    //Get full address in string format for Routes API, using account_id
    //example - '3001 Railroad St, Pittsburgh, PA 15201'
    @RequestMapping(path="/users/details/{account_id}/address", method= RequestMethod.GET)
    public String getFullAddressByAccountId(@PathVariable int account_id) {
        if (userDetailsDao.findUserDetailsByAccountId(account_id) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such username exists");
        } else {
            return userDetailsDao.getFullAddressByAccountId(account_id);
        }
    }

    //Get total amount of glass recycled for an account_id
    @RequestMapping(path="/users/details/{account_id}/total", method= RequestMethod.GET)
    public int getTotalGlassRecycledByAccountId(@PathVariable int account_id) {
        if (userDetailsDao.findUserDetailsByAccountId(account_id) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such username exists");
        } else {
            return userDetailsDao.getTotalGlassRecycledByAccountId(account_id);
        }
    }

    //Get current credit/point balance for an account_id
    @RequestMapping(path="/users/details/{account_id}/balance", method= RequestMethod.GET)
    public int getCreditBalanceByAccountId(@PathVariable int account_id) {
        if (userDetailsDao.findUserDetailsByAccountId(account_id) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such username exists");
        } else {
            return userDetailsDao.getCreditBalanceByAccountId(account_id);
        }
    }


    //Get the total amount of credits an account has redeemed towards prizes
    //Not 100% sure we'll need this method, keeping for now
    @RequestMapping(path="/users/details/{account_id}/redeemed", method= RequestMethod.GET)
    public int getCreditRedeemedByAccountId(@PathVariable int account_id) {
        if (userDetailsDao.findUserDetailsByAccountId(account_id) == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such username exists");
        } else {
            return userDetailsDao.getCreditRedeemedByAccountId(account_id);
        }
    }
}
