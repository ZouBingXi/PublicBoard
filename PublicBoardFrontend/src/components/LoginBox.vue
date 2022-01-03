<template>
  <div class="login-box">
    <el-form :model="loginForm" :rules="rules" ref="loginForm" label-position="top" @keyup.enter="login">
      <el-form-item label="账号：" prop="account">
        <el-input v-model.number="loginForm.account" placeholder="请输入账号" maxlength="10" size="large"></el-input>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
          <el-input v-model="loginForm.password" maxlength="30" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
    </el-form>
    <router-link :to="{path:'/forgetpassword'}" class="forget-router-link">忘记密码？</router-link>
    <div class="login-button">
      <el-button @click="login" type="primary">登录</el-button>
    </div>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import {LOGIN} from "../store/actions.type";

export default {
  name: "LoginBox",
  data(){
    return{
      loginForm:{
        account:null,
        password:null
      },
      rules:{
        account: [
          { required: true, message: '请输入账号', trigger: 'blur'},
          { type: 'number', min: 1000000000, max: 9999999999, message: '账号为 10 位数字',trigger: 'blur'},
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur'},
          /*{ min: 6, max: 20, message: '密码长度在 6 到 20 个字符',trigger: 'blur'}*/
        ]
      }
    }
  },
  methods:{
    login(){
      console.log("登录");//输出登录信息

      this.FormIsCorrect.then(()=>{
        this.$store.dispatch(LOGIN,{userid:this.loginForm.account,password:this.loginForm.password})
        .then(()=>{
          this.$router.push({path:'/'})
        })
        .catch((err)=>{
          ElMessage.error(err.msg);
        })
      })
    }
  },
  computed:{
    FormIsCorrect(){
      return this.$refs['loginForm'].validate();
    }
  }
}

</script>

<style>

</style>