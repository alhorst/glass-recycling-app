<template>
  <div id="route-map">
    <h1>VITRUM Route Map</h1>
    <div id="map"></div>
    <div id="panel"></div>
  </div>
</template>
<script>
import PickupService from "../services/PickupService";
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
  mounted() {
    this.directionsService();
  },
  created() {
    PickupService.getPickups().then((response) => {
      this.unassignedPickups = response.data;
    });
  },
  methods: {
    directionsService() {
      const map = new window.google.maps.Map(document.getElementById("map"), {
        zoom: 4,
        center: { lat: 40.46083373916581, lng: -79.97458794232827 },
      });
      const directionsService = new window.google.maps.DirectionsService();
      const directionsRenderer = new window.google.maps.DirectionsRenderer({
        draggable: true,
        map,
        panel: document.getElementById("panel"),
      });
        this.displayRoute(
            "3001 Railroad St, Pittsburgh, PA 15201",
            "3001 Railroad St, Pittsburgh, PA 15201",
            directionsService,
          directionsRenderer
  );
    },
    displayRoute(origin, destination, service, display) {
      service
        .route({
          origin: origin,
          destination: destination,
          waypoints: [
            { location: "4812 Broad St, Pittsburgh, PA 15244" },
            { location: "715 Ivy St, Pittsburgh, PA 15232" },
          ],
          travelMode: window.google.maps.TravelMode.DRIVING,
          avoidTolls: true,
        })
        .then((result) => {
          display.setDirections(result);
        })
        .catch((e) => {
          alert("Could not display directions due to: " + e);
        });
    },
  },
};
</script>
<style>
#map {
  width: 70vw;
  height: 500px;
  margin: auto;
  margin-bottom: 40px;
  padding-bottom: 40px;
}
</style>