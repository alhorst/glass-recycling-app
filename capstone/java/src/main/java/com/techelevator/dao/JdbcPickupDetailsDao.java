package com.techelevator.dao;

import com.techelevator.model.PickupDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.Date;
import java.util.List;

public class JdbcPickupDetailsDao implements PickupDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPickupDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PickupDetails getPickupDetails(int pickup_id) {

        PickupDetails pickupDetails = null;
        String sql = "SELECT pickup_id, driver_id, requesting_username, pickup_date, num_of_bins, is_picked_up" +
                    "FROM pickup_details " +
                    "WHERE pickup_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pickup_id);
        if (results.next()) {

        }

        return null;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByDriverId(int driver_Id) {
        return null;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByDate(Date pickup_date) {
        return null;
    }

    @Override
    public PickupDetails createPickupDetails(PickupDetails pickupDetails) {
        return null;
    }

    @Override
    public void updatePickupDetails(PickupDetails pickupDetails) {

    }

    @Override
    public void deletePickupDetails(int pickup_id) {

    }

//    private PickupDetails mapRowToPickupDetails(SqlRowSet rowSet) {
//
//        PickupDetails pickupDetails = new PickupDetails();
//
//        pickupDetails.setPickup_id(rowSet.getInt("pickup_id"));
//        pickupDetails.setDriver_id(rowSet.getInt("driver_id"));
//        pickupDetails.setRequesting_username(rowSet.getString("requesting_username"));
//        pickupDetails.setPickup_date(rowSet.getDate("pickup_date"));
////        pickupDetails.setNum_of_bins(rowSet.getInt("num_of_bins"));
////        pickupDetails.setIs_picked_up(rowSet.getBoolean("is_picked_up"));
////
////        return pickupDetails;
//
//
//    }

}
