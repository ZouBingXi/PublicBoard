<template>
  <div class="changePassword">
    <div class="transparentBackground">
    </div>
    <div class="Box">
      <h1 class="TitleText">修改密码</h1>
      <el-form :model="changePasswordForm" :rules="rules" ref="changePasswordForm" label-position="top" @keypress.enter="ChangePassword">
        <el-form-item label="原密码：" prop="oldPassword">
          <el-input v-model="changePasswordForm.oldPassword" maxlength="20" placeholder="请输入原密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码：" prop="newPassword">
          <el-input v-model="changePasswordForm.newPassword" maxlength="20" placeholder="请输入新密码" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码：" prop="confirmPassword">
          <el-input v-model="changePasswordForm.confirmPassword" maxlength="20" placeholder="请再次输入密码" show-password></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="ChangePassword" type="primary">确定</el-button>
      <el-button @click="this.$emit('exit')" type="primary">取消</el-button>
    </div>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";

export default {
  name: "changePassword",
  data() {
    const checkpassword=(rule,value,callback)=>{
      if(this.changePasswordForm.newPassword===value)
        callback();
      else
        callback(new Error("两次密码不一致"));
    }
    return {
      changePasswordForm: {
        oldPassword: '',
        newPassword: '',
        confirmPassword: ''
      },
      rules: {
        oldPassword: [
          {required: true, message: '请输入原密码', trigger: 'blur'}
        ],
        newPassword: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          {
            pattern: /(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}/,
            message: '密码中必须包含字母、数字，至少6个字符，最多20个字符。',
            trigger: 'blur'
          }
        ],
        confirmPassword: [
          {required: true, validator: checkpassword, trigger: 'blur'},
        ],
      }
    }
  },
  methods:{
    ChangePassword(){
      this.$refs['changePasswordForm'].validate().then(()=>{
        var body={
          oldPassword:this.changePasswordForm.oldPassword,
          newPassword:this.changePasswordForm.newPassword
        }
        this.$axios.post('/user/changepassword',body).then(({data})=>{
          if(data.code==200){
            console.log("修改密码成功",data);
            ElMessage.success('修改密码成功');
            this.$emit('exit');
          }
          else{
            console.log("修改失败",data);
            ElMessage.error(data.msg)
          }
        })
      })
    }
  }
}
</script>

<style lang="scss">
.changePassword{
  .Box {
    position: fixed;
    top:calc(50% - 270px);
    left:calc(50% - 230px);

    width: 400px;
    padding: 60px;
    border-radius: 20px;

    background: white;
    font-weight:bold;

    .TitleText{
      font-size: 35px;
      line-height: 35px;
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
      margin-top: 30px;
      line-height: 30px;
      width:195px;
      font-size: 24px;
      background: #333333;
      border: #333333;
      border-radius: 10px;
    }
  }
}

</style>