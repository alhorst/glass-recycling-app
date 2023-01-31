package com.techelevator.dao;

import com.techelevator.model.PickupDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JdbcPickupDetailsDao implements PickupDetailsDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPickupDetailsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public PickupDetails getPickupDetails(int pickup_id) {

        PickupDetails pickupDetails = null;
        String sql = "SELECT pickup_id, driver_id, requesting_username, pickup_date, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE pickup_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pickup_id);
        if (results.next()) {
            pickupDetails = mapRowToPickupDetails(results);
        }

        return pickupDetails;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByDriverId(int driver_Id) {
        List<PickupDetails> pickupDetailsList = new ArrayList<>();
        String sql = "SELECT pickup_id, driver_id, requesting_username, pickup_date, num_of_bins, is_picked_up " +
                    "FROM pickup_details " +
                    "WHERE driver_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, driver_Id);
        while (results.next()) {
            pickupDetailsList.add(mapRowToPickupDetails(results));
        }
        return pickupDetailsList;
    }

    @Override
    public List<PickupDetails> getPickupDetailsByDate(Date pickup_date) {
        List<PickupDetails> pickupDetailsList = new ArrayList<>();
        String sql = "SELECT pickup_id, driver_id, requesting_username, pickup_date, num_of_bins, is_picked_up " +
                "FROM pickup_details " +
                "WHERE pickup_date = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pickup_date);
        while (results.next()) {
            pickupDetailsList.add(mapRowToPickupDetails(results));
        }
        return pickupDetailsList;
    }

    @Override
    public PickupDetails createPickupDetails(PickupDetails pickupDetails) {
        String sql = "INSERT INTO pickup_details (driver_id, requesting_username, pickup_date, num_of_bins, is_picked_up) " +
                    "VALUES (?, ?, ?, ?, ?) RETURNING pickup_id;";
        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                pickupDetails.getDriver_id(), pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up());
        return getPickupDetails(newId);
    }

    @Override
    public void updatePickupDetails(PickupDetails pickupDetails) {
        String sql = "UPDATE pickup_details " +
                "SET driver_id = ?, requesting username = ?, pickup_date = ?, num_of_bins = ?, is_picked_up = ? " +
                "WHERE pickup_id = ?;";
        jdbcTemplate.update(sql, pickupDetails.getDriver_id(), pickupDetails.getRequesting_username(), pickupDetails.getPickup_date(), pickupDetails.getNum_of_bins(), pickupDetails.getIs_picked_up());

    }

    @Override
    public void deletePickupDetails(int pickup_id) {
        String sql = "DELETE FROM pickup_details WHERE pickup_id = ?;";
        jdbcTemplate.update(sql, pickup_id);

    }

    private PickupDetails mapRowToPickupDetails(SqlRowSet rowSet) {

        PickupDetails pickupDetails = new PickupDetails();

        pickupDetails.setPickup_id(rowSet.getInt("pickup_id"));
        pickupDetails.setDriver_id(rowSet.getInt("driver_id"));
        pickupDetails.setRequesting_username(rowSet.getString("requesting_username"));
        pickupDetails.setPickup_date(rowSet.getDate("pickup_date"));
        pickupDetails.setNum_of_bins(rowSet.getInt("num_of_bins"));
        pickupDetails.setIs_picked_up(rowSet.getBoolean("is_picked_up"));

        return pickupDetails;


    }

}
