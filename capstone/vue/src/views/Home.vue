<template>
<div class="container">
  <div class="home">
   <img src="" alt= "Glass Recycling Logo" class="logo">
  </div>
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


</div>
</template>

<script>
export default {
  name: "home"
  //name: "account"
};
</script>


<style scope>

*{
  background-color:#EAFFF1 ;
}

.info {
  background-color: cornsilk;
}

.left-container {
  display: flex;
  flex-direction: column;
  margin: 60px;
  padding: 30px 50px 30px 400px;
  font-family: cursive;
  font-size: 20px;
 
}
.footer-container {
  display: flex;
}

.right-container {
  display: flex;
  margin: 100px;
  flex-direction: column;
  font-size: 20px;
}

.home-footer:hover, .contact:hover, .drop-off:hover, .calendar:hover{
color: green;
text-decoration: underline;
}

.home-footer, .contact, .drop-off, .calendar, .app-home{
  padding: 15px;
  text-transform: uppercase;
}






.container {
  background-color: #EAFFF1;
  padding: 20px;
 
}

.logo {
  width: 140px;
}

ul {
  margin: 0;
  padding: 0;
  list-style-type: none;
  display: flex;

}

li {
  font-family:Georgia, 'Times New Roman', Times, serif;
  font-size: 20px;
  text-transform: uppercase;
  color: black;
  padding: 0 50px 0 0;
  margin-left: 40px;

}

.recycling-container {
  margin: 200px 60px 300px 60px;
  height: 500px;
  background-color:	cornsilk;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 12px;
  
}

a,
a:visited {
  color: black;
  text-decoration: none;
}

a:hover {
  color: green;
  text-decoration: underline;
}

nav{
  display: flex;
  flex: auto;
  justify-content: right;
  align-content:flex-end;
  flex-direction: row;
}


</style>