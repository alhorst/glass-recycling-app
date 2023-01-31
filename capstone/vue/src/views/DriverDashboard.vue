<template>
  <div class="container">
      <div id="driver-dash">
          <div id="navbar">
              <nav>
          <ul>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            Dashboard Pick-ups Routes Account
          </ul>
        </nav>
        <router-view />
          </div>
      </div>
  </div>
</template>

<script>
export default {
  name: "driver-dash",
};
</script>

<style>
</style>