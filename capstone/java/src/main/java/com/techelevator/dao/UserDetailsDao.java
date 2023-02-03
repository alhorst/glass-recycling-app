package com.techelevator.dao;

import com.techelevator.model.UserDetails;

import java.util.List;

public interface UserDetailsDao {

    List<UserDetails> findAllUserDetails();

    UserDetails findUserDetailsByAccountId(int account_id);

    UserDetails findUserDetailsByUsername(String username);

    UserDetails createUserDetails(UserDetails userDetails);

    void updateUserDetails(UserDetails userDetails);

    void deleteUserDetails(int account_id);

    String getFullAddressByAccountId(int account_id);

    int getTotalGlassRecycledByAccountId(int account_id);

    int getCreditBalanceByAccountId(int account_id);

    int getCreditRedeemedByAccountId(int account_id);






}
