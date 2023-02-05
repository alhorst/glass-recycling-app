<!--
This will be used for user's account page after logged in!
Three columns( history, calendar, account)

-->

<template>
  <div class="account">
    <div class="my-account">
      <router-link :to="{ name: 'account-detail' }">My Account</router-link>
      <h4>
        <i>{{ user.username }}</i>
      </h4>
    </div>
    <!-- recycling history and calendar-->
    <!-- <div class="pick-up">
      <button v-on:click="showForm = !showForm">Schedule Pick up!</button>
        <pick-up v-show="showForm"></pick-up>
      
    </div> -->
    <div>
      <pick-up></pick-up>
    </div>

    <div class="history">
      <button>My Recycling History</button>
    </div>
  </div>
</template>

<script>
import AccountService from "../services/AccountService.js";
import PickUp from "../components/PickUp.vue";

export default {
  name: "account-main",
  components: {
    PickUp,
  },
  data() {
    return {
      user: "",
      expanded: false,
      showForm: false,
    };
  },

  //this created method update the user after we calling the method. then we can retrive the information that w want to here
  created() {
    //after saving account details, the form should be the same.
    //get address
    AccountService.getUserDetails().then((response) => {
      this.user = response.data;
    });
  },
};
</script>

<style scoped>
.account {
  display: flex;
 height: 800px;
  flex-flow: column wrap;
  font-size: 18px;
  width: 200px;
}

.my-account, .pick-up{
  border-radius: 0.8rem;
  padding: 20px;
  padding-bottom: 24px;
  background-color: whitesmoke;
  height: 40px;
  word-spacing: 1px;

  line-height: 0.5em;
  height: 2em; /* height is 2x line-height, so two lines will display */
  overflow: hidden;
  margin-bottom: 6px;
}

.history {
  border-radius: 0.8rem;
  padding: 20px;
  padding-bottom: 24px;
  background-color: whitesmoke;
  height: 40px;
  word-spacing: 1px;

  line-height: 0.5em;
  height: 2em; /* height is 2x line-height, so two lines will display */
  overflow: hidden;
  margin-bottom: 6px;
  align-self: flex-end;
}
</style>