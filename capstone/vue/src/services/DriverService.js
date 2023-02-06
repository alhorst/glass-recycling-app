import axios from 'axios';

export default {
    //add driver to the table

    addDriver(newDriver){
        return axios.post('/addDriver', newDriver);
    }
}