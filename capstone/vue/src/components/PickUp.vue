<template>
  <div>
    <div class="row">
      <div class="col-md-12">
        <form v-on:submit.prevent>
          <h1>Sign Up</h1>

          <fieldset>
            <label for="name">Name:</label>
            <input type="text" id="name" name="user_name" v-model="pickup.requesting_username" />

            <label for="email">Date:</label>
            <input type="date" id="email" name="date" v-model="pickup.pickup_date"/>
             <label for="job">Number of Bins:</label>
            <select id="job" name="user_job" v-model="pickup.num_of_bins">
              <optgroup label="">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </optgroup>
            </select>
          </fieldset>
          <button type="submit" v-on:click="addPickUp()" >Submit</button>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
import PickupService from "../services/PickupService.js";

export default {
  name: "pick-up",
  data(){
      return {
          pickup: {
              requesting_username:'',
              pickup_date :'',
              num_of_bins : '',
              is_picked_up : false,
              pickup_weight:null,
              route_id :null,
          }
      }
  },
  methods: {
     addPickUp(){
      PickupService.addPickup(this.pickup).then((response)=> {
          if(response.status === 201){
              this.$router.push('/account')
          }
      })
     },
  }
  }



</script>

<style scoped>
*,
*:before,
*:after {
  -moz-box-sizing: border-box;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}

body {
  font-family: "Nunito", sans-serif;
  color: #384047;
}

form {
  max-width: 300px;
  margin: 10px auto;
  padding: 10px 20px;
  background: #f4f7f8;
  border-radius: 8px;
}

h1 {
  margin: 0 0 30px 0;
  text-align: center;
}

input[type="text"],
input[type="date"],
input[type="search"],
input[type="url"],
textarea,
select {
  background: rgba(255, 255, 255, 0.1);
  border: none;
  font-size: 16px;
  height: auto;
  margin: 0;
  outline: 0;
  padding: 15px;
  width: 100%;
  background-color: #e8eeef;
  color: #8a97a0;
  box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
  margin-bottom: 30px;
}

select {
  padding: 6px;
  height: 32px;
  border-radius: 2px;
}

button {
  padding: 19px 39px 18px 39px;
  color: #fff;
  background-color: #4bc970;
  font-size: 18px;
  text-align: center;
  font-style: normal;
  border-radius: 5px;
  width: 100%;
  border: 1px solid #3ac162;
  border-width: 1px 1px 3px;
  box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
  margin-bottom: 10px;
}

fieldset {
  margin-bottom: 30px;
  border: none;
}

legend {
  font-size: 1.4em;
  margin-bottom: 10px;
}

label {
  display: block;
  margin-bottom: 8px;
}

label.light {
  font-weight: 300;
  display: inline;
}

.number {
  background-color: #5fcf80;
  color: #fff;
  height: 30px;
  width: 30px;
  display: inline-block;
  font-size: 0.8em;
  margin-right: 4px;
  line-height: 30px;
  text-align: center;
  text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
  border-radius: 100%;
}

@media screen and (min-width: 480px) {
  form {
    max-width: 480px;
  }
}
</style>
