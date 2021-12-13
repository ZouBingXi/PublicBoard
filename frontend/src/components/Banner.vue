<template>
  <div class="banner">
    <h1 data-spotlight="PublicBoard!" class="logo-font">PublicBoard</h1>
  </div>
</template>

<script>
export default {
  name: "Banner"
}
</script>

<style scoped>
.banner {
  background-color: #313131;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 10px -20px 10px -20px;
  padding: 30px;
  height: 120px;
  box-shadow: 0px 2px 10px -2px rgba(0,0,0,0.75) inset, 0px -2px 10px -2px rgba(0,0,0,0.75) inset;
}

.logo-font {
  color: #414141;
  font-family: Helvetica;
  margin: 0;
  padding: 0;
  font-size: 4rem;
  letter-spacing: -0.1rem;
  position: relative;
}

.logo-font::after {
  content: attr(data-spotlight);
  color: transparent;
  position: absolute;
  top: 0;
  left: 0;
  -webkit-clip-path: ellipse(100px 100px at 0% 50%);
  clip-path: ellipse(50px 50px at 0% 50%);
  animation: spotlight 5s infinite;
  background-image: linear-gradient(90deg, #29a9e4, #88bbc2, #b6cd9d, #d9e175);
  background-position: center center;
  -webkit-background-clip: text;
  /*background-clip: text;*/
}

@keyframes spotlight {
  0% {
    -webkit-clip-path: ellipse(100px 100px at 0% 50%);
    clip-path: ellipse(50px 50px at 0% 50%);
  }
  50% {
    -webkit-clip-path: ellipse(100px 100px at 100% 50%);
    clip-path: ellipse(50px 50px at 100% 50%);
  }
  100% {
    -webkit-clip-path: ellipse(100px 100px at 0% 50%);
    clip-path: ellipse(50px 50px at 0% 50%);
  }
}
</style>