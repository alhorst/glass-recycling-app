package com.techelevator.dao;

import com.techelevator.model.User;
import com.techelevator.model.UserDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUserDetailsDao implements UserDetailsDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public UserDetails createUserDetails(UserDetails userDetails) {
        return null;
    }

    @Override
    public void updateUserDetails(UserDetails userDetails) {

    }

    @Override
    public void deleteUserDetails(int account_id) {

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
