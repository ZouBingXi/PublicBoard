<template>
  <div>
    <!-- 横幅 -->
    <Banner></Banner>

    <!-- 搜索排序区 -->
    <el-affix :offset="60">
    <div class="search-sort-box">
      <!-- 搜索区 -->
      <div class="search-box">
        <el-input placeholder="请输入搜索内容" v-model="key" @change="handleKeyChange" class="input-with-select">
          <template #append>
            <el-button icon="el-icon-search" @click="handleKeyChange"></el-button>
          </template>
        </el-input>
      </div>

      <!-- 排序区 -->
      <div class="sort-box">
        <el-radio-group v-model="sort" size="medium">
          <el-radio-button label="1" >最新发布</el-radio-button>
          <el-radio-button label="2">最多浏览</el-radio-button>
          <el-radio-button label="3">最多点赞</el-radio-button>
        </el-radio-group>
      </div>

      <!-- 发帖按钮 -->
      <el-button type="info" round plain icon="el-icon-edit" @click="this.$router.push({path: '/articleedit'});">发帖</el-button>
    </div>
    </el-affix>

    <!-- 主要信息区 -->
    <div class="main-info-box">
      <!-- 左侧栏 -->
      <div class="left-aside-box">
        <el-affix :offset="150">
          <el-tabs
              class="tabs-style"
              value="n0"
              tab-position="left"
              @tab-click="handleClick">
            <el-tab-pane label="不限类型" name=""></el-tab-pane>
            <el-tab-pane label="校园卡丢失" name="校园卡丢失"></el-tab-pane>
            <el-tab-pane label="寻主" name="寻主"></el-tab-pane>
            <el-tab-pane label="寻物" name="寻物"></el-tab-pane>
            <el-tab-pane label="捞人" name="捞人"></el-tab-pane>
            <el-tab-pane label="求助" name="求助"></el-tab-pane>
            <el-tab-pane label="避雷" name="避雷"></el-tab-pane>
            <el-tab-pane label="指南" name="指南"></el-tab-pane>
            <el-tab-pane label="课程" name="课程"></el-tab-pane>
          </el-tabs>
        </el-affix>
      </div>

      <!-- 主要区域 -->
      <div class="main-box">
        <HomeArticleList :search="search_content" :sort="sort" :type="type">

        </HomeArticleList>
      </div>

      <!-- 右侧栏 -->
      <div class="right-aside-box">
        <el-affix :offset="150">
          <el-image id="right-aside-img"
                    style="width: 300px; height: 500px"
                    fit="fit"
                    :src=right_aside_img
          ></el-image>
        </el-affix>
      </div>
    </div>

<!--    <div class="contain" >-->
<!--      <el-menu mode="horizontal" router :default-active="$route.path"  active-text-color="black">-->
<!--        <el-menu-item index="/">帖子</el-menu-item>-->
<!--        <el-menu-item index="/subjectslist">话题</el-menu-item>-->
<!--      </el-menu>-->
<!--      <router-view></router-view>-->
<!--    </div>-->

<!--回到顶部按钮-->

    <el-popover
        placement="left"
        :width="60"
        trigger="hover"
        content="回到顶部"
        hide-after="0"
    >
      <template #reference>
        <el-backtop>
          <div
              style="
        height: 100%;
        width: 100%;
        background-color: #f2f5f6;
        box-shadow: 0 0 6px rgba(0, 0, 0, 0.12);
        text-align: center;
        line-height: 40px;
        color: #303133;
        font-weight: bold;
      "
          >
            UP
          </div>
        </el-backtop>
      </template>
    </el-popover>

  </div>
</template>

<script>
import Banner from "@/components/Banner";
import HomeArticleList from "../components/HomeArticleList";

import  Home_info_img from '../assets/logogif.gif'

/*import {PURGE_AUTH} from "@/store/mutations.type";
import router from "@/router";
import {ElMessage} from "element-plus";*/

export default {
  name: "Home",
  components:{
    Banner,
    HomeArticleList
  },

  data() {
    return {
      key: '',
      search_content:'',
      sort: 1,
      currentpage:1,
      pagesize:10,
      type:'',
      articles:[],
      right_aside_img: Home_info_img
    }
  },
  methods: {
    handleClick(tab) {
      console.log(tab.props.name);
      this.type = tab.props.name;
    },
    handleKeyChange(){
      this.search_content=this.key;
    }
  },
}
</script>

<style scoped >

.search-sort-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  margin: 0;
  padding: 20px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  background-color: white;
}

.search-box {
  width: 400px;
}

.el-input /deep/ .el-input__inner:focus{
  border-color:#17181A;
}

.sort-box {
  margin: 0px 25px 0px 25px;
}

.main-info-box {
  display: flex;
  justify-content: center;
  /*align-items: center;*/
  margin: 0;
  padding: 20px;
}

.left-aside-box {
  width: 300px;
  height: 400px;
  display: flex;
  justify-content: flex-end;
  /*align-items: center;*/
}

.tabs-style {
  /*margin-right: 50px;*/
}

.main-box {
  width: 690px;
  margin: 0px 20px 0px 20px;
}

.right-aside-box {
  width: 300px;
}

/*.contain{*/
/*  width: 800px;*/
/*  margin: auto;*/
/*}*/

</style>

<style lang="scss">
.el-popover.el-popper{
  min-width: 60px;
}
</style>