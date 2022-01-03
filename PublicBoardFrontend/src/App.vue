<template>
  <div id="app">
    <Header v-if="isShow"></Header>
    <router-view/>
  </div>
</template>

<script>
import Header from "./components/Header";

export default {
  name: 'App',
  components: {
    Header

  },
  computed:{
    isShow(){
      var isLoginPage=this.$route.path==='/login';
      var isForgetPassword=this.$route.path==='/forgetpassword';
      return !(isLoginPage ||isForgetPassword)
    }
  }
}
</script>

<style>
#app {
  margin:0px;
  padding: 0px;
  min-width: 1024px;
  height: 100%;
}
</style>
