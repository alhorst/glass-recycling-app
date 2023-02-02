package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.PickupDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.DriverDetails;
import com.techelevator.model.PickupDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Driver;
import java.util.List;

public class PickupController {

    //To-do:
    ///// Look into Authorization for methods - what needs to be Admin Authorized? Only authenticated? and public?


    private PickupDetailsDao pickupDetailsDao;
    private RoutesDao routesDao;
    private DriverDetailsDao driverDetailsDao;

    public PickupController(PickupDetailsDao pickupDetailsDao, RoutesDao routesDao, DriverDetailsDao driverDetailsDao) {
        this.pickupDetailsDao = pickupDetailsDao;
        this.routesDao = routesDao;
        this.driverDetailsDao = driverDetailsDao;
    }



    //PickupDetailsDao Methods start here **********

    //Get a PickupDetails object, by pickup_id
    @RequestMapping(path="/pickups/{pickupId}", method= RequestMethod.GET)
    public PickupDetails getPickupByPickupId(@PathVariable int pickupId) {
        PickupDetails pickup = pickupDetailsDao.getPickupDetails(pickupId);
        if (pickup != null) {
            return pickup;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That pickup Id does not exist");
        }
    }

    //Get a list of PickupDetails associated with a driver ID
    @RequestMapping(path="/drivers/{driverId}/pickups", method= RequestMethod.GET)
    public List<PickupDetails> getPickupDetailsByDriverId(@PathVariable int driverId) {
        if (driverDetailsDao.getDriverByEmployeeId(driverId) != null) {
            return pickupDetailsDao.getPickupDetailsByDriverId(driverId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That driver Id does not exist");
        }
    }

    //To-do:
    //////look into get pickup details by Date method


    //Add a pickup in the pickup_details table
    //how to get newPickup object back - reference driver controller
    // might need new method - getPickupDetailByRequestingUsername
    /*
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/pickups", method= RequestMethod.POST)
    public PickupDetails addPickupDetails(@RequestBody PickupDetails newPickup) {
        if (newPickup != null) {
            pickupDetailsDao.createPickupDetails(newPickup);

        }
        return null;
    }*/

    //Updates a row in the pickup_details table
    //would be a way to assign pickup to a driver/route
    @RequestMapping(path="/pickups/{pickupId}", method= RequestMethod.PUT)
    public PickupDetails updatePickupDetails(@RequestBody PickupDetails updatedPickup, @PathVariable int pickupId) {
        if (pickupId == updatedPickup.getPickup_id()) {
            pickupDetailsDao.updatePickupDetails(updatedPickup);
            return pickupDetailsDao.getPickupDetails(pickupId);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The pickup Id provided does not match the record you're attempting to update");
        }
    }

    //Deletes a pickup from the pickup_details table
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/pickups/{pickupId}", method= RequestMethod.DELETE)
    public void deletePickupDetails(@PathVariable int pickupId) {
        if (pickupDetailsDao.getPickupDetails(pickupId) != null) {
            pickupDetailsDao.deletePickupDetails(pickupId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The Pickup you're attempting to delete, does not exist");
        }
    }




}
