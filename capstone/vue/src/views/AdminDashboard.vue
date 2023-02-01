<template>
  <div class="container">
    <div id="admin-dash">
      <div id="navbar">
        <nav>
          <ul>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            Dashboard Pick-ups Routes Drivers Users Account Logout
          </ul>
        </nav>
        <router-view />
        <div class="dashboard">
          <h1>Dashboard</h1>
          <div class="map">MAP PLACEHOLDER</div>
          <h2>Pickups</h2>
          <table id="tbl-pickups">
            <thead>
              <tr class="tbl-headers">
                <th>Pickup ID</th>
                <th>Requester</th>
                <th>Date</th>
                <th>Number of Bins</th>
                <th>Driver</th>
                <th>Status</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>#8904</td>
                <th class="mobile-header">Sally Jones</th>
                <td>11/23/20</td>
                <th class="mobile-header">3</th>
                <td>Paul Bunyan</td>
                <th class="mobile-header">Checkbox</th>
              </tr>
              <tr>
                <td>#8905</td>
                <th class="mobile-header">Orlando Bloom</th>
                <td>11/24/20</td>
                <th class="mobile-header">1</th>
                <td>Mr. Socks</td>
                <th class="mobile-header">Checkbox</th>
              </tr>
              <tr>
                <td>#8906</td>
                <th class="mobile-header">Jonathan Davis</th>
                <td>11/29/20</td>
                <th class="mobile-header">2</th>
                <td>Timmy Nubbins</td>
                <th class="mobile-header">Checkbox</th>
              </tr>
            </tbody>
          </table>
          <h2>Pickups without drivers</h2>
          <table id="tbl-pickups-unassigned">
            <thead>
              <tr>
                <th>Pickup ID</th>
                <th>Requester</th>
                <th>Date</th>
                <th>Number of Bins</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>#8904</td>
                <th class="mobile-header">Spongebob</th>
                <td>12/01/20</td>
                <th class="mobile-header">3</th>
              </tr>
              <tr>
                <td>#8905</td>
                <th class="mobile-header">Patrick</th>
                <td>12/10/20</td>
                <th class="mobile-header">1</th>
              </tr>
              <tr>
                <td>#8906</td>
                <th class="mobile-header">Sandy Squirrel</th>
                <td>12/11/20</td>
                <th class="mobile-header">2</th>
              </tr>
            </tbody>
          </table>
          <h2>Drivers</h2>
          <table id="drivers">
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Availability</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>Paul Bunyan</td>
                <td>201-201-2029</td>
                <td>pbuyn@gmail.com</td>
                <td>Checkbox?</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- <tbody>
            <tr>
              <td>
                <input
                  type="text"
                  id="pickUpIDFilter"
                  v-model="filter.pickUpID"
                />
              </td>
              <td>
                <input
                  type="text"
                  id="requesterFilter"
                  v-model="filter.requester"
                />
              </td>
              <td>
                <input type="date" id="dateFilter" v-model="filter.date" />
              </td>
              <td>
                <input
                  type="number"
                  id="numberOfBinsFilter"
                  v-model="filter.numberOfBins"
                />
              </td>
              <td>
                <input type="text" id="driverFilter" v-model="filter.driver" />
              </td>
              <td>
                <input type="checkbox" />
              </td>
              <td>{{ pickup.pickupID }}</td>
              <td>{{ pickup.requester }}</td>
              <td>{{ pickup.date }}</td>
              <td>{{ pickup.numberOfBins }}</td>
              <td>{{ pickup.driver }}</td>
              <td>{{ pickup.status }}</td>
            </tr>
          </tbody> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "admin-dash",
};
</script>

<style scoped>
* {
  font-family: "Source Sans Pro";
  box-sizing: border-box;
}

.container {
  display: grid;
  grid-template-rows: 2em 8em 4em 4em 4em 2em;
  grid-template-areas:
    "nav"
    "map"
    "tblone"
    "tbltwo"
    "tblthree";
  grid-row-gap: 20px;
}

#navbar {
  grid-area: nav;
}
.dashboard {
  background: #008067;
}
.map {
  background: white;
  text-align: center;
  max-width: 100%;
  height: 35em;
  margin: 2em;
  grid-area: map;
}

#tbl-pickups {
  width: 100%;
  border-collapse: collapse;
  text-align: left;
  overflow: hidden;
  grid-area: tblone;
}

#tbl-pickups-unassigned {
  grid-area: tbltwo;
}
</style>