package com.techelevator.model;

import java.util.Date;

public class PickupDetails {

    private int pickup_id;
    private int driver_id;
    private int route_id;
    private String requesting_username;
    private Date pickup_date;
    private int num_of_bins;
    private Boolean is_picked_up;



    public int getPickup_id() {
        return pickup_id;
    }

    public void setPickup_id(int pickup_id) {
        this.pickup_id = pickup_id;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
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

    public Boolean getIs_picked_up() {
        return is_picked_up;
    }

    public void setIs_picked_up(Boolean is_picked_up) {
        this.is_picked_up = is_picked_up;
    }
}
