package com.techelevator.controller;

import com.techelevator.dao.DriverDetailsDao;
import com.techelevator.dao.RoutesDao;
import com.techelevator.model.Routes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RoutesController {

    private RoutesDao routesDao;
    private DriverDetailsDao driverDetailsDao;

    public RoutesController(RoutesDao routesDao, DriverDetailsDao driverDetailsDao) {
        this.routesDao = routesDao;
        this.driverDetailsDao = driverDetailsDao;
    }


    //To-Do:
    ////Get routes by date Method--- thinking of the best way to implement and pass date to the handler method
    ///// Look into Authorization for methods - what needs to be Admin Authorized? Only authenticated? and public?

    //RoutesDao Methods start here **********

    //Get all routes from the routes table
    @RequestMapping(path="/routes", method= RequestMethod.GET)
    public List<Routes> getAllRoutes() {
        List<Routes> allRoutes = routesDao.getAllRoutes();
        if (allRoutes.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no routes to report at this time");
        }
        return allRoutes;
    }

    //Get a Route object from the routes table, by route_id
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

    //Get a List of Route objects from the routes table, grouped by driver_id
    @RequestMapping(path="/routes/drivers/{driverId}", method= RequestMethod.GET)
    public List<Routes> getRoutesByDriverId(@PathVariable int driverId) {

        if (driverDetailsDao.getDriverByEmployeeId(driverId) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "That driver id does not exist");
        }

        List<Routes> results = new ArrayList<>();
        results = routesDao.getRoutesByDriverId(driverId);
        if (results.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are no routes assigned to that driver");
        } else {
            return results;
        }
    }

    //Add a route to the routes table - returning the new Route object
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/routes", method= RequestMethod.POST)
    public Routes addNewRoute(@Valid @RequestBody Routes newRoute) {
        if (newRoute != null) {
            return routesDao.createRoute(newRoute);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No route details provided in the request");
        }
    }

    //Update a route on the routes table - returning the updated route
    @RequestMapping(path="/routes/{routeId}", method= RequestMethod.PUT)
    public Routes updateRoute(@Valid @RequestBody Routes routeToUpdate, @PathVariable int routeId) {
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
