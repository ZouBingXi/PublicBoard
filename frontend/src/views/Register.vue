<template>
  <!-- 页面背景 -->
  <div id="background">

    <!-- 基础页面 -->
    <div id="base">

      <!-- 注册背景 -->
      <div id="register-background">
        <el-image id="register-background-img" :src=images_list.image1 ></el-image>
      </div>

      <!-- 注册组合 -->
      <div id="register-combo">

        <!-- 注册文本 -->
        <div id="register-text">
          <p id="register-text1">注册</p>
        </div>

        <!-- 拓展文本 -->
        <div id="extra-text">
          <p id="extra-text1">已有账号？</p>
        </div>

        <!-- 返回登录文字链接 -->
        <div id="register-text-link">
          <el-link @click="gotoLogin('ruleForm')" id="register-text-link1" :underline="false" type="info">登录</el-link>
        </div>

        <!-- 输入表单 -->
        <div id="input-form">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="70px" class="demo-ruleForm" hide-required-asterisk>

            <el-form-item id="input-form-item1" label="学号" prop="userid">
              <el-input v-model.number="ruleForm.userid" placeholder="请输入学号"></el-input>
            </el-form-item>

            <el-form-item id="input-form-item2" label="密码" prop="password">
              <el-input v-model="ruleForm.password" placeholder="请输入密码" show-password></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="checkpassword">
              <el-input v-model="ruleForm.checkpassword" placeholder="请再次输入密码" show-password></el-input>
            </el-form-item>
<!--            <el-form-item>-->
<!--              <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>-->
<!--              <el-button @click="resetForm('ruleForm')">重置</el-button>-->
<!--            </el-form-item>-->
          </el-form>
        </div>

        <!-- 注册按钮 -->
        <div id="register-button">
          <el-button id="register-button1" type="info" @click="submitForm('ruleForm')">注册</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import  register_background from '../common/images/Register/register_background.svg'

export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value !== this.ruleForm.password) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        userid: '',
        password: '',
        checkpassword: ''
      },
      rules: {
        userid: [
          { required: true, message: '请输入学号', trigger: 'blur' },
          { type: 'number', min: 1000000000, max: 9999999999, message: '学号为 10 位数字', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 6, max: 20, message: '密码长度在 6 到 20 个字符', trigger: 'blur' }
        ],
        checkpassword: [
          { required: true, message: '请再次输入密码', trigger: 'blur' },
          { validator: validatePass, trigger: 'blur' }
        ]
      },
      images_list: {
        image1: register_background
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          this.$axios.post('/user/add', {
            userid: _this.ruleForm.userid,
            password: _this.ruleForm.password
          }).then((response) => {
            console.log(response);
            this.$refs[formName].resetFields();
            if(response.data.code == 200) {
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'success'
              });
              this.$router.push({path: '/login'});
            }
            else {
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'error'
              });
            }
          });
        } else {
          this.$message({
            showClose: true,
            message: '注册失败，请完成信息填写',
            type: 'error'
          });
          return false;
        }
      });
    },
    gotoLogin(formName) {
      this.$refs[formName].resetFields();
      this.$router.push({path: '/login'});
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

#register-background-img {
  border-width:0px;
  position:absolute;
  left:0px;
  top:0px;
  width:910px;
  height:530px;
}

#register-background {
  border-width:0px;
  position:absolute;
  left:62px;
  top:120px;
  width:910px;
  height:530px;
  display:flex;
}

#register-combo {
  border-width:0px;
  position:absolute;
  left:115px;
  top:182px;
  width:300px;
  height:432px;
}

#register-text {
  border-width:0px;
  position:absolute;
  left:118px;
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
#register-text1 {
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

#extra-text {
  border-width:0px;
  position:absolute;
  left:125px;
  top:55px;
  width:73px;
  height:21px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
}

#extra-text1 {
  position:absolute;
  align-self:flex-start;
  margin:0px 0px 0px 0px;
  padding:0px 0px 0px 0px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
  font-size:12px;
}

#register-text-link {
  border-width:0px;
  position:absolute;
  left:160px;
  top:53px;
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
  padding:0px 0px 0px 0px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
  font-size:12px;
  text-decoration:underline;
}

#input-form {
  border-width:0px;
  position:absolute;
  left:0px;
  top:92px;
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

#register-button {
  border-width:0px;
  position:absolute;
  left:70px;
  top:325px;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:15px;
}

#register-button1 {
  width:221px;
}

</style>