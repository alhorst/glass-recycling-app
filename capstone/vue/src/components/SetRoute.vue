<template>
  <div id="route-map">
    <h1>Route Map</h1>
  </div>
</template>

<script>
import PickupService from '../services/PickupService';
export default {
  name: "RouteMap",
  data() {
    return {
      unassignedPickups: [],
      filter: {
        pickup_id: "",
        route_id: "",
        requesting_username: "",
        pickup_date: "",
        num_of_bins: "",
        is_picked_up: false,
        full_address: "",
      },
      waypointArr: [],
    };
  },

  created() {
       PickupService.getPickups().then((response)=> {
           this.unassignedPickups = response.data;

   })

   },
  methods: {
    directionsService() {
      this.map = new window.google.maps.Map(
        document.getElementById("route-map"),
        {
          center: new window.google.maps.LatLng(
            40.46083373916581,
            -79.97458794232827
          ),
          zoom: 15,
          mapTypeId: window.google.maps.MapTypeId.ROADMAP,
        }
      );

      const directionsService = new window.google.maps.DirectionsService();

      directionsService.route(
        {
          origin: "3001 Railroad St, Pittsburgh, PA 15201",
          destination: "3001 Railroad St, Pittsburgh, PA 15201",
          waypoints: [
            {
              location: "4812 Broad St, Pittsburgh, PA 15244",
              stopover: true,
            },
            {
              location: "715 Ivy St, Pittsburgh, PA 15232",
              stopover: true,
            },
          ],

          travelMode: "DRIVING",
        },
        (response, status) => {
          if (status === "OK") {
            new window.google.maps.DirectionsRenderer({
              suppressMarkers: true,
              directions: response,
            });
          }
        }
      );
    },
  },
};
</script>

<style>
</style>