
<!-- This is splash page. user can see the splash page but can not access the details if not logged in --> 

<template>
<div id="container">

  <div id="home" class="header">
    <section class="desc"> Welcome to GlassEco</section>
   
   <img src="../img/updateimage.png" alt= "Glass Recycling Logo" class="logo">

  </div>

  <!--container for information and the sign up, registration" -->
  <div class="body-container">
    <section class="info">Information about recycling</section>
    <div id="account">
      <nav id="sign-in" class="account-sign"><p>Don't have an acount yet? Register
        <router-link v-bind:to="{name: 'register'}">here!</router-link></p>
      </nav>
      <nav id="log-in">
        <p>If you have an account, please log in<router-link v-bind:to="{name: 'login'}"> here!</router-link></p>
      </nav>
    </div>  
  </div>

  <!--
  <nav>
      <ul>
        <li><a href="#">Home</a></li>
        <li><a href="#">Account</a></li>
      </ul>
  </nav>

  

<div class= "recycling-container">
  <h2 class="info">Information </h2>
</div>

<div class="footer-container">
  <div class="left-container">
    <section class="app-home">App Name</section>
    <section class="home-footer">Home</section>
    <section class="drop-off">Drop-off Hub</section>
    <section class="contact"> Contact</section>
    <section class="calendar">Calendar</section>
    
  </div>
  <div class="right-container">
    <img src="" alt= "Glass Recycling Logo" class="logo">
    <section class="address">Address</section>
  </div>

</div>
-->


</div>
</template>

<script>
export default {
  name: "home"
  //name: "account"
};
</script>


<style scope>

*{
  background-color:#BFE3BC ;
}

img{
 
  display: flex;

}

#log-in {
   display: flex;
  margin: 0px 100px 30px 690px;
  font-size: 20px;
  flex-direction:row;
  padding: 0rem;
}

#account {
  display: flex;
  flex-direction: column;
}




.desc {
font-family: cursive;
  font-size: 30px;
}

#container{
  display: flex;
  flex-direction: column;
 
}
.account-sign{
  display: flex;
  margin: 0px 100px 50px 690px;
  font-size: 20px;
  flex-direction:row;
  padding: 0rem;
}

.header {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items:center;
  padding:35px;

}

.body-container {
  display: flex;
  flex-direction: row;
  margin: 60px;
  padding: 30px 200px 200px 300px;
  font-family: cursive;
  font-size: 20px;
  border-style: solid;
  border-color: cornsilk;
  

}




</style>