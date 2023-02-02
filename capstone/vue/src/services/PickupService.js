import axios from 'axios';

export default {

    getPickupById(pickupId) {
        return axios.get(`/pickups/${pickupId}`)
    }, //remember to change this to http if we set a baseURL

    getPickups() {
        return axios.get('/pickups')
    },

    getPickupByDriverId(driverId) {
        return axios.get(`/pickups/${driverId}`)
    },



}
