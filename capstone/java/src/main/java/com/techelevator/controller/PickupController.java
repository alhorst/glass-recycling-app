package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.PickupDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.DriverDetails;
import com.techelevator.model.PickupDetails;
import com.techelevator.model.Routes;
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

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/pickups", method= RequestMethod.POST)
    public PickupDetails addPickupDetails(@RequestBody PickupDetails newPickup) {
        if (newPickup != null) {
            return pickupDetailsDao.createPickupDetails(newPickup);

        }
        return null;
    }

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


    //RoutesDao Methods start here **********

    //Get a Route object from the routes table, using route_id
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.GET)
    public Routes getRouteByRouteId(@PathVariable int routeId) {
        Routes route = null;
        route = routesDao.getRoutesByRouteId(routeId);
        if (route != null) {
            return route;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such route exists");
        }
    }

    //Get routes by date Method--- thinking of best way to implement and pass date to the handler method

    //Add a route to the routes table - returning the new Route object
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/routes", method= RequestMethod.POST)
    public Routes addNewRoute(@RequestBody Routes newRoute) {
        if (newRoute != null) {
            return routesDao.createRoute(newRoute);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No route details provided in the request");
        }
    }

    //Update a route on the routes table - returning the updated route
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.PUT)
    public Routes updateRoute(@RequestBody Routes routeToUpdate, @PathVariable int routeId) {
        if (routeToUpdate.getRouteId() != routeId) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The route Id provided does not match the route record you're attempting to update");
        } else {
            Routes updatedRoute = null;
            routesDao.updateRoute(routeToUpdate);
            updatedRoute = routesDao.getRoutesByRouteId(routeId);
            return updatedRoute;
        }
    }

    //Deletes a route from the routes table
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.DELETE)
    public void deleteRoute(@PathVariable int routeId) {
        if (routesDao.getRoutesByRouteId(routeId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The route record you're attempting to delete, does not exist");
        } else {
            routesDao.deleteRoute(routeId);
        }
    }
}
