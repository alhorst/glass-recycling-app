package com.techelevator.dao;

import com.techelevator.model.PickupDetails;

import java.util.Date;
import java.util.List;

public interface PickupDetailsDao {

    PickupDetails getPickupDetails(int pickup_id);

    List<PickupDetails> getAllPickupDetails();

    List<PickupDetails> getPickupDetailsByUsername(String username);

    List<PickupDetails> getPickupDetailsByRouteId(int routeId);

    List<PickupDetails> getPickupDetailsByDriverId(int driverId);

    List<PickupDetails> getPickupDetailsByDate(Date pickupDate);

    PickupDetails createPickupDetails(PickupDetails pickupDetails);

    void updatePickupDetails(PickupDetails pickupDetails);

    void deletePickupDetails(int pickup_id);
}
