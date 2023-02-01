package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcUserDetailsDao implements UserDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<UserDetails> getAllUserDetails() {
        List<UserDetails> allUsers = new ArrayList<>();
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            UserDetails userDetails = mapRowToUserDetail(results);
            allUsers.add(userDetails);
        }
        return allUsers;
    }

    @Override
    public UserDetails getUserDetailsByAccountId(int account_id) {
        UserDetails userDetails = null;
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details " +
                "WHERE account_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, account_id);
        if (result.next()){
            userDetails = mapRowToUserDetail(result);
        }
        return userDetails;
    }

    @Override
    public UserDetails getUserDetailsByUsername(String username) {
        UserDetails userDetails = null;
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details " +
                "WHERE username = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            userDetails = mapRowToUserDetail(result);
        }
        return userDetails;
    }

    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {
        String sql = "INSERT INTO user_details (username, full_name, street_address, city, state_abbreviation, zipcode, " +
                                                "phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed) " +
                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING account_id;";
        Integer account_id = jdbcTemplate.queryForObject(sql, Integer.class, userDetails.getUsername(), userDetails.getFull_name(), userDetails.getStreet_address(),
                             userDetails.getCity(), userDetails.getState_abbreviation(), userDetails.getZipcode(), userDetails.getPhone_number(),
                             userDetails.getEmail_address(), userDetails.getTotal_pounds_recycled(), userDetails.getCredits_balance(), userDetails.getCredits_redeemed());

        return getUserDetailsByAccountId(account_id);
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {
        String sql = "UPDATE user_details " +
                    "SET username = ?, full_name = ?, street_address = ?, city = ?, state_abbreviation = ?, zipcode = ?, " +
                    "phone_number = ?, email_address = ?, total_pounds_recycled = ?, credits_balance = ?, credits_redeemed = ? " +
                    "WHERE account_id = ?;";
        jdbcTemplate.update(sql, userDetails.getUsername(), userDetails.getFull_name(), userDetails.getStreet_address(),
                userDetails.getCity(), userDetails.getState_abbreviation(), userDetails.getZipcode(), userDetails.getPhone_number(),
                userDetails.getEmail_address(), userDetails.getTotal_pounds_recycled(), userDetails.getCredits_balance(), userDetails.getCredits_redeemed(),
                userDetails.getAccount_id());
    }

    @Override
    public void deleteUserDetails(int account_id) {
        String sql = "DELETE FROM user_details WHERE account_id = ?;";
        jdbcTemplate.update(sql, account_id);
    }

    @Override
    public String getFullAddress(String username) {
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                    "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                    "FROM user_details WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            UserDetails userDetail = mapRowToUserDetail(result);
            String fullAddress = userDetail.getStreet_address() + ", " + userDetail.getCity() + ", " +
                                userDetail.getState_abbreviation() + " " + userDetail.getZipcode();
            return fullAddress;
        }
        return null;
    }


    @Override
    public int getCreditBalance(String username) {
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                    "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                    "FROM user_details WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            return mapRowToUserDetail(result).getCredits_balance();
        }
        return 0;
    }

    @Override
    public int getCreditRedeemed(String username) {
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                "FROM user_details WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            return mapRowToUserDetail(result).getCredits_redeemed();
        }
        return 0;
    }

    @Override
    public int getTotalGlassRecycled(String username) {
        String sql = "SELECT account_id, username, full_name, street_address, city, state_abbreviation, " +
                     "zipcode, phone_number, email_address, total_pounds_recycled, credits_balance, credits_redeemed " +
                     "FROM user_details WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            return mapRowToUserDetail(result).getTotal_pounds_recycled();
        }
        return 0;
    }

    private UserDetails mapRowToUserDetail(SqlRowSet rs) {
        UserDetails userDetail = new UserDetails();
        userDetail.setAccount_id(rs.getInt("account_id"));
        userDetail.setUsername(rs.getString("username"));
        userDetail.setFull_name(rs.getString("full_name"));
        userDetail.setStreet_address(rs.getString("street_address"));
        userDetail.setCity(rs.getString("city"));
        userDetail.setState_abbreviation(rs.getString("state_abbreviation"));
        userDetail.setZipcode(rs.getString("zipcode"));
        userDetail.setPhone_number(rs.getString("phone_number"));
        userDetail.setEmail_address(rs.getString("email_address"));
        userDetail.setTotal_pounds_recycled(rs.getInt("total_pounds_recycled"));
        userDetail.setCredits_balance(rs.getInt("credits_balance"));
        userDetail.setCredits_redeemed(rs.getInt("credits_redeemed"));
        return userDetail;
    }
}
