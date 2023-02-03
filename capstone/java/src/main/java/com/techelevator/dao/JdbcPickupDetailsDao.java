package com.techelevator.dao;

import com.techelevator.model.PickupDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcPickupDetailsDao implements PickupDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPickupDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    //Do we need a getUnassignedPickups method - would display list of unassigned pickups to admin


    //getMyPickupDetails in PickupController utilizes, feeding in the principal's username
    @Override
    public List<PickupDetails> getPickupDetailsByUsername(String username) {
        List<PickupDetails> myPickups = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE requesting_username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while(results.next()) {
            PickupDetails eachPickup = mapRowToPickupDetails(results);
            myPickups.add(eachPickup);
        }
        return myPickups;
    }

    @Override
    public PickupDetails getPickupDetails(int pickupId) {

        PickupDetails pickupDetails = null;
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE pickup_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pickupId);
        if (results.next()) {
            pickupDetails = mapRowToPickupDetails(results);
        }
        return pickupDetails;
    }

    @Override
    public List<PickupDetails> getAllPickupDetails() {
        List<PickupDetails> allPickups = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            allPickups.add(mapRowToPickupDetails(results));
        }
        return allPickups;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByRouteId(int routeId) {
        List<PickupDetails> pickupDetailsList = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE route_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, routeId);
        while (results.next()) {
            pickupDetailsList.add(mapRowToPickupDetails(results));
        }
        return pickupDetailsList;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByDriverId(int driverId) {
        List<PickupDetails> pickupsByDriver = new ArrayList<>();
        String sql = "SELECT pickup_id, pickup_details.route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "JOIN routes ON pickup_details.route_id = routes.route_id " +
                    "WHERE driver_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, driverId);
        while (results.next()) {
            pickupsByDriver.add(mapRowToPickupDetails(results));
        }
        return pickupsByDriver;
    }

    //researching best way to implement
    //commenting out for now
    /*
    @Override
    public List<PickupDetails> getPickupDetailsByDate(Date pickupDate) {
        List<PickupDetails> pickupDetailsList = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE pickup_date = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pickupDate);
        while (results.next()) {
            pickupDetailsList.add(mapRowToPickupDetails(results));
        }
        return pickupDetailsList;
    }*/

    @Override
    public PickupDetails createPickupDetails(PickupDetails pickupDetails) {
        String sql = "INSERT INTO pickup_details (route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING pickup_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                pickupDetails.getRoute_id(), pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.getPickup_weight(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up());
        return getPickupDetails(newId);
    }

    @Override
    public void updatePickupDetails(PickupDetails pickupDetails) {
        String sql = "UPDATE pickup_details " +
                    "SET route_id = ?, requesting_username = ?, pickup_date = ?, pickup_weight = ?, num_of_bins = ?, is_picked_up = ? " +
                    "WHERE pickup_id = ?;";
        jdbcTemplate.update(sql, pickupDetails.getRoute_id(), pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.getPickup_weight(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up(), pickupDetails.getPickup_id());

    }

    @Override
    public void deletePickupDetails(int pickupId) {
        String sql = "DELETE FROM pickup_details WHERE pickup_id = ?;";
        jdbcTemplate.update(sql, pickupId);
    }

    private PickupDetails mapRowToPickupDetails(SqlRowSet rowSet) {

        PickupDetails pickupDetails = new PickupDetails();

        pickupDetails.setPickup_id(rowSet.getInt("pickup_id"));
        pickupDetails.setRoute_id(rowSet.getInt("route_id"));
        pickupDetails.setRequesting_username(rowSet.getString("requesting_username"));
        pickupDetails.setPickup_date(rowSet.getDate("pickup_date"));
        pickupDetails.setNum_of_bins(rowSet.getInt("num_of_bins"));
        pickupDetails.setPickup_weight(rowSet.getInt("pickup_weight"));
        pickupDetails.setIs_picked_up(rowSet.getBoolean("is_picked_up"));

        return pickupDetails;


    }

}
