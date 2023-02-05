
<!-- This is splash page. user can see the splash page but can not access the details if not logged in --> 

<template>
  <div id="container">
    <!--container for information and the sign up, registration" -->
    <div class="body-container">
      <section class="about-us">
        <div id="info">
          <h2>Get to know Vitrum</h2>
          We're a grassroots program offering curbside glass recycling in the
          Pittsburgh metro region. Despite its status as an infinitely
          recyclable material, 75% of glass containers used by consumers will be
          disposed of in landfills where it will never decompose.
          <!-- <img src="../img/bottles.png" alt="bottles" class="info-image" /> -->
        </div>
      </section>
      <section class="images-container">
        <div class="flip-card">
          <div class="flip-card-inner">
            <div class="flip-card-front">
              <img
                id="one"
                src="../img/bottles-in-crate.jpg"
                alt="Green bottles in crate"
              />
            </div>
            <div class="flip-card-back">
              <h1>Did you know?</h1>
              <p>
                Recycling one glass bottle saves enough electricity to light a
                100-watt bulb for 4 hours!
              </p>
            </div>
          </div>
        </div>
        <div id="counter">
          We've saved <br />XX<br />lbs of glass from the landfill!
        </div>
      </section>

      <section class="mid-container">
        <div>
          Enroll with Vitrum today and our fleet of glass wranglers will deliver
          custom bins to your home or business.
        </div>
      </section>
      <section class="last-container">
        <div>
          When those bins are full, simply schedule a pickup date on our handy
          app and drop those wine bottles and jam jars at the curb; they will be
          crushed to a fine sand which we sell or donate to our commercial
          partners for a wide variety of sustainable applications: erosion
          remediation, creating road services, and even to make swanky and
          unique kitchen countertops. Keep track of your recycling stats on your
          dashboard and earn points with every pickup to redeem for cool prizes.
          Sign up today!
        </div>
      </section>
    </div>
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
  font-family: "Raleway", sans-serif;
}
#container {
  border: 1px solid red;
}

h2 {
  text-align: center;
}

.about-us {
  background-color: white;
  margin-top: 5em;
  box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5);
  border: 1px solid rgb(236, 1, 197);
  padding: 30px;
  height: 3.7em;
  align-items: center;
  justify-content: center;
  text-align: justify;
  overflow: auto;
}

.images-container {
  display: flex;
  border: 1px solid orange;
  margin-top: 5em;
  padding: 30px;
}

.flip-card {
  background-color: transparent;
  width: 600px;
  height: 220px;
  perspective: 1000px;
}

/* need to figure out how to get picture full width...or maybe find
another picture */
.flip-card-inner {
  position: relative;
  width: 100%;
  height: 100%;
  transition: transform 0.6s;
  transform-style: preserve-3d;
  box-shadow: 0 4px 8px 9 rbga(0, 0, 0, 0.2);
}

.flip-card:hover .flip-card-inner {
  transform: rotateY(180deg);
}

.flip-card-front,
.flip-card-back {
  position: absolute;
  width: 100%;
  height: 100%;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
}

.flip-card-front {
  background-color: transparent;
}

.flip-card-back {
  background-color: #2980b9;
  color: white;
  transform: rotateY(180deg);
}

#one {
  max-width: 50%;
  border-radius: 15px;
}

#counter {
  background-color: white;
  border-radius: 15px;
}

.mid-container {
  background-color: white;
  margin-top: 2em;
  box-shadow: 1px 1px 7px rgba(0, 0, 0, 0.5);
  border: 1px solid rgb(236, 1, 197);
  padding: 30px;
  height: 60px;
  width: 85%;
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
  height: 200px;
  width: 85%;
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

#container {
  /* display: flex;
  flex-direction: column; */
  /* align-items: center; */
  text-align: center;
  margin-left: auto;
  margin-right: auto;
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