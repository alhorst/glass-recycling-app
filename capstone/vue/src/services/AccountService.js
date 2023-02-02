import axios from 'axios';


//should  we have fixed http??? Ask!
const http = axios.create({
    baseURL: "http://localhost:8083"
})

export default {

    //this method is to get all account details for user/driver based on username
    // getUserAccountInformation(username) {
    //     return http.get(`/users/${username}/details`)
    // },

    //this method is to get all account details for user/driver based on username
    getUserInfoById(userId) {
        return http.get(`/users/${userId}`)
    }

}