<template>
  <div class="container">
    <h2>Welcome to your Dashboard!</h2>
    <div class="map">
      <locations>MAP GOES HERE</locations>
    </div>
    <div id="card-pickup">
      <button v-on:click.prevent="created">Pickups</button>
      <table id="tbl-pickups">
        <thead id="tbl-head-pickups">
          <tr>
            <th>&nbsp;</th>
            <th>Pickup ID</th>
            <th>Route ID</th>
            <th>Requester</th>
            <th>Date</th>
            
            <th>Number of Bins</th>
            <th>Status</th>
            <th>Address</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>
              <!-- this method is working! -->
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
                v-model="filter.pickup_id"
              />
            </td>
            <td>
              <input
                type="text"
                id="lastNameFilter"
                v-model="filter.route_id"
              />
            </td>
            <td>
              <input
                type="text"
                id="usernameFilter"
                v-model="filter.requesting_username"
              />
            </td>
            <td>
              <input
                type="text"
                id="emailFilter"
                v-model="filter.pickup_date"
              />
            </td>
             <td>
              <select id="statusFilter" v-model="filter.num_of_bins">
                <option value>All Bins</option>
                <option value="Active">1</option>
                <option value="Disabled">2</option>
                <option value="">3</option>
              </select>
            </td> 
            <td>&nbsp;</td>
            <td>
              <input
                type="text"
                id="AddressFilter"
                v-model="filter.Address"
              />
            </td>
          </tr>
          <tr
            v-for="user in users"
            v-bind:key="user.pickup_id"
            v-bind:class="{ disabled: user.is_picked_up === 'Not Picked Up' }"
          >
             <td>
              <input
                type="checkbox"
                name="selectedUsers"
                v-model="selectedUserIDs"
                v-bind:checked="
                  selectedUserIDs.includes(Number.parseInt(user.pickup_id))
                "
                v-bind:value="Number.parseInt(user.pickup_id)"
              />
            </td> 
            <td>{{ user.pickup_id}}</td>
            <td>{{ user.route_id }}</td>
            <td>{{ user.requesting_username }}</td>
            <td>{{ user.pickup_date }}</td>
            <td>{{ user.num_of_bins}}</td>
            <td>
               <button class="btnEnableDisable" v-on:click="flipStatus(user.pickup_id)"
               @click="updatePickup">
                {{ user.is_picked_up === "Not Picked Up" ? "Picked Up" : "Not Picked Up" }}
                
              </button> 
            </td>
          </tr>
        </tbody>
      </table>

      <div class="all-actions">
             
        <button
         v-bind:disabled="actionButtonDisabled" 
          v-on:click.prevent="deletePickups()"
        >
          Delete Pickup
        </button> -->

         <button v-on:click="showForm = !showForm">Add New Pickup! ♻️</button> 

         <form id="frmAddNewPickup" v-show="showForm">
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
            Save Pickup
          </button>
        </form>
      </div>
  </div>
  </div>
</template>

<script>
import Locations from "./Locations.vue";
import PickupService from '../services/PickupService.js';

