package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.PickupDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.PickupDetails;
import com.techelevator.model.UserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class PickupController {

    private PickupDetailsDao pickupDetailsDao;
    private RoutesDao routesDao;
    private DriverDetailsDao driverDetailsDao;

    public PickupController(PickupDetailsDao pickupDetailsDao, RoutesDao routesDao, DriverDetailsDao driverDetailsDao) {
        this.pickupDetailsDao = pickupDetailsDao;
        this.routesDao = routesDao;
        this.driverDetailsDao = driverDetailsDao;
    }


    //To-do:
    //////look into get pickup details by Date method
    ///// Look into Authorization for methods - what needs to be Admin Authorized? Only authenticated? and public?

    //PickupDetailsDao Methods start here **********

    //get my user details - logged in user via Principal
    @RequestMapping(path="/pickups/myPickups", method= RequestMethod.GET)
    public List<PickupDetails> getMyPickups(Principal principal) {
        List<PickupDetails> myPickups = pickupDetailsDao.getPickupDetailsByUsername(principal.getName());
        if (myPickups.size() != 0) {
            return myPickups;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are currently no pickups associated with that User!");
        }
    }

    //Get all pickups from the pickup_details table
    @RequestMapping(path="/pickups", method= RequestMethod.GET)
    public List<PickupDetails> getAllPickups(){
        if (pickupDetailsDao.getAllPickupDetails() != null){
            return pickupDetailsDao.getAllPickupDetails();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no pickups at this time");
        }
    }

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

    //Get a list of PickupDetails associated with a driver ID/employee_id
    @RequestMapping(path="/pickups/drivers/{driverId}", method= RequestMethod.GET)
    public List<PickupDetails> getPickupDetailsByDriverId(@PathVariable int driverId) {

        List<PickupDetails> results = null;

        if (driverDetailsDao.getDriverByEmployeeId(driverId) != null) {
             results = pickupDetailsDao.getPickupDetailsByDriverId(driverId);
             if (results == null) {
                 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That driver is not assigned to any pickups at the moment");
             } else {
                 return results;
             }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That driver does not exist");
        }
    }

    //Get a list of PickupDetails associated with a route ID
    @RequestMapping(path="/pickups/routes/{routeId}", method= RequestMethod.GET)
    public List<PickupDetails> getPickupDetailsByRouteId(@PathVariable int routeId) {
        if (pickupDetailsDao.getPickupDetailsByRouteId(routeId) != null) {
            return pickupDetailsDao.getPickupDetailsByRouteId(routeId);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no pickups assigned to that route");
        }
    }

    //Add a pickup in the pickup_details table
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/pickups", method= RequestMethod.POST)
    public PickupDetails addPickupDetails(@Valid @RequestBody PickupDetails newPickup) {
        if (newPickup != null) {
            return pickupDetailsDao.createPickupDetails(newPickup);
        }
        return null;
    }

    //Updates a row in the pickup_details table
    //would be a way to assign pickup to a driver/route
    @RequestMapping(path="/pickups/{pickupId}", method= RequestMethod.PUT)
    public PickupDetails updatePickupDetails(@Valid @RequestBody PickupDetails updatedPickup, @PathVariable int pickupId) {
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
