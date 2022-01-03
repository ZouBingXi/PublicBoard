<template>
  <div class="login-page">
    <div class="login-contain">
        <img class="img" :src="img_url">
        <img class="logo" :src="logo_url">
<!--      <p class="title_publicboard">PublicBoard</p>-->
      <div class="menu">
        <el-tabs v-model="activeName" @tab-click="handleClick" class="mytabs">
          <el-tab-pane label="登录" name="login" style="width: 100px"></el-tab-pane>
          <el-tab-pane label="注册" name="register"></el-tab-pane>
        </el-tabs>
        <login-box v-if="activeName=='login'"/>
        <register-box v-else @registerSuccess="activeName = 'login'"/>
      </div>
      <p class="icon-text">扫码打赏</p>
      <div class="icon" @mouseenter="view">
        <svg t="1639712321154" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="11741" width="40" height="40"><path d="M384 64H134.4c-51.2 0-89.6 41.6-89.6 89.6v227.2c0 51.2 41.6 89.6 89.6 89.6H384c51.2 0 89.6-41.6 89.6-89.6V153.6c0-48-38.4-89.6-89.6-89.6z m44.8 316.8c0 25.6-19.2 44.8-44.8 44.8H134.4c-25.6 0-44.8-19.2-44.8-44.8V153.6c0-25.6 19.2-44.8 44.8-44.8H384c25.6 0 44.8 19.2 44.8 44.8v227.2zM192 192h134.4v134.4H192V192z m185.6 352h-243.2c-48 0-86.4 38.4-86.4 89.6v220.8c0 48 38.4 89.6 86.4 89.6h243.2c48 0 86.4-38.4 86.4-89.6V633.6c3.2-51.2-38.4-89.6-86.4-89.6z m44.8 307.2c0 25.6-19.2 44.8-44.8 44.8h-243.2c-25.6 0-44.8-19.2-44.8-44.8v-220.8c0-25.6 19.2-44.8 44.8-44.8h243.2c25.6 0 44.8 19.2 44.8 44.8v220.8z m-230.4-182.4h131.2v131.2H192v-131.2z m441.6-198.4h249.6c51.2 0 89.6-41.6 89.6-89.6V153.6c0-51.2-41.6-89.6-89.6-89.6h-249.6c-51.2 0-89.6 41.6-89.6 89.6v227.2c0 51.2 41.6 89.6 89.6 89.6zM588.8 153.6c0-25.6 19.2-44.8 44.8-44.8h249.6c25.6 0 44.8 19.2 44.8 44.8v227.2c0 25.6-19.2 44.8-44.8 44.8h-249.6c-25.6 0-44.8-19.2-44.8-44.8V153.6z m112 38.4h134.4v134.4h-134.4V192z m-128 524.8h137.6c12.8 0 22.4-9.6 22.4-22.4V556.8c0-12.8-9.6-22.4-22.4-22.4h-137.6c-12.8 0-22.4 9.6-22.4 22.4v137.6c0 12.8 9.6 22.4 22.4 22.4z m313.6-153.6v38.4c0 12.8 12.8 25.6 25.6 25.6h38.4c12.8 0 25.6-12.8 25.6-25.6V563.2c0-12.8-12.8-25.6-25.6-25.6h-38.4c-12.8 0-25.6 9.6-25.6 25.6z m-304 380.8h48c12.8 0 22.4-9.6 22.4-22.4v-48c0-12.8-9.6-22.4-22.4-22.4h-48c-12.8 0-22.4 9.6-22.4 22.4V921.6c0 12.8 9.6 22.4 22.4 22.4z m361.6-240H844.8a28.68224 28.68224 0 0 0-28.8 28.8v44.8H768c-19.2 0-32 12.8-32 32V908.8c0 16 12.8 28.8 28.8 28.8l179.2 3.2c16 0 28.8-12.8 28.8-28.8v-179.2c0-16-12.8-28.8-28.8-28.8z" fill="#8B8B8B" p-id="11742"></path></svg>
      </div>
    </div>
  </div>
</template>

<script>
import LoginBox from "../components/LoginBox";
import RegisterBox from "../components/RegisterBox";

import login_img from "@/assets/loginImage.png"
import logo_img from "@/assets/logo.png"
export default {
  name: "TestLogin",
  components:{
    RegisterBox,
    LoginBox
  },
  data(){
    return{
      img_url:login_img,
      logo_url:logo_img,
      activeName:'login',
      test:'',
    }
  },
  methods:{
    view(){
      console.log('悬停');
    }
  }
}
</script>

<style scoped>
.login-page{
  position: relative;
  background: #F6F6F6;
  width: 100%;
  height: 100%;
  min-height: 1024px;
  min-width: 1300px;
}
.login-contain {
  position: relative;
  width: 1218px;
  height: 840px;
  margin: auto;
  top:calc(50% - 420px);

  background: #FFFFFF;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.img{
  position: absolute;
  width: 584px;
  height: 840px;
  left: 0px;
  top: 0px;
  border-radius: 30px 0px 0px 30px;
}
.logo{
  position: absolute;
  width: 383px;
  height: 102px;
  left: 678px;
  top: 53px;
}
.menu{
  position: absolute;
  width: 400px;
  left: 701px;
  top: 190px;
  font-style: normal;
  font-weight: bold;
}
</style>

<style>

</style>