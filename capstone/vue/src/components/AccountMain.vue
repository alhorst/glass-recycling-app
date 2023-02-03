<!--
This will be used for user's account page after logged in!
Three columns( history, calendar, account)

-->

<template>
  <div class="account">
  <div>
<router-link :to="{name: 'account-detail'}" class="my-account">My Account</router-link>
<!-- //<div>{{user}}</div> -->
</div>
<!-- recycling history and calendar
<div>
    </div> 

    <div></div> -->
</div>
</template>

<script>

import AccountService from "../services/AccountService.js"

export default {
    name: "account-main",
     data() {
      return {
          user: '',
          expanded: false,
       }
     },

     //this created method update the user after we calling the method. then we can retrive the information that w want to here
   created(){
        //after saving account details, the form should be the same. 
        //get address
        AccountService.getUserDetails().then((response)=>{

       this.user = response.data
       })
       }
}
        

</script>

<style scoped>

.account {
    display: flex;
    flex-direction: row-reverse;
    font-size: 20px;
}

.my-account {
    border-radius: .5rem;
    padding: 18px;
    background-color: lightgray;

  
}

</style>