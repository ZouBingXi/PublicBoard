<template>
  <div class="headerContain">
    <div class="banner"> </div>
    <div class="whiteBackground myShadow">
      <el-avatar class="avatar" :src="currentUser.headimage" shape="square" fit="contain" :size="160"></el-avatar>
      <p class="username">{{ this.currentUser.username }}</p>
      <p class="userid">{{ this.currentUser.userid }}</p>
      <div class="buttonContain">
        <el-button class="changeInfoButton" @click="$router.push({path:'/personalinfopage'})">修改个人信息</el-button>
        <el-button class="editButton" @click="$router.push({path:'/articleedit'})">发帖<i class="el-icon-edit-outline"></i></el-button>
        <el-button class="logooutButton" @click="logout">退出登录</el-button>
        <el-button class="adminButton" @click="gotoAdmin" v-if="this.currentUser.usertype=='管理员'">后台管理</el-button>
      </div>
    </div>
    <div class="myNews">
      <el-menu mode="horizontal">
        <el-menu-item>我的发布</el-menu-item>
      </el-menu>
<!--      <ArticlePreView></ArticlePreView>
      <ArticlePreView></ArticlePreView>
      <ArticlePreView></ArticlePreView>
      <ArticlePreView></ArticlePreView>
      <ArticlePreView></ArticlePreView>
      <ArticlePreView></ArticlePreView>-->
      <MyArticleList></MyArticleList>
    </div>
  </div>
</template>

<script>
/*import ArticlePreView from "../components/ArticlePreView";*/
import MyArticleList from "../components/MyArticleList";

import {mapGetters} from "vuex";
import {LOGOUT} from "../store/actions.type";

import imgURL from '../assets/personalPageImg.png'
import headimg from '../assets/headimage.png'

export default {
  name: "PersonalInfoPage",
/*  components: {ArticlePreView},*/
  components:{MyArticleList},
  data(){
    return{
      imgURL:imgURL,
      headimg:headimg,
    }
  },
  computed:{
    ...mapGetters(['currentUser'])
  },
  methods:{
    logout(){
      this.$store.dispatch(LOGOUT).then(()=>{
        this.$router.push({path:'/login'});
      })
    },
    gotoAdmin(){
      this.$router.push({path:'/admin'});
    }
  }
}
</script>

<style scoped lang="scss">
.headerContain{
  position: relative;
  width: 100%;
  .banner{
    height: 250px;
    background-image: url("../assets/personalPageImg.png");
    background-size: cover;
    background-position:center center ;
  }

  .whiteBackground{
    position: relative;
    width: 1000px;
    height: 168px;
    background: white;
    margin: auto;
    margin-bottom: 10px;
    .avatar{
      position: absolute;
      left: 29px;
      top: -32px;
      border-radius: 20px;
    }
    .username{
      position: absolute;
      left: 221px;
      top: 14px;
      margin: 0px;
      /* 用户名 */
      font-weight: bold;
      font-size: 28px;
      line-height: 38px;
    }
    .userid{
      position: absolute;

      left: 221px;
      top: 52px;
      margin: 0px;
      font-size: 21px;
      line-height: 29px;
      font-weight: 300;
    }
    .buttonContain{
      position: absolute;
      left:210px;
      top:99px;
      .el-button{
        position: relative;
        font-size: 15px;
        min-height: 30px;
        font-weight: bold;
        border-radius: 6px;
        border: black 1px solid;
        font-family:Noto Sans Kannada UI;
        &.is-active,&:hover,&:active,&:focus{
          background: none;
        }
        &.editButton{
          color: black;
          padding: 0px 16px;
          margin-left: 25px;
        }
        &.changeInfoButton{
          color: white;
          background: black;
          padding: 0px 31px;
        }
        &.logooutButton{
          color: #909399;
          padding: 0px 10px;
          border: #909399 1px solid;
          margin-left: 415px;
        }
        &.adminButton{
          position: absolute;
          top:0px;
          left:570px ;
          color: #909399;
          padding: 0px 10px;
          border: #909399 1px solid;
        }
      }
    }
  }
}

.myNews{
  width: 1000px;
  margin: auto;
  .el-menu{
    margin-bottom: 10px;
    .el-menu-item{
      line-height: 45px;
      height: 45px;
      font-weight: bold;
      font-size: 18px;
    }
  }

}
</style>