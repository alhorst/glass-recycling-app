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
        <table id="tblPickups">
          <thead>
            <tr>
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
          </tbody>
        </table>
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
#navbar {
  display: flex;
  justify-content: center;
  align-items: flex-start;
}
</style>