import axios from 'axios';

export default {

    getPickupById(pickupId) {
        return axios.get(`/pickups/${pickupId}`);
    }, //remember to change this to http if we set a baseURL

    getPickups() {
        return axios.get('/pickups');
    },

    getPickupByDriverId(driverId) {
        return axios.get(`/pickups/${driverId}`);
    },

    addPickup(pickup) {
        return axios.post('/pickups', pickup);
    },

    updatePickup(pickup) {
        return axios.put(`/pickups/${pickup.pickupId}`, pickup);
    },

    deletePickup(pickupId) {
        return axios.delete(`/pickups/${pickupId}`);
    }



}
