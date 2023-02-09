package com.techelevator.controller;

import com.techelevator.dao.PrizeDetailsDao;
import com.techelevator.dao.UserDetailsDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class PrizeController {

    private PrizeDetailsDao prizeDetailsDao;
    private UserDetailsDao userDetailsDao;

    public PrizeController(PrizeDetailsDao prizeDetailsDao, UserDetailsDao userDetailsDao){
        this.prizeDetailsDao = prizeDetailsDao;
        this.userDetailsDao = userDetailsDao;
    }


    //PrizeDetailsDao Methods start here **********


}
