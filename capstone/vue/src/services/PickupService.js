import axios from 'axios';

export default {

    getPickupById(pickupId) {
        return axios.get(`/pickups/${pickupId}`);
    }, //remember to change this to http if we set a baseURL

    // getPickups() {
    //     return axios.get('/pickups/unassigned');
    // },

    getPickups() {
        return axios.get('/pickups');
    },

    getPickupByDriverId(driverId) {
        return axios.get(`/pickups/${driverId}`);
    },

    addPickup(pickup) {
        return axios.post('/pickups', pickup);
    },


    //update pickup(assign route id)
    updatePickup(pickup) {
        return axios.put(`/pickups/${pickup.pickup_id}`, pickup);
    },

    deletePickup(pickupId) {
        return axios.delete(`/pickups/${pickupId}`);
    },

   // get all pickups

  


    

    // getAddress(username){
    //     return axios.get('/users/myDetails/address', username)
    // }

    // get all unassigned pickup request from logged in users

    getPickupRequests(){
        return axios.get('/pickups/unassigned');
    }



}
