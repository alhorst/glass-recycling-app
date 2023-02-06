package com.techelevator.dao;

import com.techelevator.model.PickupDetails;

import java.util.Date;
import java.util.List;

public interface PickupDetailsDao {

    PickupDetails getPickupDetailsByPickupId(int pickupId);

    List<PickupDetails> getAllUnassignedPickups();

    List<PickupDetails> getUnassignedPickupsByUsername(String username);

    List<PickupDetails> getAllPickupDetails();

    List<PickupDetails> getPickupDetailsByRecyclerUsername(String recyclerUsername);

    List<PickupDetails> getPickupDetailsByDriverUsername(String driverUsername);

    List<PickupDetails> getPickupDetailsByRouteId(int routeId);

    List<PickupDetails> getPickupDetailsByDriverId(int driverId);

    List<PickupDetails> getPickupDetailsByDate(Date pickupDate);

    PickupDetails createPickupDetails(PickupDetails pickupDetails);

    void updatePickupDetails(PickupDetails pickupDetails);

    void deletePickupDetails(int pickup_id);
}
