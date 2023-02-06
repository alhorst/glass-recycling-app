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


    //Returns all unassigned pickups from pickup_details - (pickups NOT assigned to routeID yet)
    // including pickup_details && full_address
    @Override
    public List<PickupDetails> getAllUnassignedPickups() {
            List<PickupDetails> unassignedPickups = new ArrayList<>();
            String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up, " +
                        "street_address || ', ' || city || ', ' || state_abbreviation || ' ' || zipcode AS full_address " +
                        "FROM pickup_details " +
                        "JOIN user_details ON pickup_details.requesting_username = user_details.username " +
                        "WHERE route_id IS NULL;";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while(results.next()) {
                unassignedPickups.add(mapRowToPickupDetails(results));
            }
            return unassignedPickups;
        }

    //Returns unassigned pickups from pickup_details, for a given username - (Will utilize in PickupController to return 'MyUnassignedPickups' feeding in the principal)
    @Override
    public List<PickupDetails> getUnassignedPickupsByUsername(String username) {
        List<PickupDetails> myUnassignedPickups = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE route_id IS NULL AND requesting_username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, username);
        while(results.next()) {
            myUnassignedPickups.add(mapRowToPickupDetails(results));
        }
        return myUnassignedPickups;
    }

    //Get all pickups from the pickup_details table
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

    //Get pickup_details from pickup_details table, by requesting_username (recyclerUsername)
    //getMyPickups in PickupController utilizes this method, feeding in the logged-in recycler(principal) username
    @Override
    public List<PickupDetails> getPickupDetailsByRecyclerUsername(String recyclerUsername) {
        List<PickupDetails> myPickups = new ArrayList<>();
        String sql = "SELECT pickup_id, route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE requesting_username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recyclerUsername);
        while(results.next()) {
            PickupDetails eachPickup = mapRowToPickupDetails(results);
            myPickups.add(eachPickup);
        }
        return myPickups;
    }

    //Get pickup_details from pickup_details table, by Driver's Username
    //getMyPickupsByDriver in PickupController utilizes this method, feeding in the logged-in driver(principal) username
    @Override
    public List<PickupDetails> getPickupDetailsByDriverUsername(String driverUsername) {
        List<PickupDetails> myDriverPickups = new ArrayList<>();
        String sql = "SELECT pickup_id, pickup_details.route_id, requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "JOIN routes ON pickup_details.route_id = routes.route_id " +
                    "JOIN driver_details ON routes.driver_id = driver_details.driver_id " +
                    "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, driverUsername);
        while (results.next()) {
            myDriverPickups.add(mapRowToPickupDetails(results));
        }
        return myDriverPickups;
    }

    //Get pickup from pickup_details table, by pickup_id
    @Override
    public PickupDetails getPickupDetailsByPickupId(int pickupId) {

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

    //Get pickup_details from pickup_details table, by route_id
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


    //Get pickup_details from pickup_details table, by driver_id
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
        String sql = "INSERT INTO pickup_details (requesting_username, pickup_date, pickup_weight, num_of_bins, is_picked_up) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING pickup_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.calcPickupWeight(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up());
        return getPickupDetailsByPickupId(newId);
    }

    //Update a pickup
    @Override
    public void updatePickupDetails(PickupDetails pickupDetails) {
        if (pickupDetails.getRoute_id() == 0) {
            String sql = "UPDATE pickup_details " +
                    "SET requesting_username = ?, pickup_date = ?, pickup_weight = ?, num_of_bins = ?, is_picked_up = ? " +
                    "WHERE pickup_id = ?;";

            jdbcTemplate.update(sql, pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.calcPickupWeight(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up(), pickupDetails.getPickup_id());
        } else {
            String sql = "UPDATE pickup_details " +
                    "SET route_id = ?, requesting_username = ?, pickup_date = ?, pickup_weight = ?, num_of_bins = ?, is_picked_up = ? " +
                    "WHERE pickup_id = ?;";

            jdbcTemplate.update(sql, pickupDetails.getRoute_id(), pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.calcPickupWeight(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up(), pickupDetails.getPickup_id());
        }
    }

    //Delete a pickup from pickup_details table
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
        pickupDetails.setPickup_date(rowSet.getDate("pickup_date").toLocalDate());
        pickupDetails.setNum_of_bins(rowSet.getInt("num_of_bins"));
        pickupDetails.setPickup_weight(rowSet.getInt("pickup_weight"));
        pickupDetails.setIs_picked_up(rowSet.getBoolean("is_picked_up"));

        return pickupDetails;
    }

}
