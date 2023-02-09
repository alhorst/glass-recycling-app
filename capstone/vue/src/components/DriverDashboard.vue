<template>
  <div class="container">
    <div id="driver-dash">
      <div id="navbar">Pick-Ups Routes Account</div>
    </div>
    <div>
      <h3>Driver: {{$store.state.user.username}}</h3>
    </div>

    <set-route></set-route>
  </div>
</template>

<script>
import PickupService from "../services/PickupService.js";
import SetRoute from "./SetRoute.vue";
//import AccountService from "../services/AccountService.js";
export default {
  name: "driver-dashboard",
  components: {
    SetRoute,
  },
  data(){
    return {
      users: {
        pickup_id: "",
        route_id: "",
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
        full_address: "",
      },
      user:'',
    }
  },
  // mounted() {
  //   this.SetRoute.directionsService();
  //   this.SetRoute.assignRouteOne();
  // },

  //this created can be seen only Admin, this getPickups( ) return all pickups information
  // created() {
  //   PickupService.getPickups().then((response) => {
  //     this.users = response.data;
      // this.SetRoute.assignRouteOne();

  //      created() {
  
  //   AccountService.getUserDetails().then((response) => {
  //     this.user = response.data;
  //   });
  // },
  methods: {

      getDriverPickups(){
        PickupService.getDriverPickUpRoutes(this.user).then(response => {
          this.users= response.data;
        })
      }
  }
}
</script>

<style>
/* #driver-dash {
  display: flex;
  align-items: center;
} */

#navbar {
  font-size: 25px;
  text-align:center;
  padding: 13px;
}
</style>