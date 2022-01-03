<template>
  <div class="page">
    <div class="contain">
      <img class="forget-img" :src="forgetPasswordImageURL"/>
      <FindPasswordBox v-show="flag" @goToResetPassword="email=$event;flag=!flag" />
      <ResetPasswordBox  v-show="!flag" :email="email" @goToFindPasswordBox="flag=!flag"/>
    </div>
  </div>
</template>

<script>
import forgetPasswordImageURL from "../assets/forgetPasswordPageImage.png"
import FindPasswordBox from "../components/FindPasswordBox";
import ResetPasswordBox from "../components/ResetPasswordBox";

export default {
  name: "ForgetPassword",
  components:{
    FindPasswordBox,
    ResetPasswordBox,
  },
  data(){
    return{
      forgetPasswordImageURL:forgetPasswordImageURL,
      flag:true,
      email:'',
    }
  }
}
</script>

<style scoped>
.page {
  position: relative;
  background: #F6F6F6;
  width: 100%;
  height: 100%;
  min-height: 1024px;
  min-width: 1300px;
}
.contain{
  position: relative;
  width: 1218px;
  height: 840px;
  margin: auto;
  top:calc(50% - 420px);

  background: #FFFFFF;
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.forget-img{
  float: right;
  width: 584px;
  height: 840px;
  border-radius: 0px 30px 30px 0px;
}
</style>