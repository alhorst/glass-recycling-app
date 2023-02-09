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
  name: "driver-dash",
  components: {
    SetRoute,
  },
  mounted() {
    this.SetRoute.directionsService();
    this.assignRouteOne();
  },
  created() {
    PickupService.getPickups().then((response) => {
      this.users = response.data;
      this.SetRoute.assignRouteOne();
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