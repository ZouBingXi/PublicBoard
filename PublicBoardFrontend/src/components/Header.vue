<template>
  <el-affix>
  <div class="header">
    <img :src="logoimg" class="logo"/>
    <div style="display: inline-block;margin-left: 10px">
      <el-menu mode="horizontal" class="menu" router :default-active="this.$route.path" style="border-bottom: none">
        <el-menu-item index="/"><i class="el-icon-s-home" style="margin: 0px;font-size: 20px"></i> 首页</el-menu-item>
      </el-menu>

<!--      <div class="headMenu">
        <el-menu  mode="horizontal">
          <el-submenu>
            <template #title>
              <img :src="currentUser.headimage" @click="this.$router.push({path:'/personalpage'})" @focusout="menuShow=false" class="headimg">
            </template>
            <el-menu-item @click.stop="this.$router.push({path:'personalpage'})"><i class="el-icon-s-custom" style="margin: 0px"/> 个人主页</el-menu-item>
            <el-menu-item><i class="el-icon-s-tools" style="margin: 0px"/>个人信息</el-menu-item>
            <el-menu-item><i class="el-icon-switch-button" style="margin: 0px"/>退出登录</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>-->

    </div>

    <img :src="currentUser.headimage" @click="this.$router.push({path:'/personalpage'})" @focusout="menuShow=false" class="headimg">
    <!--    头像菜单栏-->
    <div class="anonymousmodeButton">
      <el-switch active-text="匿名模式" v-model="currentUser.anonymousmode" @change="changeAnonymousmode"></el-switch>
    </div>

  </div>
  </el-affix>
</template>

<script>
import logoimg from "../assets/logo.png"
import headimg from "../assets/headimage.png"
import {ElMessage} from "element-plus";

import {mapGetters} from "vuex";

import {GET_USER_INFO} from "../store/actions.type";

export default {
  name: "Header",
  components:{
  },
  data(){
    return{
      logoimg:logoimg,
      headimg:headimg,
      menuShow:false,

    }
  },
  computed:{
    ...mapGetters(['currentUser'])
  },
  methods:{
    changeAnonymousmode(){
      this.$axios.post('/user/switchmode').then(({data})=>{
        ElMessage.success(data.msg);
        this.$store.dispatch(GET_USER_INFO);
      })
    }
  }
}
</script>

<style lang="scss">
.header{
  position: relative;
  width: 100%;
  height: 60px;
  background: white;

  .logo{
    width: 180px;
    margin: 5px 15px;
  }
  .menu{
    border-bottom: none;
    .el-menu-item{
      font-size: 20px;
      font-weight: bold;
      height: 50px;
      line-height: 50px;

    }
  }
  .headimg{
    width: 40px;
    height: 40px;
    float: right;
    margin: 10px 20px;

    border-radius:5px;
    cursor: pointer;
  }
  .headMenu{
    position: absolute;
    top: 0px;
    right: 0px;

    .el-menu-item{
      height: 30px;
      width: 40px;
      line-height: 40px;
      color: #909399;
      font-weight: bold;
    }
    .el-menu--horizontal > .el-submenu.is-active .el-submenu__title{
      border-bottom: none;
      height: 50px;
    }
  }
  .anonymousmodeButton{
    position: absolute;
    top:20px;
    right: 100px;
  }
}

</style>