<template>
  <div class="changeUsername">
    <div class="transparentBackground">
    </div>
    <div class="Box">
      <h1 class="TitleText">修改用户名</h1>
      <el-form :model="changeUsernameForm" :rules="rules" ref="changeUsernameForm" label-position="top" @keypress.enter="ChangeName">
        <el-form-item label="新用户名：" prop="newName">
          <el-input v-model="changeUsernameForm.newName" maxlength="20" placeholder="请输入新用户名"></el-input>
        </el-form-item>
      </el-form>
      <el-button @click="ChangeName" type="primary">确定</el-button>
      <el-button @click="this.$emit('exit')" type="primary">取消</el-button>
    </div>
  </div>
</template>

<script>
import {GET_USER_INFO} from "../store/actions.type";

export default {
  name: "changePassword",
  data() {
    return {
      changeUsernameForm: {
        newName:''
      },
      rules: {
        newName: [
          {required: true, message: '请输入用户名', trigger: 'blur'}
        ],
      }
    }
  },
  methods:{
    ChangeName(){
      this.$refs['changeUsernameForm'].validate().then(()=>{
        let formData = new FormData();
        formData.append('username',this.changeUsernameForm.newName);
        this.$axios.post("user/updateprofile",formData)
            .then(re=>
            {
              console.log(re);
              this.$store.dispatch(GET_USER_INFO);
              this.$emit('exit');
            });
      })
    },
  }
}
</script>

<style lang="scss">
.changeUsername{
  .Box {
    position: fixed;
    top:calc(50% - 150px);
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