export default {
  name: "admin-page",
  components: {
    Locations,
  },
  data() {
    return {
      users: {
          pickup_id:'',
          route_id: '',
          requesting_username:'',
          pickup_date:'',
          num_of_bins:'',
          is_picked_up:false,

      },
      showForm: false,
      selectedUserIDs: [],
      filter: {
         pickup_id:'',
          route_id: '',
          requesting_username:'',
          pickup_date:'',
          num_of_bins:'',
          is_picked_up:false,
      },
      // nextUserId: 7,
      newUser: {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      },
     
    };
  },
  methods: {

    created(){
      //get all users unassigned pickup data here
      PickupService.getPickups().then((response)=> {
          this.users = response.data;
      })
      },
      //
      // deleteCard() {
      // if (
      //   confirm(
      //     "Are you sure you want to delete this card? This action cannot be undone."
      //   )
      // ) {
      //   boardsService
      //     .deleteCard(this.card.id)
      //     .then(response => {
      //       if (response.status === 200) {
      //         alert("Card successfully deleted");
      //         this.$router.push(`/board/${this.card.boardId}`);
      //       }
      //     })
      //     .catch(error => {
      //       if (error.response) {
      //         this.errorMsg =
      //           "Error deleting card. Response received was '" +
      //           error.response.statusText +
      //           "'.";
      //       } else if (error.request) {
      //         this.errorMsg =
      //           "Error deleting card. Server could not be reached.";
      //       } else {
      //         this.errorMsg =
      //           "Error deleting card. Request could not be created.";
      //       }
      //     });

      //admin needs to delete pickups
      deletePickups(){
         if (
        confirm(
          "Are you sure you want to delete this card? This action cannot be undone."
        )
    ) {
        for(let i=0;i < this.selectedUserIDs.length; i++){
        PickupService.deletePickup(this.selectedUserIDs[i]).then((response) => {
          if(response.status === 200){
            alert('pickup successfully deleted')
            this.$router.push('/admin')
          }
      
        }).catch(error => {
            if (error.response) {
              this.errorMsg =
                "Error deleting card. Response received was '" +
                error.response.statusText +
                "'.";
            } else if (error.request) {
              this.errorMsg =
                "Error deleting card. Server could not be reached.";
            } else {
              this.errorMsg =
                "Error deleting card. Request could not be created.";
            }
          });
        }
    
      }},

      // updatePickup(){
      //   PickupService.updatePickup().then(response=> {
      //     forEach((user) =>{
      //       this.user = response.data
      //     })
      // })
    // getNextUserId() {
    //   return this.nextUserId++;
    // },

    // saveUser() {
    //   this.newUser.id = this.getNextUserId();
    //   this.users.push(this.newUser);
    //   this.clearForm();
    // },

    // clearForm() {
    //   this.newUser = {
    //     id: null,
    //     firstName: "",
    //     lastName: "",
    //     username: "",
    //     emailAddress: "",
    //     status: "Active",
    //   };
    // },
    updatePickup(){
     const newPickUp = {
        pickup_id: this.pickup_id,
        route_id: this.route_id,
        requesting_username: this.requesting_username,
        pickup_date: this.pickup_date,
        num_of_bins: this.num_of_bins,
        is_picked_up: this.is_picked_up
     }
        PickupService.updatePickup(newPickUp).then(response=> {
        if(response.status === 200){
          this.$router.push('/admin')
        }
      })
  },

    flipStatus(pickup_id) {
      this.users.forEach((user) => {
        if (user.pickup_id === pickup_id) {
          if (user.is_picked_up === "Not Picked Up") {
            user.is_picked_up === " Picked Up";
            
          } else {
            user.is_picked_up = "Not Picked Up";
          }
        }
      });
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
          if (this.users[j].id === this.selectedUserIDs[i]) {
            if (statusToChange === "Active" || statusToChange === "Disabled") {
              this.users[j].status = statusToChange;
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
        for (let i = 0; i < this.users.length; i++) {
          this.selectedUserIDs.push(this.users[i].pickup_id);
        }
      } else {
        this.selectedUserIDs = [];
      }
    },
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
      let filteredUsers = this.users;
      if (this.filter.firstName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.firstName
            .toLowerCase()
            .includes(this.filter.firstName.toLowerCase())
        );
      }
      if (this.filter.lastName != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.lastName
            .toLowerCase()
            .includes(this.filter.lastName.toLowerCase())
        );
      }
      if (this.filter.username != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.username
            .toLowerCase()
            .includes(this.filter.username.toLowerCase())
        );
      }
      if (this.filter.emailAddress != "") {
        filteredUsers = filteredUsers.filter((user) =>
          user.emailAddress
            .toLowerCase()
            .includes(this.filter.emailAddress.toLowerCase())
        );
      }
      if (this.filter.status != "") {
        filteredUsers = filteredUsers.filter(
          (user) => user.status === this.filter.status
        );
      }
      return filteredUsers;
    },
  },
};
</script>

<style scoped>
.container {
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
}

.map {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: map;
  padding: 20px 0;
  background-color: white;
}

#card-pickup {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: pickups;
  padding: 20px 0;
  border: 1px solid red;
}

#card-driver {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  grid-area: drivers;
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
