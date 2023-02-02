<template>
    <form v-on:submit.prevent>
    <div class="field">
      <label for="name">UserName</label>
      <input type="text" v-model="userDetails.username" />
    </div>
    <div class="field">
      <label for="author">FullName</label>
      <input type="text" v-model="userDetails.full_name" />
    </div>
    <div class="field">
      <label for="content">Address</label>
      <input spellcheck="false" v-model="userDetails.street_address" />
    </div>
     <div class="field">
      <label for="content">City</label>
      <input spellcheck="false" v-model="userDetails.city" />
    </div>
     <div class="field">
      <label for="content">State</label>
      <input spellcheck="false" v-model="userDetails.state_abbreviation" />
    </div>
     <div class="field">
      <label for="content">ZipCode</label>
      <input spellcheck="false" v-model="userDetails.zipcode" />
    </div>
     <div class="field">
      <label for="content">Phone</label>
      <input spellcheck="false" v-model="userDetails.phone_number" />
    </div>
     <div class="field">
      <label for="content">Email Address</label>
      <input spellcheck="false" v-model="userDetails.email_address" />
    </div>
    <div class="actions">
      <button type="button" v-on:click="cancel()">Cancel</button>&nbsp;
      <button type="submit" v-on:click="saveAccountDetails()">Save Document</button>
    </div>
  </form>
</template>

<script>
import AccountService from "../services/AccountService.js";
export default {
    name: "account-details",
    data() {
        return {
            userDetails: {
            username: '',
            full_name: '',
            street_address: '',
            city: '',
            state_abbreviation: '',
            zipcode: '',
            phone_number:'',
            email_address:'',

        }
        }
    },
// methods:{
//     setUser(user){
//         this.$store.commit('SET_USER', user)

methods: {
    saveAccountDetails(){
        AccountService.addUserDetails(this.userDetails).then((response) => {
            if(response.status === 201) {
                //
                this.$router.push('/account')
            }
        })
    },
     cancel() {
      this.$router.push("/account");
    }
}
}

</script>

<style scoped>
form {
  padding: 20px;
  font-size: 16px;
  width: 500px;
}
form * {
  box-sizing: border-box;
  line-height: 1.5;
}
.field {
  display: flex;
  flex-direction: column;
}
.field label {
  margin: 4px 0;
  font-weight: bold;
}
.field input,
.field textarea {
  padding: 8px;
}
.field textarea {
  height: 300px;
}
.actions {
  text-align: right;
  padding: 10px 0;
}
</style>