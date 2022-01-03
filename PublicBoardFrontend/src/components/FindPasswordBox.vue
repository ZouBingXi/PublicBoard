<template>
  <div class="findPasswordBox">
    <h1 class="findPasswordText">找回密码</h1>
    <p style="font-weight: bold;font-size: 14px;line-height: 19px;color: #8B8B8B">验证码将会发送到你的注册邮箱</p>
    <el-form :model="Form" :rules="rules" ref="Form" label-position="top" @keypress.enter="goToResetPassword" @submit.prevent>
      <el-form-item label="邮箱：" prop="email">
        <el-input v-model="Form.email" placeholder="请输入邮箱" size="large"></el-input>
      </el-form-item>
    </el-form>
    <router-link :to="{path:'/login'}" class="link">返回登陆?</router-link>
    <el-button @click="goToResetPassword" type="primary">下一步</el-button>
  </div>
</template>

<script>
export default {
  name: "FindPasswordBox",
  data(){
    return{
      Form:{email:''},
      rules:{
        email: [
          { required: true,message:'请输入邮箱', trigger: 'blur'},
          {
            required: true,
            pattern:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
            message:'请检查邮箱格式',
            trigger: 'blur'
          },
        ]
      }
    }
  },
  methods:{
    goToResetPassword(){
      this.$refs['Form'].validate().then(()=>{
        this.$emit('goToResetPassword',this.Form.email);
      })
    }
  },
}
</script>

<style lang="scss">

.findPasswordBox {
  font-weight:bold;
  position: absolute;
  width: 400px;
  height: 311px;
  left: 118px;
  top: 269px;
  margin:0px;

  .findPasswordText{
    font-size: 54px;
    line-height: 74px;
    margin: 0px;
  }

  .el-form-item {
    margin-top: 40px;
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