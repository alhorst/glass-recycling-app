package com.techelevator.model;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class Routes {

    private int routeId;
    //Blocks past or same day route creation - must be 1 day in advance
    @Future(message = "You must schedule a route for a future date")
    private Date routeDate;
    private int driverId;

    public Routes() {
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public Date getRouteDate() {
        return routeDate;
    }

    public void setRouteDate(Date routeDate) {
        this.routeDate = routeDate;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
}
