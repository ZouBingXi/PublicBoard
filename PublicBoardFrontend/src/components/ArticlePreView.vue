<template>
  <div class="contain myShadow">
      <div class="userInfo">
        <el-avatar :src="headimage" :size="32" class="headimg"></el-avatar>
        <span class="username">{{ username }}</span>
        <span class="type" :style="typeColor">{{ type }}</span>
      </div>
      <h1 class="title" @click="readArticle">{{ title }}</h1>
      <p class="content" @click="readArticle">{{content}}</p>
      <div class="subscript">
        <span>点赞量：{{ likenum }}</span>
        <span> 浏览量：{{ viewnum }}</span>
        <span>发表日期：{{sendtime}}</span>
      </div>
  </div>
</template>

<script>
import defaultImg from '../assets/logo.png'

const myprops={
  headimage:{
    default:defaultImg
  },
  title:{
    default:'题目'
  },
  userid:{
    default:'201902020202'
  },
  username:{
    default:'用户名1222'
  },
  content:{
    default:'看LOL视频不看马老师看LOL视频不看马老师看LOL视频不看马老师看LOL视频不看马老师看LOL视频不看马老师看LOL视频不看马老师看LOL视频不看马老师'
  },
  articleid:{
    default:'题目'
  },
  type:{
    default:'校园卡'
  },
  likenum:{
    default:100
  },
  viewnum:{
    default:100
  },
  sendtime:{
    default:'2021.7.23'
  }
}

export default{
  name: "ArticlePreView",
  props:myprops,
  data(){
    return{

    }
  },
  methods:{
    readArticle(){
      this.$router.push(
          {path:'/articleread',
            query:{
            article_userid:this.userid,
              article_id:this.articleid,
              article_type:this.type
          }
          })
    }
  },
  computed:{
    typeColor(){
      if(this.type=="校园卡丢失"||this.type=="寻主"||this.type=="寻物"||this.type=="捞人")
        return "background: #eeeeee;"
      else
        return "background: #bbbbbb;color:white"
    }
  }
}


</script>

<style scoped lang="scss">
.contain{
  /*display:inline-block;*/
  position: relative;
  padding: 15px 20px;
  margin-bottom: 10px;
/*  box-sizing: border-box;*/
  background: white;

  .userInfo{
    height: 32px;
    .username{
      display: inline-block;
      overflow: hidden;
      line-height: 32px;
      font-size: 16px;
      font-weight: bold;
      margin-left: 10px;
    }
    .type{
      padding: 2px 8px;
      background: #eeeeee;
      display: inline-block;
      font-size: 15px;
      font-weight: bold;
      color: #aaaaaa;
      float: right;
    }
  }
  .title{
    margin: 7px 0px;
    font-size: 27px;
    cursor: pointer;
    &:hover{
      color: #333333;
    }
  }
  .content{
    max-height: 60px;
    margin: 0px 0px 10px;
    margin-bottom: 10px;
    font-size: 14px;

    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 3;
    overflow: hidden;

    cursor: pointer;
    &:hover{
      color: #333333;
    }
  }
  .subscript{
    font-size: 12px;
    color: #909399;
    span:not(:first-child){
      margin-left: 10px;
    }
  }
}
</style>