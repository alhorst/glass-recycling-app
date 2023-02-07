<template>
  <!-- <div id="card-driver">
    <h2>Drivers</h2>
    <table id="tbl-drivers">
      <thead id="tbl-head-drivers">
        <tr>
          <th>&nbsp;</th>
          <th>Employee Id</th>
          <th>Username</th>
          <th>Home Office Address</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input
              type="checkbox"
              id="selectAll"
              v-on:change="selectAllUsers($event)"
            />
          </td>
          <td>
            <input
              type="text"
              id="firstNameFilter"
              v-model="filter.employee_id"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.username" />
          </td>
          <td>
            <input
              type="text"
              id="usernameFilter"
              v-model="filter.home_office_address"
            />
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="driver in drivers"
          v-bind:key="driver.employee_id"
          v-bind:class="{ disabled: driver.status === 'Disabled' }"
        >
          <td>
            <input
              type="checkbox"
              name="selectedUsers"
              v-model="selectedUserIDs"
              v-bind:checked="
                selectedUserIDs.includes(Number.parseInt(user.id))"
              v-bind:value="Number.parseInt(user.id)"
            />
          </td>
          <td>{{ driver.employee_id }}</td>
          <td>{{ driver.username }}</td>
          <td>
            <button class="btnEnableDisable" v-on:click="flipStatus(user.id)">
              {{ user.status === "Active" ? "Deactivate" : "Activate" }}
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <div class="all-actions">
      <button
        v-bind:disabled="actionButtonDisabled"
        v-on:click="deleteSelectedUsers()"
      >
        Delete Driver
      </button>

      <button v-on:click="showForm = !showForm">Add New Driver! 🚗</button>

      <form id="frmAddNewDriver" v-show="showForm">
        <div class="field">
          <label for="firstName">First Name:</label>
          <input type="text" name="firstName" v-model="newUser.firstName" />
        </div>
        <div class="field">
          <label for="lastName">Last Name:</label>
          <input type="text" name="lastName" v-model="newUser.lastName" />
        </div>
        <div class="field">
          <label for="username">Username:</label>
          <input type="text" name="username" v-model="newUser.username" />
        </div>
        <div class="field">
          <label for="emailAddress">Email Address:</label>
          <input
            type="text"
            name="emailAddress"
            v-model="newUser.emailAddress"
          />
        </div>
        <button type="submit" class="btn save" v-on:click.prevent="saveUser">
          Save Driver
        </button>
      </form>
    </div>
  </div> -->
  <div id="card-driver">
    <button v-on:click.prevent="created">Drivers</button>
    <table id="tbl-drivers">
      <thead id="tbl-head-drivers">
        <tr>
          <th>&nbsp;</th>
          <th>Driver Id</th>
          <th>Username</th>
          <th>Home Office Address</th>
        </tr>
      </thead>
      <tbody>
        <tr>
          <td>
            <input
              type="checkbox"
              id="selectAll"
              v-on:change="selectAllDrivers($event)"
            />
          </td>
          <td>
            <input
              type="text"
              id="firstNameFilter"
              v-model="filter.driver_id"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.username" />
          </td>
          <td>
            <input
              type="text"
              id="address"
              v-model="filter.home_office_address"
            />
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr v-for="driver in drivers" v-bind:key="driver.id">
          <td>
            <input
              type="checkbox"
              name="selectedUsers"
              v-model="selectedUserIDs"
              v-bind:checked="
                selectedUserIDs.includes(Number.parseInt(driver.employee_id))
              "
              v-bind:value="Number.parseInt(driver.employee_id)"
            />
          </td>
          <td>{{ driver.driver_id }}</td>
          <td>{{ driver.username }}</td>
          <td>{{ driver.home_office_address }}</td>
        </tr>
      </tbody>
    </table>
    <div id="all-actions">
      <button
        v-bind:disabled="actionButtonDisabled"
        v-on:click="deleteSelectedUsers()"
      >
        Delete Driver
      </button>

      <button v-on:click="showForm = !showForm">Add New Driver! 🚗</button>
      <form id="frmAddNewDriver" v-show="showForm">
        <div class="field">
          <label for="firstName">Username:</label>
          <input type="text" name="firstName" v-model="newDriver.username" />
        </div>
        <div class="field">
          <label for="firstName">Password:</label>
          <input type="text" name="firstName" v-model="newDriver.password" />
        </div>
        <div class="field">
          <label for="firstName">confirmPassword:</label>
          <input type="text" name="firstName" v-model="newDriver.confirmPassword" />
        </div>
        <div class="field">
          <label for="lastName">status:</label>
          <input type="text" name="lastName" v-model="newDriver.is_driver" />
        </div>
        <button type="submit" class="btn save" v-on:click.prevent="addDriver">
          Save Driver
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import DriverService from "../services/DriverService.js";
export default {
  name: "driver-table",
  data() {
    return {
      drivers: {
        driver_id: "",
        username: "",
        home_office_address: "",
      },
      selectedUserIDs: [],
      showForm: false,
      filter: {
        employee_id: "",
        username: "",
        home_office_address: "",
      },

      newDriver: {
        driver_id: '',
        password: "",
        confirmPassword: '',
        is_driver: true,
        role: "user",
        home_office_address: "3001 Railroad St, Pittsburgh, PA 15201",
      },
    };
  },
  methods: {

    //created method to get all updated driver list
    created(){
      DriverService.getAllDrivers().then(response =>{
        this.drivers = response.data
      })
    },
    //add newe driver to the table
    addDriver(){
      DriverService.addDriver(this.newDriver).then((response) => {
        if (response.status === 201) {
          // this.$router.push("/admin");
           DriverService.getAllDrivers().then(response =>{
        this.drivers = response.data
      })
        }
      })
    },
    enabledSelectedUsers() {
      this.changeStatus("Active");
    },
    disableSelectedUsers() {
      this.changeStatus("Disabled");
    },
    deleteSelectedUsers() {
      this.changeStatus("Delete");
    },
    changeStatus(statusToChange) {
      for (let i = 0; i < this.selectedUserIDs.length; i++) {
        for (let j = 0; j < this.users.length; j++) {
          if (this.users[j].pickup_id === this.selectedUserIDs[i]) {
            if (statusToChange === "Not Picked Up" || statusToChange === "Picked Up") {
              this.users[j].is_picked_up = statusToChange;
            } else if (statusToChange === "Delete") {
              this.users.splice(i, 1);
            }
          }
        }
      }
    },
    selectAllUsers(event) {
      if (event.target.checked) {
        this.selectedUserIDs = [];
        for (let i = 0; i < this.drivers.length; i++) {
          this.selectedUserIDs.push(this.drivers[i].driver_id);
        }
      } else {
        this.selectedUserIDs = [];
      }
    },
  computed: {
    actionButtonDisabled() {
      if (this.selectedUserIDs.length === 0) {
        return true;
      } else {
        return false;
      }
    },
    filteredList() {
      let filteredUsers = this.drivers;
      if (this.filter.driver_id != "") {
        filteredUsers = filteredUsers.filter((driver) =>
         driver.driver_id
            .toLowerCase()
            .includes(this.filter.driver_id.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((driver) =>
          driver.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      // if (this.filter.username != "") {
      //   filteredUsers = filteredUsers.filter((user) =>
      //     user.username
      //       .toLowerCase()
      //       .includes(this.filter.username.toLowerCase())
      //   );
      // }
      // if (this.filter.emailAddress != "") {
      //   filteredUsers = filteredUsers.filter((user) =>
      //     user.emailAddress
      //       .toLowerCase()
      //       .includes(this.filter.emailAddress.toLowerCase())
      //   );
      // }
      // if (this.filter.status != "") {
      //   filteredUsers = filteredUsers.filter(
      //     (user) => user.status === this.filter.status
      //   );
      // }
      return filteredUsers;
    },
  },
}
}
</script>

<style scoped>
/* <!-- .container {
  display: grid;
  grid-template-areas:
    "map"
    "pickups"
    "drivers";
  grid-template-rows: auto;
  gap: 40px;
  padding: 10px;
  border: 1px solid white;
  margin: 50px;
  text-align: center;
} --> */

/* <!-- .map {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: map;
  padding: 20px 0;
  background-color: white;
} -->

<!-- #card-pickup {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: pickups;
  padding: 20px 0;
  border: 1px solid red;
} --> */

#card-driver {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  /* grid-area: drivers; */
  padding: 20px 0;
  border: 1px solid blue;
}

h2 {
  font-size: 2em;
  font-family: "Raleway", sans-serif;
  text-transform: uppercase;
  background-color: rgb(36, 182, 126);
  text-align: center;
  margin-top: 5px;
  margin-bottom: 5px;
}
table {
  /* margin-top: 20px;  */
  font-family: "Raleyway", sans-serif;
  margin-bottom: 10px;
  background-color: grey;
  width: 100%;
}

#tbl-head-pickups {
  padding: 10px;
}

#tbl-head-drivers {
  padding: 10px;
}

th {
  text-transform: uppercase;
  background-color: grey;
}

td {
  padding: 5px;
}

tr:nth-child(even) {
  background-color: lightgreen;
}

tr.disabled {
  color: red;
}

input,
select {
  font-size: 16px;
}

#frmAddNewDriver {
  display: flex;
  align-items: center;
}

form {
  margin: 20px;
  width: 350px;
}

.field {
  padding: 10px 0px;
}

label {
  width: 135px;
  display: inline-block;
}

button {
  border: 1px solid white;
  background-color: white;
  padding: 6px 20px;
  text-align: center;
  font-size: 0.8em;
  text-decoration: none;
  display: inline-block;
  margin: 6px 4px;
  border-radius: 8px;
}

.all-actions {
  margin-bottom: 20px;
}

.btn.save {
  margin: 10px;
  float: right;
}
</style>
