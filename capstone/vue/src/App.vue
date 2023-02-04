<template>
  <div id="app">
    <!-- Nav bar -->
    <div class="nav-container">
      <!-- <div id="navbar"> -->
      <img id="logo" src="./img/logo-clear.png" alt="Vitrum Recycling Logo" />
      <nav>
        <ul>
          <li>
            <router-link v-bind:to="{ name: 'home' }"
              ><i class="fa-solid fa-house"></i>Home</router-link
            >
          </li>
          <li><i class="fa-solid fa-recycle"></i>Services</li>
        </ul>
      </nav>
      <div class="spacer">&nbsp;</div>
      <router-link v-bind:to="{ name: 'account' }"
        >Account<i class="fa-solid fa-user"></i
      ></router-link>
      <!-- This router link for logout is going to dropdown from Account...once I figure it out -->
      <router-link
        v-bind:to="{ name: 'logout' }"
        v-if="$store.state.token != ''"
        >Logout</router-link
      >
      <!-- </div> -->
    </div>
    <router-view />
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap");

html {
  background: linear-gradient(
    0deg,
    rgba(11, 122, 30, 1) 0%,
    rgba(122, 198, 47, 1) 22%,
    rgba(252, 252, 252, 1) 95%
  );
}

#logo {
  float: left;
  padding-left: 10px;
  padding-top: 10px;
  height: auto;
  width: 15%;
}
.nav-container {
  border: 1px solid red;
  background-color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  position: fixed;
  font-size: 20px;
  padding: 10px;
  width: 100%;
  left: 0;
  top: 0;
  z-index: 1;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

/* #navbar {
  background-color: white;
  padding: 0.2em;
  width: 100%;
} */

ul {
  display: flex;
  justify-content: flex-start;
  list-style-type: none;
}

li {
  font-size: 20px;
  font-weight: 600;
  padding: 0 20px 0 0;
  margin-left: 10px;
  margin-top: 10px;
  color: #0b7a1e;
}

i {
  padding-right: 5px;
  padding-left: 10px;
}
.spacer {
  flex-grow: 1;
}

a {
  box-shadow: inset 0 0 0 0 #0b7a1e;
  color: #0b7a1e;
  margin: 0 -0.25rem;
  padding: 0 0.25rem;
  transition: color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  text-decoration: none;
  text-align: center;
  margin-right: 20px;
  font-weight: 600;
}

a:hover {
  box-shadow: inset 200px 0 0 0 #9bdb66;
  color: white;
}
</style>
