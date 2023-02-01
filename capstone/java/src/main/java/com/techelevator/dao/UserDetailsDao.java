package com.techelevator.dao;

import com.techelevator.model.UserDetails;

import java.util.List;

public interface UserDetailsDao {

    List<UserDetails> getAllUserDetails();

    UserDetails getUserDetailsByAccountId(int account_id);

    UserDetails getUserDetailsByUsername(String username);

    UserDetails createUserDetails(UserDetails userDetails);

    void updateUserDetails(UserDetails userDetails);

    void deleteUserDetails(int account_id);

    String getFullAddressByUsername(String username);

    int getCreditBalance(String username);

    int getCreditRedeemed(String username);

    int getTotalGlassRecycled(String username);




}
