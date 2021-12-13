<template>
  <!-- 页面背景 -->
  <div id="background">

    <!-- 基础页面 -->
    <div id="base">

      <!-- 登录背景 -->
      <div id="login-background">
        <el-image id="login-background-img" :src=images_list.image1 ></el-image>
      </div>

      <!-- 登录组合 -->
      <div id="login-combo">

        <!-- 登录文本 -->
        <div id="login-text">
          <p id="login-text1">登录</p>
        </div>

        <!-- 输入表单 -->
        <div id="input-form">
          <el-form :model="loginForm" :rules="rules" ref="ruleForm" label-width="50px" class="demo-ruleForm" hide-required-asterisk>

            <el-form-item id="input-form-item1" label="学号" prop="userid">
              <el-input v-model.number="loginForm.userid" placeholder="请输入学号"></el-input>
            </el-form-item>

            <el-form-item id="input-form-item2" label="密码" prop="password">
              <el-input v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>
<!--            <el-form-item>-->
<!--              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>-->
<!--              <el-button @click="resetForm('ruleForm')">重置</el-button>-->
<!--            </el-form-item>-->
        </el-form>
        </div>

        <!-- 忘记密码？文字链接 -->
        <div id="forget-password-text-link">
          <el-link id="forget-password-text-link1" :underline="false" type="info">忘记密码？</el-link>
        </div>

        <!-- 登录按钮 -->
        <div id="input-button">
          <el-button id="input-button1" type="info" @click="submitForm">登录</el-button>
        </div>

        <!-- 立即注册文字链接 -->
        <div id="register-text-link">
          <el-link @click="gotoRegister('ruleForm')" id="register-text-link1" :underline="false" type="info">立即注册</el-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import  login_background from '../common/images/Login/login_background.svg'
import {LOGIN} from "@/store/actions.type";

export default {
  data() {
    return {
      loginForm: {
        userid: null,
        password: ''
      },
      rules: {
        userid: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { type: 'number', min: 1000000000, max: 9999999999, message: '学号为 10 位数字', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
        ]
      },
      images_list: {
        image1: login_background
      }
    };
  },
  methods: {
    submitForm (){
      this.$refs['ruleForm'].validate()
      .then(()=>{
        this.$store.dispatch(LOGIN,this.loginForm)
            .then(()=>this.$router.push({path: '/'}))
        .catch(({msg})=>this.$message({
          showClose: true,
          message: msg,
          type: 'error'
        }))
      })
    },
    gotoRegister(formName) {
      this.$refs[formName].resetFields();
      this.$router.push({path: '/register'});
    }
    // resetForm(formName) {
    //   this.$refs[formName].resetFields();
    // }
  }
}
</script>

<style scoped>
#base {
  position:absolute;
  left:50%;
  width:1024px;
  z-index:0;
  transform:translate(-50%);
}

#login-background-img {
  border-width:0px;
  position:absolute;
  left:0px;
  top:0px;
  width:910px;
  height:530px;
}

#login-background {
  border-width:0px;
  position:absolute;
  left:62px;
  top:120px;
  width:910px;
  height:530px;
  display:flex;
}

#login-combo {
  border-width:0px;
  position:absolute;
  left:588px;
  top:182px;
  width:300px;
  height:432px;
}

#login-text {
  border-width:0px;
  position:absolute;
  left:98px;
  top:0px;
  width:103px;
  height:52px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:40px;
  text-align:center;
}
#login-text1 {
  position:absolute;
  align-self:flex-start;
  margin:0px 0px 0px 0px;
  padding:0px 0px 0px 0px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
}

#input-form {
  border-width:0px;
  position:absolute;
  left:0px;
  top:112px;
  width:300px;
  height:45px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:15px;
}

.el-input /deep/ .el-input__inner:focus{
  border-color:#17181A;
}

#input-form-item1 {
  margin-bottom:30px;
}

#input-form-item2 {
  margin-bottom:30px;
}

#forget-password-text-link {
  border-width:0px;
  position:absolute;
  left:200px;
  top:230px;
  width:83px;
  height:21px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
}

#forget-password-text-link1 {
  position:absolute;
  align-self:center;
  padding:2px 2px 2px 2px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
  font-size:12px;
  text-decoration:underline;
}

#input-button {
  border-width:0px;
  position:absolute;
  left:50px;
  top:280px;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:15px;
}

#input-button1 {
  width:221px;
}

#register-text-link {
  border-width:0px;
  position:absolute;
  left:118px;
  top:390px;
  width:83px;
  height:21px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
}

#register-text-link1 {
  position:absolute;
  align-self:center;
  padding:2px 2px 2px 2px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
  text-decoration:underline;
}

</style>