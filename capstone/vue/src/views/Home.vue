<template>
  <div class="body-container">
    <div class="hero-image">
      <div class="hero-text">
        <h1>We're all in this together! Recycle for the planet!</h1>
        <button>
          <router-link id="register" v-bind:to="{ name: 'register' }">
          </router-link
          >Sign up today!
        </button>
      </div>
    </div>
    <section class="about-us">
      <div id="info">
        <h2>Get to know Vitrum</h2>
        <p>
          We're a grassroots program offering curbside glass recycling in the
          Pittsburgh metro region. Despite its status as an infinitely
          recyclable material, 75% of glass containers used by consumers will be
          disposed of in landfills where it will never decompose. Enroll with
          Vitrum today and our fleet of glass wranglers will deliver custom bins
          to your home or business.
        </p>
        <div class="illustration"><img src="../img/happyrecycle.jpg" /></div>
      </div>
    </section>
    <section class="counter-container">
      <div id="counter">We've saved XX lbs of glass from landfills!</div>
    </section>

    <section class="mid-container">
      <div>
        When those bins are full, simply schedule a pickup date on our handy app
        and drop those wine bottles and jam jars at the curb; they will be
        crushed to a fine sand which we sell or donate to our commercial
        partners for a wide variety of sustainable applications: erosion
        remediation, creating road services, and even to make swanky and unique
        kitchen countertops. Keep track of your recycling stats on your
        dashboard and earn points with every pickup to redeem for cool prizes.
        Sign up today!
      </div>
    </section>
    <section class="last-container">
      <div class="row">
        <div class="column">
          <div class="card">
            <img
              src="../img/antony.jpg"
              alt="Antony headshot"
              style="width: 100%"
            />
            <div class="contact-container">
              <h2>Antony Trivet</h2>
              <p class="title">CEO &amp; Founder</p>
              <p>Some text that describes me lorem ipsum ipsum lorem.</p>
              <p>antony@vitrum.com</p>
              <p><button class="button">Contact me</button></p>
            </div>
          </div>
        </div>

        <div class="column">
          <div class="card">
            <img
              src="../img/maria.jpg"
              alt="Maria headshot"
              style="width: 100%"
            />
            <div class="contact-container">
              <h2>Maria Orlova</h2>
              <p class="title">Media Director</p>
              <p>Some text that describes me lorem ipsum ipsum lorem.</p>
              <p>maria@vitrum.com</p>
              <p><button class="button">Contact me</button></p>
            </div>
          </div>
        </div>

        <div class="column">
          <div class="card">
            <img
              src="../img/alex.jpg"
              alt="Alex headshot"
              style="width: 100%"
            />
            <div class="contact-container">
              <h2>Alex Starnes</h2>
              <p class="title">Art Director</p>
              <p>Some text that describes me lorem ipsum ipsum lorem.</p>
              <p>alex@vitrum.com</p>
              <p><button class="button">Contact</button></p>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "home",
  //name: "account"
};
</script>


<style scoped>
html {
  box-sizing: border-box;
}

*,
*:before,
*:after {
  box-sizing: inherit;
}

.body-container {
  border: 1px solid red;
  width: 100%;
}

.hero-image {
  border: 1px solid yellow;
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.4)),
    url("../img/glass-bottles-honeycomb.jpg");
  height: 600px;
  width: 100%;
  background-position: center;
  background-repeat: no-repeat;
  background-size: cover;
  position: relative;
}

.hero-text {
  text-align: center;
  position: absolute;
  letter-spacing: 0.3em;
  font-size: 1.2em;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
}

.hero-image button {
  display: inline-block;
  padding: 10px 25px;
  color: white;
  font-size: 1em;
  text-align: center;
  cursor: pointer;
  border: 2px solid white;
}

h2 {
  text-align: center;
}

.about-us {
  background-color: white;
  margin-top: 0;
  border: 1px solid rgb(236, 1, 197);
  padding: 30px;
  padding-left: 50px;
  padding-right: 50px;
  height: auto;
  font-size: 1.5em;
  text-align: justify;
  /* overflow: auto; */
}

.illustration img {
  border: 1px solid red;
  max-width: 100%;
}

.counter-container {
  display: flex;
  justify-content: center;
  background-color: #7ac62f;
  margin-top: 5em;
  height: 200px;
  width: 100%;
}

#counter {
  font-size: 3em;
  border-radius: 15px;
  border: 1px solid yellow;
}

.mid-container {
  background-color: white;
  margin-top: 2em;
  box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5);
  border: 1px solid rgb(236, 1, 197);
  padding: 30px;
  height: 60px;
  align-items: center;
  justify-content: center;
  text-align: justify;
}

.last-container {
  background-color: white;
  box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5);
  margin-top: 2em;
  border: 1px solid blue;
  padding: 30px;
  height: 700px;
  /* align-items: center; */
  /* justify-content: center; */
  /* text-align: justify; */
}

.column {
  float: left;
  width: 33.3%;
  margin-bottom: 16px;
  padding: 0 8px;
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

.desc {
  border: 1px solid red;
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

/*check media again */
@media screen and (max-width: 1024px) {
  .body-container div {
    flex-wrap: wrap;
  }
}

/* @media screen and (max-width: 650px) {
  .column {
    width: 100%;
    display: block;
  }
}

.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}

.container {
  padding: 0 16px;
}

.container::after, .row::after {
  content: "";
  clear: both;
  display: table;
}

.title {
  color: grey;
}

.button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.button:hover {
  background-color: #555;
} */
</style>