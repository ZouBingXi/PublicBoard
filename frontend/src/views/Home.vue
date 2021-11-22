<template>
  <el-header>
    <el-menu mode="horizontal">
      <div style="
        margin-left: 1%;
        margin-right: 1%;
        font-size: 24px;
        float: left;
        line-height: 60px;
        color: black">
        <span>PublicBoard主页</span>
      </div>
      <el-menu-item style="float: right">sign out</el-menu-item>
    </el-menu>
    <h1 style="vertical-align: center;color: white"></h1>
  </el-header>
</template>

<script>
import TheHeader from "@/components/TheHeader";
export default {
  name: "Home",
  components: {TheHeader},
}
</script>

<style scoped>

</style>