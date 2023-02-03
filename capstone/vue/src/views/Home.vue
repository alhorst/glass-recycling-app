
<!-- This is splash page. user can see the splash page but can not access the details if not logged in --> 

<template>
  <div id="container">
    <div id="home" class="header">
      <!-- <img
        src="../img/image (1).png"
        alt="Glass Recycling Logo"
        class="logo"
     
      <section class="desc">Vitrum Recycle</section> /> -->
    </div>

    <!--container for information and the sign up, registration" -->
    <div class="body-container">
      <div class="first-container">
        <div class="info">
          <b>VITRUM</b> Recycle is a grassroots program offering curbside glass
          recycling in the Pittsburgh metro region. Despite its status as an
          infinitely recyclable material, 75% of glass containers used by
          consumers will be disposed of in landfills where it will never
          decompose.
          <!-- <img src="../img/bottles.png" alt="bottles" class="info-image" /> -->
        </div>
        <div class="detail-image"> </div>
      </div>
      <div class="mid-container">
        <div>
          Enroll with Vitrum today and our fleet of glass wranglers will deliver custom bins to your home or business.
        </div>
        <div></div>
      </div>
      <div class="last-container">
        <div></div>
        <div>When those bins are full, simply schedule a pickup date on our handy app and drop those wine bottles 
          and jam jars at the curb; they will be crushed to a fine sand which we sell or donate to our commercial 
          partners for a wide variety of sustainable applications: erosion remediation, creating road services, 
          and even to make swanky and unique kitchen countertops. Keep track of your recycling stats on your 
          dashboard and earn points with every pickup to redeem for cool prizes. Sign up today!</div>
      </div>
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
  font-family: 'Raleway', sans-serif;
}
#container{
  /* background-color: #bfe3bc; */
}

#first-container{
  display: flex;
  
  
}
.detail-image{
  flex: 1;
}

.detail {
  /* background-color: #24b4a5; */
  /* box-sizing: content-box;
  padding: 10px 370px 60px 100px; */
  /* border-right-style: solid;
  border-right-color: cornsilk;
  border-right-width: 5px; */
}

img {
  display: flex;
}

.last-container {

  padding: 150px;
  margin: 30px;
  height: 100px;
  width:600px;
  align-items: center;
  justify-content: center;
  text-align: justify;
  flex-direction: row;
}

.mid-container {
   padding: 200px;
  margin: 30px;
  height: 100px;
  width:600px;
  align-items: center;
  justify-content: center;
  text-align: justify;
  flex-direction: row;
  margin-left: auto;
}

.info {
  /* border-right-style: solid;
  border-right-color: cornsilk;
  border-right-width: 5px; */
  padding: 30px;
  margin: 30px;
  height: 100px;
  width:600px;
  align-items: center;
  justify-content: center;
  text-align: justify;
}

#log-in {
  /* margin: 0px 10px 0px 60px; */
  font-size: 20px;
  /* padding: 0rem; */
  align-items: center;
  justify-content: center;
  padding-left: 30px;
  border-radius: 90px;
}

#account {
  /* display: flex; */
  /* flex-direction: column; */
  /* background-color: #24b4a5; */
  /* padding:10px 5px 20px 0px; */
}

.desc {
  font-style: normal;
  font-weight: 500;
  letter-spacing: 0.02em;
  text-transform: none;
  font-size: 1.5rem;
}

#login:hover,
#register:hover {
  color: #24b4a5;
}

#container {
  /* display: flex;
  flex-direction: column; */
  /* align-items: center; */
  text-align: center;
margin-left: auto;
margin-right: auto
}
.account-sign {
  /* margin: 0px 10px 0px 60px; */
  font-size: 20px;
  /* background-color: #24b4a5; */
  padding-left: 30px;
  border-radius: 90px;
}

.header {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 25px;
}

.body-container {
  /* display: flex; */
  margin: auto;
  padding: 30px 0px 60px 100px;
  /* border-style: solid;
  border-color: cornsilk; */
  /* background-color: #24b4a5; */
  /* justify-content: space-evenly; */
  font-style: normal;
  font-weight: 500;
  letter-spacing: 0.01em;
  text-transform: none;
  font-size: 1.5rem;
}

.info-image {
  width: 300px;
  /* background-color: #24b4a5; */
}

/*check media again */
@media screen and (max-width: 1024px) {
  .body-container div {
    flex-wrap: wrap;
  }
}
</style>