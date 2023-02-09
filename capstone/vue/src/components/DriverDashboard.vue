<template>
  <div class="container">
    <div id="driver-dash">
      <div id="navbar">Dashboard Pick-ups Routes Account</div>
    </div>

    <set-route></set-route>
  </div>
</template>

<script>
import PickupService from "../services/PickupService.js";
import SetRoute from "./SetRoute.vue";
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
    }
  },
  // mounted() {
  //   this.SetRoute.directionsService();
  //   this.SetRoute.assignRouteOne();
  // },
  created() {
    PickupService.getPickups().then((response) => {
      this.users = response.data;
      // this.SetRoute.assignRouteOne();
    });
  },
};
</script>

<style>
/* #driver-dash {
  display: flex;
  align-items: center;
} */
</style>