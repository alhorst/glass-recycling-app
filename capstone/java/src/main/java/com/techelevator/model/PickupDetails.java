package com.techelevator.model;

import javax.validation.constraints.Future;
import java.util.Date;

public class PickupDetails {

    private int pickup_id;
    private int route_id;
    private String requesting_username;
    //Blocks past or same day pickup requests - must be 1 day in advance
    @Future(message = "You must schedule a pickup for a future date")
    private Date pickup_date;
    private int pickup_weight;
    private int num_of_bins;
    private Boolean is_picked_up;



    public int getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(int pickup_id) {
        this.pickup_id = pickup_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getRequesting_username() {
        return requesting_username;
    }

    public void setRequesting_username(String requesting_username) {
        this.requesting_username = requesting_username;
    }

    public Date getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(Date pickup_date) {
        this.pickup_date = pickup_date;
    }

    public int getNum_of_bins() {
        return num_of_bins;
    }

    public void setNum_of_bins(int num_of_bins) {
        this.num_of_bins = num_of_bins;
    }

    public int getPickup_weight() {
        return pickup_weight;
    }

    public void setPickup_weight(int pickup_weight) {
        this.pickup_weight = pickup_weight;
    }

    public Boolean getIs_picked_up() {
        return is_picked_up;
    }

    public void setIs_picked_up(Boolean is_picked_up) {
        this.is_picked_up = is_picked_up;
    }
}
