package com.techelevator.dao;

import com.techelevator.model.UserDetails;

public interface UserDetailsDao {

    UserDetails createUserDetails(UserDetails userDetails);

    void updateUserDetails(UserDetails userDetails);

    void deleteUserDetails(int account_id);

    String getFullAddress(String username);

    int getCreditBalance(String username);

    int getTotalGlassRecycled(String username);




}
