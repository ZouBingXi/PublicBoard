<template>
  <div class="register-box">
    <el-form :model="RegisterForm" :rules="rules" ref="RegisterForm" label-position="top" label-width="auto" @keypress.enter="register">
      <el-form-item label="账号：" prop="account">
        <el-input v-model.number="RegisterForm.account" maxlength="10" placeholder="请输入账号"></el-input>
      </el-form-item>
      <el-form-item label="密码：" prop="password">
        <el-input v-model="RegisterForm.password" maxlength="20" placeholder="请输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item label="确认密码：" prop="confirmPassword">
        <el-input v-model="RegisterForm.confirmPassword" maxlength="20" placeholder="请再次输入密码" show-password></el-input>
      </el-form-item>
      <el-form-item label="邮箱：" prop="email">
        <el-input v-model="RegisterForm.email" maxlength="30" placeholder="请输入邮箱"></el-input>
      </el-form-item>
      <el-form-item label="验证码：" prop="code">
          <el-input v-model="RegisterForm.code" maxlength="6" placeholder="请输入验证码">
            <template #append>
              <el-button @click="getcode" :disabled="buttonDisabled">{{ buttonText }}</el-button>
            </template>
          </el-input>
      </el-form-item>
    </el-form>
    <el-button @click="register" type="primary">注册</el-button>
<!--    测试用按钮-->
<!--    <el-button @click="test">test</el-button>-->
  </div>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "LoginBox",
  data(){
    /*校验密码是否一致*/
    const checkpassword=(rule,value,callback)=>{
      if(this.RegisterForm.password===value)
        callback();
      else
        callback(new Error("两次密码不一致"));
    }
    /*校验邮箱格式函数，已弃用，已整合到 rules 对象中*/
    /*const checkemail=(rule,value,callback)=>{
      let reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if(!value)
        callback(new Error("请输入邮箱"));
      else if(reg.test(value))
        callback();
      else
        callback(new Error("请检查邮箱格式"))
    }*/
    const showError=(rule,value,callback)=>{
      if(this.errorcode==1)
        callback('验证码错误')
      else
        callback()
    }
    return{
      buttonDisabled:false,
      timeToSend:null,
      RegisterForm:{
        account:null,
        password:null,
        confirmPassword:null,
        email:null,
        code:null,
      },
      rules:{
        account: [
          { required: true, message: '请输入账号', trigger: 'blur'},
          { type: 'number', min: 1000000000, max: 9999999999, message: '账号为 10 位数字',trigger: 'blur'}
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur'},
          {
            pattern:/(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}/,
            message: '密码中必须包含字母、数字，至少6个字符，最多20个字符。',
            trigger: 'blur'
          }
        ],
        confirmPassword: [
          { required: true,validator:checkpassword, trigger: 'blur'},
        ],
        email: [
          { required: true,message:'请输入邮箱', trigger: 'blur'},
          {
            required: true,
            pattern:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message:'请检查邮箱格式',
            trigger: 'blur'
          },
        ],
        code: [
          { required: true, message: '请输入验证码', trigger: 'blur'},
          { min: 6, max: 6, message: '验证码为6位',trigger: 'blur'},
          {validator:showError,trigger: 'blur'}
        ]
      }
    }
  },

  methods: {
    /*注册函数，调用后端注册接口*/
    register() {
      this.$refs['RegisterForm'].validate()
          .then(() => {
            var body={
              userid:this.RegisterForm.account,
              password:this.RegisterForm.password,
              email:this.RegisterForm.email,
              code:this.RegisterForm.code,
            }
            this.$axios.post('/user/add',body).then(({data})=>{
              if(data.code==200){
                console.log("注册成功",data);
                this.$emit('registerSuccess');
                ElMessage.success('注册成功')
              }
              else{
                console.log("注册失败",data);
                ElMessage.error(data.msg)
              }

            })

          })
    },
    /*获取验证码，成功发送请求后，注意要调用setTimer，防止用户频繁发送请求*/
    getcode() {
      this.$refs['RegisterForm'].validateField('email',(err)=>{
        if(!err){
          this.$axios.get('/user/sendcode?flag=noexist&to='+this.RegisterForm.email)
          .then(({data})=>{
            if(data.code==200)
              this.setTimer();
            else
              ElMessage.error(data.msg)

          })
        }
      })
    },

    /*发送验证码后，设置计时器30s*/
    setTimer() {
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

    /*测试用函数*/
    test(){
      this.errorcode=1;
      this.$refs['RegisterForm'].validateField('code')
    }
  },
  computed:{
    /*返回验证码按钮显示文本信息*/
    buttonText(){
      if(this.buttonDisabled)
        return this.timeToSend+'秒后重新发送';
      else
        return '点击发送验证码';
    }
  }
}


</script>


<style>


</style>