<template>
  <div class="container">
    <div>
      <h2>Drivers</h2>
    </div>
    <table id="tbl-drivers">
      <thead id="tbl-head-drivers">
        <tr>
          <th>&nbsp;</th>
          <th>First Name</th>
          <th>Last Name</th>
          <th>Phone Number</th>
          <th>Email Address</th>
          <th>Status</th>
          <th>Actions</th>
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
              v-model="filter.firstName"
            />
          </td>
          <td>
            <input type="text" id="lastNameFilter" v-model="filter.lastName" />
          </td>
          <td>
            <input type="text" id="usernameFilter" v-model="filter.username" />
          </td>
          <td>
            <input type="text" id="emailFilter" v-model="filter.emailAddress" />
          </td>
          <td>
            <select id="statusFilter" v-model="filter.status">
              <option value>Show All</option>
              <option value="Active">Active</option>
              <option value="Disabled">Disabled</option>
            </select>
          </td>
          <td>&nbsp;</td>
        </tr>
        <tr
          v-for="user in filteredList"
          v-bind:key="user.id"
          v-bind:class="{ disabled: user.status === 'Disabled' }"
        >
          <td>
            <input
              type="checkbox"
              name="selectedUsers"
              v-model="selectedUserIDs"
              v-bind:checked="
                selectedUserIDs.includes(Number.parseInt(user.id))
              "
              v-bind:value="Number.parseInt(user.id)"
            />
          </td>
          <td>{{ user.firstName }}</td>
          <td>{{ user.lastName }}</td>
          <td>{{ user.username }}</td>
          <td>{{ user.emailAddress }}</td>
          <td>{{ user.status }}</td>
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
          Save User
        </button>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "user-list",
  data() {
    return {
      showForm: false,
      selectedUserIDs: [],
      filter: {
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "",
      },
      nextUserId: 7,
      newUser: {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      },
      users: [
        {
          id: 1,
          firstName: "John",
          lastName: "Smith",
          username: "jsmith",
          emailAddress: "jsmith@gmail.com",
          status: "Active",
        },
        {
          id: 2,
          firstName: "Anna",
          lastName: "Bell",
          username: "abell",
          emailAddress: "abell@yahoo.com",
          status: "Active",
        },
        {
          id: 3,
          firstName: "George",
          lastName: "Best",
          username: "gbest",
          emailAddress: "gbest@gmail.com",
          status: "Disabled",
        },
        {
          id: 4,
          firstName: "Ben",
          lastName: "Carter",
          username: "bcarter",
          emailAddress: "bcarter@gmail.com",
          status: "Active",
        },
        {
          id: 5,
          firstName: "Katie",
          lastName: "Jackson",
          username: "kjackson",
          emailAddress: "kjackson@yahoo.com",
          status: "Active",
        },
        {
          id: 6,
          firstName: "Mark",
          lastName: "Smith",
          username: "msmith",
          emailAddress: "msmith@foo.com",
          status: "Disabled",
        },
      ],
    };
  },
  methods: {
    getNextUserId() {
      return this.nextUserId++;
    },

    saveUser() {
      this.newUser.id = this.getNextUserId();
      this.users.push(this.newUser);
      this.clearForm();
    },

    clearForm() {
      this.newUser = {
        id: null,
        firstName: "",
        lastName: "",
        username: "",
        emailAddress: "",
        status: "Active",
      };
    },

    flipStatus(id) {
      this.users.forEach((user) => {
        if (user.id === id) {
          if (user.status === "Active") {
            user.status = "Disabled";
          } else {
            user.status = "Active";
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
          this.selectedUserIDs.push(this.users[i].id);
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
  border: 1px solid red;
  margin: 50px;
  background-color: rgb(36, 182, 126);
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  box-sizing: border-box;
  text-align: center;
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
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Oxygen,
    Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
  margin-bottom: 20px;
}

#tbl-drivers {
  width: 100%;
}

#tbl-head-drivers {
  padding: 10px;
}

th {
  text-transform: uppercase;
}

td {
  padding: 10px;
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
