<template>
  <el-container >
    <el-container>
      <el-aside width="250px">
        <el-menu router :default-active="$route.path">
          <el-submenu v-for="(item,index) in elSubMenu" :index="item.title" :key="index">
            <template #title><i
              :class="item.icon"></i>{{ item.title }}</template>
            <el-menu-item v-for="menuItem in item.elMenuItem" :index="menuItem.index" :key="menuItem.text">
              {{ menuItem.text }}
            </el-menu-item>
          </el-submenu>

        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>export default {
  name: "Admin",
  data() {
    return {
      elSubMenu: [
        {
          title: '数据表',
          icon: "el-icon-notebook-2",
          elMenuItem: [
            {
              index: "/admin/usertable",
              text: "用户信息表"
            },
/*            {
              index: "/admin/topictable",
              text: "话题信息表"
            },*/
            {
              index: "/admin/poststable",
              text: "帖子信息表"
            },
 /*           {
              index: "/admin/logtable",
              text: "日志表"
            },*/
          ]
        }
      ]
    }
  },
}
</script>

<style scoped>
.el-main{
  padding: 10px;
}
.el-aside{
  background: white;
}

</style>
