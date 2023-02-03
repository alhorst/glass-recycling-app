package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.PickupDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.DriverDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
public class DriverController {

    //To-do:
    ///// Look into Authorization for methods - what needs to be Admin Authorized? Only authenticated? and public?


    private DriverDetailsDao driverDetailsDao;
    private PickupDetailsDao pickupDetailsDao;
    private RoutesDao routesDao;

    public DriverController(DriverDetailsDao driverDetailsDao, PickupDetailsDao pickupDetailsDao, RoutesDao routesDao) {
        this.driverDetailsDao = driverDetailsDao;
        this.pickupDetailsDao = pickupDetailsDao;
        this.routesDao = routesDao;
    }



    //DriverDetailsDao Methods start here **********

    //Get all driver details objects from the driver_details table
    @RequestMapping(path="/driverDetails", method= RequestMethod.GET)
    public List<DriverDetails> getAllDrivers() {
        return driverDetailsDao.getAllDrivers();
    }

    //Get a driver detail object from the driver_details table, using employee_id
    @RequestMapping(path="/driverDetails/{employee_id}", method= RequestMethod.GET)
    public DriverDetails getDriverByEmployeeId(@PathVariable int employee_id) {
        DriverDetails driver = null;
        driver = driverDetailsDao.getDriverByEmployeeId(employee_id);

        if (driver == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no driver details for that employee ID");
        } else {
            return driver;
        }
    }

    //Create a driver detail in the drivers_details table
    //Should call this at the same time the admin adds a driver
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/driverDetails", method= RequestMethod.POST)
    public DriverDetails addDriverDetail(@RequestBody DriverDetails newDriver) {
        if (newDriver != null) {
            return driverDetailsDao.createDriver(newDriver);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No details provided in the request!");
        }
    }

    /* I don't think we need this functionality - commenting out for now
    //Update a row in the driver_details table - returns the updated Driver Detail object
    @RequestMapping(path="/driverDetails/{employee_id}", method= RequestMethod.PUT)
    public DriverDetails updateDriverDetail(@RequestBody DriverDetails driverToUpdate, @PathVariable int employee_id) {
        if (driverToUpdate.getEmployee_id() == employee_id) {
            driverDetailsDao.updateDriver(driverToUpdate);
            return driverDetailsDao.getDriverByEmployeeId(employee_id);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The employee Id provided does not match the record you're attempting to update");
        }
    }*/

    //Deletes a driver detail from the driver_details table
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/driverDetails/{employee_id}", method= RequestMethod.DELETE)
    public void deleteDriverDetail(@PathVariable int employee_id) {
        if (driverDetailsDao.getDriverByEmployeeId(employee_id) != null) {
            driverDetailsDao.deleteDriver(employee_id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The driver you're attempting to delete, does not exist");
        }
    }
}
