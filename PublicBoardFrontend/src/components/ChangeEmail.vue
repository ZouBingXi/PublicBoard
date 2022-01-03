<template>
  <div class="changeEmailPage">
    <div class="transparentBackground">
    </div>
    <div class="Box">
      <h1 class="TitleText">修改邮箱</h1>
      <el-form :model="ChangeEmailForm" :rules="rules" ref="ChangeEmailForm" label-position="top">
        <el-form-item label="新邮箱：" prop="email">
          <el-input v-model="ChangeEmailForm.email" maxlength="30" placeholder="请输入新邮箱"></el-input>
        </el-form-item>
        <el-form-item label="验证码：" prop="code">
          <el-input v-model="ChangeEmailForm.code" maxlength="6" placeholder="请输入验证码">
            <template #append>
              <el-button @click="getcode" :disabled="buttonDisabled">{{ buttonText }}</el-button>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
      <el-button @click="ChangeEmail" type="primary">确定</el-button>
      <el-button @click="this.$emit('exit')" type="primary">取消</el-button>
    </div>
  </div>
</template>

<script>
import {ElMessage} from "element-plus";
import {GET_USER_INFO} from "../store/actions.type";

export default {
  name: "ChangeEmail",
  data(){
    return{
      ChangeEmailForm:{
        email:'',
        code:''
      },
      buttonDisabled:false,
      timeToSend:null,
      rules:{
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
          /*{validator:showError,trigger: 'blur'}*/
        ]
      }
    }
  },
  methods:{
    ChangeEmail(){
      this.$refs['ChangeEmailForm'].validate().then(()=>{
        var body={
          email:this.ChangeEmailForm.email,
          code:this.ChangeEmailForm.code
        }
        this.$axios.post('/user/changeemail',body).then(({data})=>{
          if(data.code==200){
            console.log("修改邮箱成功",data);
            ElMessage.success('修改邮箱成功');
            this.$store.dispatch(GET_USER_INFO);
            this.$emit('exit');
          }
          else{
            console.log("修改失败",data);
            ElMessage.error(data.msg)
          }
        })
      })
    },
    getcode() {
      this.$refs['ChangeEmailForm'].validateField('email',(err)=>{
        if(!err){
          this.$axios.get('/user/sendcode?flag=noexist&to='+this.ChangeEmailForm.email)
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
}
</script>

<style lang="scss">
.changeEmailPage{
  .Box {
    position: fixed;
    top:calc(50% - 194px);
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