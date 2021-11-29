<template>
  <el-container>
    <el-container>
      <el-aside>
        <el-menu router :default-active="$route.path">
          <el-submenu v-for="item in elSubMenu" :index="item.title" :key="elSubMenu.text"><span slot="title"><i
              :class="item.icon"></i>{{ item.title }}</span>
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
  data(){
    return{
      elSubMenu:[
        {
          title: '数据表',
          icon: "el-icon-notebook-2",
          elMenuItem:[
            {
              index: "/admin/usertable",
              text: "用户信息表"
            },
            {
              index: "/admin/topictable",
              text: "话题信息表"
            },
            {
              index: "/admin/poststable",
              text: "帖子信息表"
            },
            {
              index: "/admin/logtable",
              text: "日志表"
            },
          ]
        }
      ]
    }
  },
}
</script>

<style scoped>

</style>
