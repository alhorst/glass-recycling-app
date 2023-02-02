
<!-- This is splash page. user can see the splash page but can not access the details if not logged in --> 

<template>
  <div id="container">
    <div id="home" class="header">
      <section class="desc">Welcome to Vitrum Recycle</section>

      <img
        src="../img/updateimage.png"
        alt="Glass Recycling Logo"
        class="logo"
      />
    </div>

    <!--container for information and the sign up, registration" -->
    <div class="body-container">
      <div class="info">
        Information about recycling
        <img src="../img/bottles.png" alt="bottles" class="info-image" />
      </div>
      <div class="detail">detail</div>
      <div id="account">
        <nav id="sign-in" class="account-sign">
          <p>
            Don't have an acount yet? Register
            <router-link id="register" v-bind:to="{ name: 'register' }"
              >here!</router-link
            >
          </p>
        </nav>
        <nav id="log-in">
          <p>
            If you have an account, please log in<router-link
              id="login"
              v-bind:to="{ name: 'login' }"
            >
              here!</router-link
            >
          </p>
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
  name: "home",
  //name: "account"
};
</script>


<style scope>
* {
  background-color: #bfe3bc;
}

.detail {
  background-color: #24b4a5;
  box-sizing: content-box;
  padding: 10px 300px 60px 100px;
  border-right-style: solid;
  border-right-color: cornsilk;
  border-right-width: 5px;

}

img {
  display: flex;
}

.info {
  border-right-style: solid;
  border-right-color: cornsilk;
  border-right-width: 5px;
   align-items: center;
  justify-content: center;
  text-align: center;
  
}

#log-in {
  margin: 0px 10px 0px 60px;
  font-size: 20px;
  padding: 0rem;
  align-items: center;
  justify-content: center;
  
}

#account {
  display: flex;
  flex-direction: column;
  background-color: #24b4a5;
  padding:10px 5px 20px 0px;
}

.desc {
  font-family: cursive;
  font-size: 30px;
}

#login:hover,
#register:hover {
  color: #24b4a5;
}

#container {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.account-sign {
  margin: 0px 10px 0px 60px;
  font-size: 20px;
  background-color: #24b4a5;
  padding-left:2px;
}

.header {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 35px;
}

.body-container {
  display: flex;
  margin: 40px;
  padding: 30px 60px 60px 100px;
  font-family: cursive;
  font-size: 20px;
  border-style: solid;
  border-color: cornsilk;
  background-color: #24b4a5;
  justify-content:space-evenly;

}

.info-image {
  width: 300px;
  background-color: #24b4a5;
}


/*check media again */
@media screen and (max-width: 1024px) {
  .body-container div{
    flex-wrap: wrap;
  }
}
</style>