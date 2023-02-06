<template>
  <div id="app">
    <!-- Nav container -->
    <div class="toggle">
      <i class="fa-solid fa-bars" @click="showMenu()"></i>
    </div>
    <nav :class="this.showMobileMenu ? 'open-menu' : 'closed-menu'">
      <img id="logo" src="./img/logo-clear.png" alt="Vitrum Recycling Logo" />
      <ul>
        <li>
          <router-link v-bind:to="{ name: 'home' }"
            ><i class="fa-solid fa-house"></i
          ></router-link>
        </li>
        <li>About Us</li>
        <li>Services</li>
        <li>Schedule Pickup</li>
        <li>Contact Us</li>
      </ul>
      <input type="text" placeholder="Search..." />
      <!-- <div class="spacer">&nbsp;</div> -->
      <div class="dropdown">
        <!--I need to change name to "account-detail" because of I can't see the account form"Kubra-->
        <router-link v-bind:to="{ name: 'account' }"
          >Account<i class="fa-solid fa-user"></i
        ></router-link>
        <div class="dropdown-content">
          <router-link id="login" v-bind:to="{ name: 'login' }">
            Login</router-link
          >
          <router-link
            id="logout"
            v-bind:to="{ name: 'logout' }"
            v-if="$store.state.token != ''"
            >Logout</router-link
          >
          <!-- for some reason, with the v-if 'logout' doesn't show on dropdown -->
        </div>
      </div>
    </nav>
    <div class="main-view">
      <router-view />
    </div>
    <footer>
      <div class="footer-content">
        <div>
          <ul class="recycling">
            <h3>Recycling</h3>
            <li>Where to recycle</li>
            <li>How to recycle</li>
            <li>Recycle an item</li>
          </ul>
        </div>
        <div>
          <ul class="help-info">
            <h3>Help and Info</h3>
            <li>FAQs</li>
            <li>News</li>
            <li>Careers</li>
          </ul>
        </div>
        <div>
          <h3>Follow us</h3>
          <ul class="icons">
            <li><i class="fa-brands fa-facebook"></i></li>
            <li><i class="fa-brands fa-twitter"></i></li>
            <li><i class="fa-brands fa-instagram"></i></li>
          </ul>
        </div>
      </div>
    </footer>
  </div>
</template>
<script>
export default {
  data() {
    return {
      showMobileMenu: false,
    };
  },

  methods: {
    showMenu() {
      this.showMobileMenu = !this.showMobileMenu;
    },
  },
};
</script>

<style scope>
@import url("https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap");

html {
  background: white;
  font-family: "Raleway", sans-serif;
}

#app {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

#logo {
  float: left;
  padding-left: 10px;
  height: auto;
  width: 15%;
}

.main-view {
  margin-top: 3.2%;
}

nav {
  background-color: white;
  position: fixed;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  font-size: 20px;
  padding: 10px 30px;
  width: 100vw;
  left: 0;
  top: 0;
  z-index: 1;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
  border: 1px solid red;
}

ul {
  border: 1px solid red;
  display: flex;
  margin: 0;
  padding: 0;
  list-style: none;
}

li {
  font-size: 20px;
  font-weight: 600;
  padding: 0 20px 0 10px;
  margin-left: 10px;
  color: #0b7a1e;
}

input[type="text"] {
  border: 1px solid rgb(189, 188, 188);
  border-radius: 20px;
  padding: 10px;
  font-size: 17px;
  /* float: right; */
  padding-left: 10px;
  padding-right: 30px;
}
button {
  --color: #21a076;
  padding: 0.5em 0.5em 0.5em 1em;
  background-color: transparent;
  border-radius: 7em;
  position: relative;
  overflow: hidden;
  cursor: pointer;
  transition: 0.5s;
  font-weight: 500;
  font-size: 17px;
  margin-left: 8em;
  margin-right: 3em;
  border: 1px solid;
  font-family: inherit;
  text-transform: uppercase;
  color: var(--color);
  z-index: 1;
}

button::before,
.button::after {
  content: "";
  display: block;
  width: 50px;
  height: 20px;
  transform: translate(-50%, -50%);
  position: absolute;
  border-radius: 50%;
  z-index: -1;
  background-color: var(--color);
  transition: 1s ease;
}

button::before {
  top: -1em;
  left: -1em;
}

button::after {
  left: calc(100% + 1em);
  top: calc(100% + 1em);
}

button:hover::before,
.button:hover::after {
  height: 410px;
  width: 410px;
}

button:hover {
  color: rgb(255, 255, 255);
}

button:active {
  filter: brightness(0.8);
}

.dropdown {
  border: 1px solid red;
}

.toggle {
  display: none;
}

i {
  padding-right: 5px;
  padding-left: 7px;
}
/* .spacer {
  flex-grow: 1;
} */

a {
  box-shadow: inset 0 0 0 0 #0b7a1e;
  color: #0b7a1e;
  margin: 0 -0.25rem;
  padding: 0 0.25rem;
  transition: color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  text-decoration: none;
  text-align: center;
  margin-right: 10px;
  font-weight: 600;
}

a:hover {
  box-shadow: inset 200px 0 0 0 #9bdb66;
  color: white;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: white;
  min-width: 140px;
  right: 0;
  box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.5);
  z-index: 1;
}

.dropdown-content a {
  text-decoration: none;
  display: block;
  box-shadow: inset 0 0 0 0 #0b7a1e;
  color: #0b7a1e;
  margin: 0 -0.25rem;
  padding: 0.5rem;
  transition: color 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
}
/* why the heck is this text blue on hover!!! */
.dropdown-content a:hover {
  background-color: #9bdb66;
  box-shadow: inset 200px 0 0 0 white;
  color: black;
}

.dropdown:hover .dropdown-content {
  display: block;
}

footer {
  border: 1px solid blue;
  display: flex;
  position: absolute;
  text-align: center;
  height: auto;
  width: 100vw;
  left: 0;
  right: 0;
  padding-top: 1.5em;
  border-top: 5px solid #9bdb66;
  background-color: white;
}

.footer-content {
  /* display: flex;
  align-items: center;
  text-align: center;
  justify-content: center;
  flex-direction: column;
  text-align: center; */
}

.footer-content div {
  display: block;
}

.icons {
  list-style: none;
  display: flex;
  align-items: center;
  justify-content: center;
}

@media screen and (max-width: 768px) {
  .nav-container {
    padding-top: 10px;
    position: absolute;
    width: 100%;
  }

  #logo {
    display: none;
  }

  .open-menu {
    opacity: 1;
    height: 150px;
  }

  .closed-menu {
    opacity: 0;
    height: 0;
    padding: 0;
  }

  nav {
    flex-direction: column;
    z-index: 100;
    position: relative;
    transition: all 0.2s ease-out;
  }

  nav ul {
    flex-direction: column;
  }

  .toggle {
    display: block;
    text-align: right;
    padding: 0 10px 10px 0;
  }
}
</style>



