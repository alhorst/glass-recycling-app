package com.techelevator.dao;

import com.techelevator.model.DriverDetails;

import java.sql.Driver;
import java.util.List;

public interface DriverDetailsDao {

    List<DriverDetails> getAllDrivers();

    DriverDetails getDriverByEmployeeId(int employee_id);

    DriverDetails getDriverByUsername(String username);

    DriverDetails createDriver(DriverDetails driverDetails);

    void updateDriver(DriverDetails driverDetails);

    void deleteDriver(int employee_id);
}
