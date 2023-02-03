<template>
  <div id="app">
    <div class="navbar">
      <!-- Navbar -->
      <div id="nav-container">
        <img id="logo" src="./img/logo.png" alt="Vitrum Recycling Logo" />
        <router-link v-bind:to="{ name: 'home' }"><i class="fa fa-fw fa-home"></i>Home</router-link>
        <router-link v-bind:to="{ name: 'account'}"><i class="fa fa-fw fa-user"></i>Account</router-link>
        <!-- This router link for logout is going to dropdown from Account...once I figure it out -->
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap");

/* Why the heck is the gradient applying to ever box */
* {
  background: rgb(11, 122, 30);
  background: linear-gradient(
    0deg,
    rgba(11, 122, 30, 1) 0%,
    rgba(122, 198, 47, 1) 22%,
    rgba(252, 252, 252, 1) 95%
  );
}

#logo {
  float: left;
}
#nav {
  font-family: "Raleway", sans-serif;
  font-size: 1.5em;
  text-align: center;
  background-color: white;
  width: 100%;
}

a:link,
a:visited {
  /* background-color: green; */
  /* color: white; */
  padding: 14px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a:hover {
  /* background-color: blue; */
}
a:active {
  /* background-color: pink; */
}
/* .container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
} */
</style>
