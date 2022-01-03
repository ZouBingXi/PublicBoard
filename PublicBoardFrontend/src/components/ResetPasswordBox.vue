<template>
  <div class="resetPasswordBox">
    <h1 class="ResetPasswordText">重置密码</h1>
    <el-form :model="ResetPasswordForm" :rules="rules" ref="ResetPasswordForm" label-position="top" @keypress.enter="resetPassword">
      <el-form-item label="邮箱：" prop="email">
        <el-input :placeholder="email" size="large" disabled>
          <template #append>
            <el-button @click="this.$emit('goToFindPasswordBox')">修改</el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item label="验证码：" prop="code">
        <el-input v-model="ResetPasswordForm.code" maxlength="6" placeholder="请输入验证码" size="large">
          <template #append>
            <el-button @click="getcode" :disabled="buttonDisabled">{{ buttonText }}</el-button>
          </template>
        </el-input>
      </el-form-item>
      <el-form-item label="新密码：" prop="newpassword">
        <el-input v-model="ResetPasswordForm.newpassword" maxlength="20" placeholder="请输入新密码" size="large" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码：" prop="confirmPassword">
        <el-input v-model="ResetPasswordForm.confirmPassword" maxlength="20" placeholder="请重新输入密码" size="large" show-password></el-input>
      </el-form-item>
    </el-form>
    <router-link :to="{path:'/login'}" class="link">返回登陆?</router-link>
    <el-button @click="resetPassword" type="primary">重置密码</el-button>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "ResetPasswordBox",
  props:['email'],
  data(){
    const checkpassword=(rule,value,callback)=>{
      if(this.ResetPasswordForm.newpassword===value)
        callback();
      else
        callback(new Error("两次密码不一致"));
    }
    const showError=(rule,value,callback)=>{
      callback()
    }
    return{
      buttonDisabled:false,
      timeToSend:0,
      ResetPasswordForm:{
        code:'',
        newpassword:'',
        confirmPassword:''
      },
      rules:{
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur'},
          { min: 6, max: 6, message: '验证码为6位',trigger: 'blur'},
          {validator:showError,trigger: 'blur'}
        ],
        newpassword: [
          { required: true, message: '请输入密码', trigger: 'blur'},
          {
            pattern:/(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}/,
            message: '密码中必须包含字母、数字，至少6个字符，最多20个字符。',
            trigger: 'blur'
          }
        ],
        confirmPassword: [
          { required: true,validator:checkpassword, trigger: 'blur'},
        ]
      }
    }
  },
  methods:{
    resetPassword(){
      this.$refs['ResetPasswordForm'].validate()
          .then(() => {
            var body={
              newPassword:this.ResetPasswordForm.newpassword,
              email:this.email,
              code:this.ResetPasswordForm.code,
            }
            this.$axios.post('/user/forget',body).then(({data})=>{
              if(data.code==200){
                console.log("修改密码成功",data);
                ElMessage.success('修改密码成功');
                this.$router.push({path:'/login'});
              }
              else{
                console.log("注册失败",data);
                ElMessage.error(data.msg)
              }

            })

          })
    },

    getcode() {
      this.$axios.get('/user/sendcode?flag=exist&to='+this.email)
          .then(({data})=>{
            if(data.code==200){
              this.setTimer();
              console.log('发送验证码:'+this.email)
            }
            else{
              console.log("注册失败",data);
              ElMessage.error(data.msg)
            }
          })
    },

    /*发送验证码后，设置计时器30s*/
    setTimer(){
      this.buttonDisabled = true;
      this.timeToSend = 30;
      let timer = setInterval(() => {
        if (this.timeToSend == 1) {
          clearInterval(timer);
          this.buttonDisabled = false;
        } else {
          this.timeToSend--;
        }
      }, 1000)
    },
  },
  computed:{
    /*返回验证码按钮显示文本信息*/
    buttonText(){
      if(this.buttonDisabled)
        return this.timeToSend+'秒后重新发送';
      else
        return '点击发送验证码';
    }
  },
/*  created() {
    this.getcode();
  }*/
}
</script>

<style lang="scss">

.resetPasswordBox {
  font-weight:bold;
  position: absolute;
  width: 400px;
  left: 118px;
  top: 121px;
  margin:0px;

  .ResetPasswordText{
    font-size: 54px;
    line-height: 74px;
    margin: 0px 0px 50px;
  }

  .el-form-item {
    margin-top: 23px;
    margin-bottom: 0px;

    .el-form-item__label {
      font-size: 18px;
      line-height: 18px;
    }
  }

  .el-button--primary{
    margin-top: 10px;
    line-height: 30px;
    width:100%;
    font-size: 24px;
    background: #333333;
    border: #333333;
    border-radius: 10px;
  }
  .link{
    font-size: 17px;
    color: #666666;
    float: right;
    text-decoration: none;
    margin-top: 11px;
    .router-link-exact-active{
      color: black;
    }
  }
}
</style>