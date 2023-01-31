<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
     <label class="container">
  <input type="radio" name="radio">
  <span class="checkmark">Login as Bussiness Admin</span>
</label>
<br>
      <label for="username" class="sr-only"><b>Username: </b></label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <label for="password" class="sr-only"><b>Password: </b></label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <br>
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      <button type="submit">Sign in</button>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>


<style scoped>

.text-center{  
        width: 600px; 
        height: 300px; 
        overflow: hidden;   
        margin: 50px 30px 0px 0px;  
        padding: 40px;  
        background: cornsilk;  
        border-radius: 0px ;  
        border-style: solid;
        border-color: cornsilk;
        
          
}  

.sr-only {
  font-size: 20px;
  padding: 5px;
  margin: 3px;
}

.form-control {
    font-size: 18px;
  padding: 2px;
  margin: 2px;
}
.checkmark {
  font-size: 15px;
  padding:12px;
  margin-bottom: 2rem;
}

.form-signin {
  width:600px;
  height: 300px;
}










</style>
