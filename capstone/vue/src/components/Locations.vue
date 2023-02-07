<template>
  <div>
    <div id="grid-container">
      <div id="map"></div>

      <div id="latitude" class="coord-input">
        <span>Latitude:</span><br />
        <input id="latitude-input" type="text" /><br />
        <select id="latitude-direction">
          <option>North</option>
          <option>South</option></select
        ><br />
      </div>

      <div id="longitude" class="coord-input">
        <span>Longitude:</span><br />
        <input id="longitude-input" type="text" /><br />
        <select id="longitude-direction">
          <option>West</option>
          <option>East</option>
        </select>
        <br />
      </div>

      <div id="description" class="coord-input">
        <span class="menu-label">Description</span><br />
        <input id="description-input" type="text" /><br />
      </div>

      <div id="buttons" class="coord-input">
        <input v-on:click="addPinViaInput" type="button" value="Add Marker" />
        <br /><br />
        <input
          v-on:click="summarizeMarkers"
          type="button"
          value="Summarize All Markers"
        />
      </div>

      <div id="report"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Map",
  data() {
    return {
      map: null,
      mapCenter: { lat: 40.46083373916581, lng: -79.97458794232827},
      locations: [
        { coord: { lat: 40.46833, lng: -79.97458 }, name: "Vitrum Recylce Dropoff Center" },
        { coord: { lat: 40.42167, lng: -80.00757 }, name: "mraymond" },
        { coord: { lat: 40.47198, lng: -80.01398 }, name: "bblanc" },
        { coord: { lat: 40.44714, lng: -79.97501 }, name: "mbron" },
      ],
    };
  },
  methods: {
    initMap() {
      this.calculateCenter();
      this.map = new window.google.maps.Map(document.getElementById("map"), {
        center: this.mapCenter,
        zoom: 13,
        maxZoom: 20,
        minZoom: 3,
        streetViewControl: true,
        mapTypeControl: true,
        fullscreenControl: true,
        zoomControl: true,
      });
      let noPOIStyle = [
        {
          featureType: "poi",
          elementType: "labels",
          stylers: [{ visibility: "off" }],
        },
      ];
      this.map.setOptions({ styles: noPOIStyle });
      window.google.maps.event.addListener(this.map, "rightclick", (event) => {
        this.addPinViaClick(event);
      });
    },
    calculateCenter() {
      let latTotal = 0,
        lngTotal = 0;
      for (let i = 0; i < this.locations.length; i++) {
        latTotal += parseFloat(this.locations[i].coord.lat);
        lngTotal += parseFloat(this.locations[i].coord.lng);
      }
      const lat = latTotal / this.locations.length;
      const lng = lngTotal / this.locations.length;
      this.mapCenter = { lat: lat, lng: lng };
    },
    makeMarkerObj(latLng, name) {
      const markerObj = { coord: latLng, name: name };
      return markerObj;
    },
    addPinViaClick(event) {
      let description = window.prompt("Enter a description");
      const markerObj = this.makeMarkerObj(event.latLng.toJSON(), description);
      this.locations.push(markerObj);
      this.dropPin(markerObj);
    },
    addPinViaInput() {
      let latitudeValue = parseFloat(
        document.getElementById("latitude-input").value
      );
      let longitudeValue = parseFloat(
        document.getElementById("longitude-input").value
      );
      const latSelectIndex =
        document.getElementById("latitude-direction").selectedIndex;
      const lonSelectIndex = document.getElementById(
        "longitude-direction"
      ).selectedIndex;
      const latitudeDirection =
        document.getElementById("latitude-direction").options[latSelectIndex]
          .text;
      const longitudeDirection = document.getElementById("longitude-direction")
        .options[lonSelectIndex].text;
      const description = document.getElementById("description-input").value;
      latitudeValue =
        latitudeDirection === "North" ? latitudeValue : -1 * latitudeValue;
      longitudeValue =
        longitudeDirection === "East" ? longitudeValue : -1 * longitudeValue;
      const markerObj = this.makeMarkerObj(
        { lat: latitudeValue, lng: longitudeValue },
        description
      );
      this.locations.push(markerObj);
      this.dropPin(markerObj);
    },
    dropPins() {
      this.locations.forEach((x) => this.dropPin(x));
    },
    dropPin(markerObj) {
      new window.google.maps.Marker({
        position: markerObj.coord,
        map: this.map,
        label: {
          text: markerObj.name,
          color: "blue",
        },
      });
    },
    summarizeMarkers() {
      const dataSection = document.getElementById("report");
      let text = "<ol>";
      this.locations.forEach(
        (x) =>
          (text =
            text +
            `<li>Latitude: ${x.coord.lat} Longitude: ${x.coord.lng} Description: ${x.name}` +
            "</li>")
      );
      text += "</ol>";
      dataSection.innerHTML = text;
    },
  },
  mounted() {
    this.initMap();
    this.dropPins();
  },
};
</script>

<style>
#grid-container {
  font-family: "Raleway", sans-serif;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  grid-template-areas:
    "latitude longitude description buttons"
    "map map report report";
}
#latitude {
  grid-area: latitude;
}
#longitude {
  grid-area: longitude;
}
#description {
  grid-area: description;
}
#buttons {
  grid-area: buttons;
}
#map {
  grid-area: map;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: 25px;
}
#report {
  grid-area: report;
  font-family: "Raleway", sans-serif;
  width: 500px;
  height: 400px;
  padding: 25px;
  margin: 25px;
}

.coord-input {
  padding: 20px;
  margin: 20px;
}
.input-cell {
  vertical-align: top;
}
</style>