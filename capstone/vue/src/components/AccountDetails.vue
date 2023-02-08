<template>
  <div>
    <form v-on:submit.prevent="saveAccountDetails">
      <div class="banner">
        <h1>My Account</h1>
      </div>
      <!-- <h2>Applicant Details</h2> -->
      <div class="item">
        <p>Username</p>
        <input type="text" name="name" v-model="userDetails.username" />
      </div>
      <div class="item">
        <p>FullName</p>
        <input type="text" name="fullname" v-model="userDetails.full_name" />
      </div>
      <div class="item">
        <p>Phone Number</p>
        <input type="text" name="name" v-model="userDetails.phone_number" />
      </div>
      <div class="item">
        <p>Email</p>
        <input type="text" name="name" v-model="userDetails.email_address" />
      </div>
      <div class="item">
        <p>Address</p>
        <input
          type="text"
          name="name"
          placeholder="Street address"
          v-model="userDetails.street_address"
        />
        <div class="city-item">
          <input
            type="text"
            name="name"
            placeholder="City"
            v-model="userDetails.city"
          />
          <input
            type="text"
            name="name"
            placeholder="State"
            v-model="userDetails.state_abbreviation"
          />
          <input
            type="text"
            name="name"
            placeholder="Postal / Zip code"
            v-model="userDetails.zipcode"
          />
        </div>
      </div>
      <div class="btn-block">
        <button type="button" v-on:click="cancel()">Cancel</button>
        <button type="submit" >Save</button>
      </div>
    </form>
  </div>
</template>

<script>
import AccountService from "../services/AccountService.js";
export default {
  name: "account-details",
  data() {
    return {
      userDetails: {
        username: "",
        full_name: "",
        street_address: "",
        city: "",
        state_abbreviation: "",
        zipcode: "",
        phone_number: "",
        email_address: "",
        // total_pounds_recycled:0,
        // credits_balance:0,
        // credits_redeemed:0,
      },
    };
  },

  created() {
    AccountService.getUserDetails().then((response) => {
      
        this.userDetails = Object.assign({},response.data);
         console.log(response.data)
      
      
    });
  },
  methods: {
    saveAccountDetails() {
      AccountService.addUserDetails(this.userDetails).then((response) => {
        if (response.status === 201) {
          this.$router.push("/account");
        }
      });
    },
    cancel() {
      this.$router.push("/account");
    },
  },
};
</script>

<style scoped>
html, body {
      min-height: 100%;
      }
      body, div, form, input, select, p { 
      padding: 0;
      margin: 0;
      outline: none;
      font-family: Roboto, Arial, sans-serif;
      font-size: 14px;
      color: #666;
      line-height: 22px;
      }
      h1 {
      position: absolute;
      margin: 0;
      font-size: 32px;
      color: #fff;
      z-index: 2;
      }
      h2 {
      font-weight: 400;
      }
      .testbox {
      display: flex;
      justify-content: center;
      align-items: center;
      height: inherit;
      padding: 20px;
      }
      form {
      width: 100%;
      padding: 20px;
      border-radius: 6px;
      background: #fff;
      box-shadow: 0 0 20px 0 #095484; 
      }
      .banner {
      position: relative;
      height: 210px;
      background-image: url("/uploads/media/default/0001/01/3f0d2969f2cffeb123ab1a6fa7408bf87bd34bd3.jpeg");  
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      text-align: center;
      }
      .banner::after {
      content: "";
      background-color: rgba(0, 0, 0, 0.4); 
      position: absolute;
      width: 100%;
      height: 100%;
      }
      input, select {
      margin-bottom: 10px;
      border: 1px solid #ccc;
      border-radius: 3px;
      }
      input {
      width: calc(100% - 10px);
      padding: 5px;
      }
      select {
      width: 100%;
      padding: 7px 0;
      background: transparent;
      }
      .item:hover p, .item:hover i, .question:hover p, .question label:hover, input:hover::placeholder, a {
      color: #095484;
      }
      .item input:hover, .item select:hover {
      border: 1px solid transparent;
      box-shadow: 0 0 6px 0 #095484;
      color: #095484;
      }
      .item {
      position: relative;
      margin: 10px 0;
      }
      input[type="date"]::-webkit-inner-spin-button {
      display: none;
      }
      .item i, input[type="date"]::-webkit-calendar-picker-indicator {
      position: absolute;
      font-size: 20px;
      color: #a9a9a9;
      }
      .item i {
      right: 2%;
      top: 30px;
      z-index: 1;
      }
      [type="date"]::-webkit-calendar-picker-indicator {
      right: 1%;
      z-index: 2;
      opacity: 0;
      cursor: pointer;
      }
      input[type=checkbox]  {
      display: none;
      }
      label.check {
      position: relative;
      display: inline-block;
      margin: 5px 20px 10px 0;
      cursor: pointer;
      }
      .question span {
      margin-left: 30px;
      }
      span.required {
      margin-left: 0;
      color: red;
      }
      label.check:before {
      content: "";
      position: absolute;
      top: 2px;
      left: 0;
      width: 16px;
      height: 16px;
      border-radius: 2px;
      border: 1px solid #095484;
      }
      input[type=checkbox]:checked + .check:before {
      background: #095484;
      }
      label.check:after {
      content: "";
      position: absolute;
      top: 6px;
      left: 4px;
      width: 8px;
      height: 4px;
      border: 3px solid #fff;
      border-top: none;
      border-right: none;
      transform: rotate(-45deg);
      opacity: 0;
      }
      input[type=checkbox]:checked + label:after {
      opacity: 1;
      }
      .btn-block {
      margin-top: 10px;
      text-align: center;
      }
      button {
      width: 150px;
      padding: 10px;
      border: none;
      border-radius: 5px; 
      background: #095484;
      font-size: 16px;
      color: #fff;
      cursor: pointer;
      }
      button:hover {
      background: #0666a3;
      }
      @media (min-width: 568px) {
      .name-item, .city-item {
      display: flex;
      flex-wrap: wrap;
      justify-content: space-between;
      }
      .name-item input, .city-item input {
      width: calc(50% - 20px);
      }
      .city-item select {
      width: calc(50% - 8px);
      }
      }
</style>