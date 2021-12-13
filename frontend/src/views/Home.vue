<template>
  <div>
    <Banner></Banner>
    <div class="contain" >
      <el-menu mode="horizontal" router :default-active="$route.path"  active-text-color="black">
        <el-menu-item index="/">帖子</el-menu-item>
        <el-menu-item index="/subjectslist">话题</el-menu-item>
      </el-menu>
      <router-view></router-view>
    </div>

<!--    <el-button @click="test">test</el-button>-->
  </div>
</template>

<script>
import Banner from "@/components/Banner";
export default {
  name: "Home",
  components:{
    Banner
  },
  methods:{
    test(){
      axios.get("/news/viewDiffNews?typeid=1&page=1&size=10")
          .then((re)=>{
            console.log(re);
          })
    }
  }
}
</script>

<style scoped>

.contain{
  width: 800px;
  margin: auto;
}

</style>