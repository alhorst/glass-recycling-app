<template>
  <div id="app">
    <div class="container">
      <div id="nav">
        <router-link v-bind:to="{ name: 'home' }"
          ><i class="fa fa-fw fa-home"></i>Home</router-link
        >
        <i class="fa fa-fw fa-envelope"></i>Contact
        <i class="fa fa-fw fa-user"></i>Login
        <router-link
          v-bind:to="{ name: 'logout' }"
          v-if="$store.state.token != ''"
          >Logout</router-link
        >
      </div>
    </div>
    <router-view />
  </div>
</template>

<style>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap");

#nav {
  font-family: "Raleway", sans-serif;
  font-weight: bold;
  font-size: 1.5em;
  text-align: center;
  /* background-color: green; */
  width: 100%;
}

a:link,
a:visited {
  /* background-color: green; */
  color: white;
  padding: 14px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a:hover {
  /* background-color: blue; */
}
a:active {
  background-color: pink;
}
/* .container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
} */
</style>
