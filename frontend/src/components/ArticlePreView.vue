<template>
  <div class="contain">
    <router-link :to="{path:'/articleread',name:'ReadArticle',query:{article_type:type,article_userid:userid,article_id:articleid}}" class="preview-link">
        <el-avatar :src="headimage"></el-avatar>
        <span>{{username}}</span>
      <h1 v-text="title" />
      <p v-text="content"/>
      <el-avatar v-for="item in image" :src="item" fit="fill" :size="150" shape="square"></el-avatar>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "ArticlePreView",
  props:["title","userid","username","content","articleid","type","headimage","image"]
}
</script>

<style scoped>
.contain{
  margin-top: 15px;
  margin-bottom: 15px;
  border:10px black;
}
.preview-link{
  font-size: 15px;
  font-family:sans;
  text-decoration: none;
  font-weight:bold;
  color: black;
}
</style>

