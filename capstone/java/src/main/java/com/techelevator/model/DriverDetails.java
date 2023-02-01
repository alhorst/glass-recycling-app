package com.techelevator.model;

import java.sql.Driver;

public class DriverDetails {

    private int employee_id;
    private String username;
    private String home_office_address;

    public DriverDetails() { }

    public DriverDetails(int employee_id, String username, String home_office_address) {
        this.employee_id = employee_id;
        this.username = username;
        this.home_office_address = home_office_address;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHome_office_address() {
        return home_office_address;
    }

    public void setHome_office_address(String home_office_address) {
        this.home_office_address = home_office_address;
    }

    @Override
    public String toString() {
        return "DriverDetails{" +
                "employee_id=" + employee_id +
                ", username='" + username + '\'' +
                ", home_office_address='" + home_office_address + '\'' +
                '}';
    }
}
