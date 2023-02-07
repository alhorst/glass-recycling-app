import axios from 'axios';

export default {
    //add driver to the table

    addDriver(newDriver){
        return axios.post('/addDriver', newDriver);
    },

    //get all driver tables from db
    getAllDrivers(){
        return axios.get('/driverDetails')
    }
}