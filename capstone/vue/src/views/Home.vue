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
    <section class="mid-container">
      <div>
        When those bins are full, simply schedule a pickup date on our handy app
        and drop those wine bottles and jam jars at the curb; they will be
        crushed to a fine sand which we sell or donate to our commercial
        partners for a wide variety of sustainable applications: erosion
        remediation, creating road services, and even to make swanky and unique
        kitchen countertops. Keep track of your recycling stats on your
        dashboard and earn points with every pickup to redeem for cool prizes.
      </div>
    </section>
    <section class="counter-container">
      <div id="counter">We've saved XX lbs of glass from landfills!</div>
    </section>
    <section class="last-container">
      <div class="meet-team">
        <h2 class="mt-header">Meet Our Team</h2>
        <div class="card-wrapper">
          <div class="card">
            <img
              src="../img/tyler.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Tyler Harrington</h3>
            <p class="job-title">Founder &amp; CEO</p>
            <p class="about">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Adipisci, alias aliquam amet autem blanditiis commodi consectetur
              cum dolorem ea eos error.
            </p>
          </div>

          <div class="card">
            <img
              src="../img/maria.jpg"
              alt="Profile image"
              class="profile-img"
            />
            <h3>Maria Spiels</h3>
            <p class="job-title">Marketing Director</p>
            <p class="about">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Adipisci, alias aliquam amet autem blanditiis commodi consectetur
              cum dolorem ea eos error.
            </p>
          </div>

          <div class="card">
            <img src="../img/jon.jpg" alt="Profile image" class="profile-img" />
            <h3>Jon Nakamura</h3>
            <p class="job-title">Logistics Director</p>
            <p class="about">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit.
              Adipisci, alias aliquam amet autem blanditiis commodi consectetur
              cum dolorem ea eos error.
            </p>
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
* {
  box-sizing: border-box;
  margin: 0;
  border: 0;
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

.mid-container {
  background-color: white;
  margin-top: 2em;
  border: 1px solid rgb(236, 1, 197);
  padding: 30px;
  height: auto;
  font-size: 1.5em;
  text-align: justify;
  align-items: center;
  justify-content: center;
}

.counter-container {
  display: flex;
  justify-content: center;
  background-color: #9bdb66;
  margin-top: 5em;
  height: 200px;
  width: 100%;
}

#counter {
  font-size: 3em;
  border-radius: 15px;
  border: 1px solid yellow;
}

.last-container {
  background-color: white;
  padding: 10em 0;
  border: 1px solid blue;
}

.mt-header {
  text-transform: uppercase;
  font-size: 3.5em;
  letter-spacing: 3px;
  margin-right: -3px;
  margin-bottom: 1.5em;
  text-align: center;
  position: relative;
}

.mt-header::after {
  content: "";
  width: 10em;
  height: 0.2em;
  background-color: #ffe46c;
  position: absolute;
  bottom: -.5em;
  left: 50%;
  transform: translateX(-50%);
  border-radius: 2em;
}

.last-container .card-wrapper {
  display: flex;
  align-items: center;
  align-content: center;
  flex-direction: column;
}

.last-container .card {
  width: 32em;
  background-color: #ebeef8;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  margin: 2em 0;
  box-shadow: 0.5em 0.5em 3em rgba(0, 0, 0, 0.2);
}

.profile-img {
  width: 100%;
  height: 26em;
  object-fit: cover;
}

.last-container .card h3 {
  font-size: 2em;
  margin: 1.5em 0;
}

.last-container .job-title {
  color: #0c2d53;
  font-size: 1.5em;
  font-weight: 400;
}

.last-container .about {
  font-size: 1.3em;
  margin: 1.5em 0;
  font-style: italic;
  text-align: center;
}

.last-container .card {
  border: 1px solid red;
}

@media screen and (min-width: 700px) {
  .last-container .card-wrapper {
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: center;
    align-items: center;
  }

  .last-container .card {
    margin: 2em;
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