package com.techelevator.dao;

import com.techelevator.model.PickupDetails;

import java.util.Date;
import java.util.List;

public interface PickupDetailsDao {

    PickupDetails getPickupDetails(int pickup_id);

    List<PickupDetails> getPickupDetailsByDriverId(int driver_Id);

    List<PickupDetails> getPickupDetailsByDate(Date pickup_date);

    PickupDetails createPickupDetails(PickupDetails pickupDetails);

    void updatePickupDetails(PickupDetails pickupDetails);

    void deletePickupDetails(int pickup_id);
}
