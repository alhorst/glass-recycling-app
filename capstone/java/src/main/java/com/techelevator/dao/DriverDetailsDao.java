package com.techelevator.dao;

import com.techelevator.model.DriverDetails;

import java.sql.Driver;
import java.util.List;

public interface DriverDetailsDao {

    List<DriverDetails> getAllDrivers();

    DriverDetails getDriverByDriverId(int driverId);

    DriverDetails getDriverByUsername(String username);

    DriverDetails createDriver(DriverDetails driverDetails);

    // Commenting out for now - don't think we'll need this functionality
    // void updateDriver(DriverDetails driverDetails);

    void deleteDriver(int driver_Id);
}
