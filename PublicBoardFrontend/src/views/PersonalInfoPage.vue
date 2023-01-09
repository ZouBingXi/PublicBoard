<template>
  <div class="headerContain">
    <div class="banner"> </div>
    <div class="whiteBackground myShadow">

      <el-upload
          class="avatar"
          :show-file-list="false"
          :http-request="myupload"
          :before-upload="beforeAvatarUpload">
        <el-avatar :src="this.currentUser.headimage" shape="square" fit="contain" :size="160" style="border-radius: 20px;"></el-avatar>
        <div class="avatar-icon">
          <i class="el-icon-camera-solid" style="font-size: 40px;margin-top: 40px"></i>
          <p>修改我的头像</p>
        </div>
      </el-upload>
      <div class="infoContain">
        <div class="info" style="border-bottom: none">
          <span class="username">{{ this.currentUser.username}}</span>
          <span class="link" @click="showChangeUserName=!showChangeUserName">编辑</span>
          <router-link :to="{path:'/personalpage'}" class="link" style="float: right;margin-top: 21px;text-decoration: none">返回我的主页></router-link>
        </div>
        <div v-for="item in list" class="info" :key="item.title">
          <p class="infoTitle">{{ item.title }}</p>
          <span class="infoValue">{{ item.value }}</span>
          <span v-if="item.canEdit" class="editLink" @click="show(item.flag)">编辑</span>
        </div>
      </div>
    </div>
    <ChangeEmail v-if="showChangeEmail" @exit="showChangeEmail=false"></ChangeEmail>
    <ChangePassword v-if="showChangePassword"  @exit="showChangePassword=false"></ChangePassword>
    <ChangeUserName  v-if="showChangeUserName"  @exit="showChangeUserName=false"></ChangeUserName>
  </div>
</template>

<script>
import ChangeEmail from "../components/ChangeEmail";
import ChangePassword from "../components/ChangePassword";
import ChangeUserName from "../components/ChangeUserName";

import headimg from "../assets/headimage.png"

import {mapGetters} from "vuex";
import {GET_USER_INFO} from "../store/actions.type";

export default {
  name: "PersonalSettingPage",
  components:{
    ChangeEmail,
    ChangePassword,
    ChangeUserName
  },
  data() {
    return {
      headimg: headimg,
      showChangeEmail:false,
      showChangePassword:false,
      showChangeUserName:false,
    }
  },
  computed: {
    list() {
      return [
        {title: '用户ID', value: this.currentUser.userid},
        {title: '邮箱', value: this.currentUser.email,canEdit:true,flag:'showChangeEmail'},
        {title: '密码', value: '已设置',canEdit:true,flag:'showChangePassword'},
        {title: '用户类型', value: this.currentUser.usertype},
        {title: '状态', value: this.currentUser.banstate},
      ]
    },
    ...mapGetters(['currentUser'])
  },
  methods:{
    myupload(context){
      console.log(context);
      let formData = new FormData();
      formData.append("avatar",context.file);
      this.$axios.post("user/updateprofile",formData)
          .then(re=>
          {
            console.log(re);
            this.$store.dispatch(GET_USER_INFO);
          });
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isPNG = file.type === 'image/png';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG/PNG');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return (isJPG || isPNG) && isLt2M;
    },
    show(flag){
      this[flag]=!this[flag]
    }
  }
}



</script>

<style scoped lang="scss">
.headerContain{
  position: relative;
  width: 100%;
  height: 100%;
  .banner{
    height: 250px;
    background-image: url("../assets/personalPageImg.png");
    background-size: cover;
    background-position:center center ;
  }

  .whiteBackground {
    position: relative;
    width: 1000px;
    height: 760px;
    background: white;
    margin: auto;
    .avatar {
      position: absolute;
      left: 29px;
      top: -32px;
      .avatar-icon{
        color: white;
        position: absolute;
        top: 0px;
        left: 0px;
        width: 160px;
        height: 160px;
        background: black;
        opacity: 0;
        border-radius: 20px;
        &:hover{
          opacity: 0.5;
        }
      }
    }
    .link{
      display: inline-block;
      overflow: hidden;
      font-size: 18px;
      color: black;
      line-height: 40px;
      margin-left: 37px;
      text-decoration: underline;
      cursor: pointer;
    }
    .username {
      display: inline-block;
      overflow: hidden;
      font-weight: bold;
      font-size: 40px;
      line-height: 60px;
    }

    .userid {
      position: absolute;
      left: 221px;
      top: 52px;
      margin: 0px;
      font-size: 21px;
      line-height: 29px;
      font-weight: 300;
    }
    .infoContain{
      position: absolute;
      left: 220px;
      top: 55px;
      .info{
        width: 735px;
        margin-bottom: 30px;
        font-size: 20px;
        line-height: 20px;
        border-bottom: #dddddd 1px solid;
        .infoTitle{
          font-weight: bold;
          margin: 0px 0px 25px;
        }
        .infoValue{
          display: inline-block;
          line-height: 20px;
          margin: 0px 0px 25px;
        }
        .editLink{
          display: inline-block;
          line-height: 20px;
          float: right;
          text-decoration: underline;
          cursor: pointer;
        }
      }
    }

  }
}

</style>