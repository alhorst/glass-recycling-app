package com.techelevator.dao;

import com.techelevator.model.DriverDetails;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcDriverDetailsDao implements DriverDetailsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcDriverDetailsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DriverDetails> getAllDrivers() {
        List<DriverDetails> allDrivers = new ArrayList<>();
        String sql = "SELECT employee_id, username, home_office_address FROM driver_details;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()){
            DriverDetails driverDetails = mapRowToDriverDetails(results);
            allDrivers.add(driverDetails);
        }
        return allDrivers;
    }

    @Override
    public DriverDetails getDriverByEmployeeId(int employee_id) {
        DriverDetails driver = null;
        String sql = "SELECT employee_id, username, home_office_address FROM driver_details " +
                     "WHERE employee_id = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, employee_id);
        if (result.next()){
            driver = mapRowToDriverDetails(result);
        }
        return driver;
    }

    @Override
    public DriverDetails getDriverByUsername(String username) {
        DriverDetails driver = null;
        String sql = "SELECT employee_id, username, home_office_address FROM driver_details " +
                "WHERE username = ?;";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        if (result.next()){
            driver = mapRowToDriverDetails(result);
        }
        return driver;
    }

    @Override
    public DriverDetails createDriver(DriverDetails driverDetails) {
        return null;
    }

    @Override
    public void updateDriver(DriverDetails driverDetails) {

    }

    @Override
    public void deleteDriver(int employee_id) {
        String sql = "DELETE FROM driver_details WHERE employee_id = ?;";
        jdbcTemplate.update(sql, employee_id);
    }

    private DriverDetails mapRowToDriverDetails(SqlRowSet rs) {
        DriverDetails driverDetails = new DriverDetails();
        driverDetails.setEmployee_id(rs.getInt("employee_id"));
        driverDetails.setUsername(rs.getString("username"));
        driverDetails.setHome_office_address(rs.getString("home_office_address"));
        return driverDetails;
    }
}
