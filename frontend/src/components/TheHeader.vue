<template>
  <el-header class="myheader">
    <el-menu mode="horizontal" router :default-active="$route.path"  active-text-color="black">
      <router-link class="mylink" :to="{path:'/'}">PublicBoard</router-link>
      <el-menu-item v-if="isAuthenticated" index="/information">
        <span class="el-icon-s-custom" style="margin: 0px"></span>{{currentUser.username}}
      </el-menu-item>
      <el-menu-item v-for="item in menu" :index="item.index" :key="item.index">
          <span :class="item.icon" style="margin: 0px"></span>{{ item.text }}
      </el-menu-item>
      <el-menu-item v-if="currentUser.usertype==='管理员'&& isAuthenticated" index="/admin">
        <span class="" style="margin: 0px"></span>Admin Page
      </el-menu-item>
      <el-menu-item index="/">
        Home
      </el-menu-item>
    </el-menu>
  </el-header>
</template>

<script>
import {mapGetters} from "vuex"

export default {
  name: "TheHeader",
  data(){
    return {
      menu_notAuthenticated:[
        {
          index:"/register",
          icon:"el-icon-edit-outline",
          text:"Sign Up",
        },
        {
          index:"/login",
          icon:"el-icon-edit-outline",
          text:"Sign In",
        }
      ],
      menu_isAuthenticated:[
        {
          index:"/articleedit",
          icon:"el-icon-edit-outline",
          text:"New Article",
        },
        {
          index:"/setting",
          icon:"el-icon-s-tools",
          text:"Setting",
        },
      ]
    }
  },
  computed:{
    ...mapGetters(["currentUser","isAuthenticated"]),
    menu(){
      return this.isAuthenticated?this.menu_isAuthenticated:this.menu_notAuthenticated;
    }
  }

};
</script>

<style scoped>
.myheader{
  padding: 0px 20px 0px 20px;

}
.myheader .el-menu .el-menu-item{
  float: right;
  padding: 0px;
  margin-left: 5px;
  margin-right: 5px;
}

.myheader .mylink{
  line-height: 60px;
  font-size: 26px;
  font-family:sans;
  text-decoration: none;
  font-weight:bold;
  color: black;
}
</style>
