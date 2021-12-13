<template>
  <!-- 页面背景 -->
  <div class="background">

    <!-- 横幅 -->
    <div class="banner">
      <!-- 文章标题 -->
      <h1>{{ description.title }}</h1>

      <!-- 其他内容 -->
      <div class="main-user-box">

        <!-- 作者头像 -->
        <el-avatar class="main-avatar" :size="40" :src="description.mainavatarUrl" @error="errorHandler">
          <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
        </el-avatar>

        <!-- 作者信息 -->
        <div class="main-user-info">
          <el-link class="main-username-link" type="primary" :underline="false" @click="gotoArticleEdit">{{ description.username }}</el-link>
          <p class="article-time">{{ description.sendtime }}</p>
        </div>

        <!-- 标签 -->
        <el-tag class="main-tag" @click="gotoArticleEdit">{{ article_type }}</el-tag>
        <el-tag class="child-tag" type="success" v-show="article_type == '帖子'">{{ description.newstype }}</el-tag>
        <el-tag class="child-tag" type="warning" v-show="article_type == '话题'">{{ description.subjecttype }}</el-tag>
      </div>
    </div>

    <!-- 文章本体 -->
    <div class="article-box">
      <!-- 文章内容 -->
      <p class="article-content">{{ description.content }}</p>

      <!-- 配图 -->
      <div class="images-list" v-show="imgList.length > 0">
        <el-image
            style="width: 250px; height: 250px; margin-right: 20px; margin-bottom: 20px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"
            v-for="(item, index) in imgList"
            :key="index"
            :src="item"
            fit="cover"
            :preview-src-list="imgList">
          <div slot="error" class="image-slot">
<!--            <i style="font-size:40px" class="el-icon-picture-outline"></i>-->
            <el-skeleton style="width: 250px">
              <template slot="template">
                <el-skeleton-item variant="image" style="width: 250px; height: 250px;" />
              </template>
            </el-skeleton>
          </div>
          <div slot="placeholder" class="image-slot">
<!--            加载中<span class="dot">...</span>-->
            <el-skeleton style="width: 250px" animated>
              <template slot="template">
                <el-skeleton-item variant="image" style="width: 250px; height: 250px;" />
              </template>
            </el-skeleton>
          </div>
        </el-image>
      </div>

      <!-- 点赞区 -->
      <div class="like-box">
        <label class="heart-label" @change="change_main_likenum">
          <input type="checkbox" v-model="description.main_checklike"></input>
          <svg class="heart-svg" viewBox="0 0 532 532">
            <path id="heart" transform="translate(10, 10)" d="M256,96.5l-19.7-20.3C186.1,24.3,104.5,15.9,49.7,62.6c-62.8,53.6-66.1,149.8-9.9,207.9l193.5,199.8 c12.5,12.9,32.8,12.9,45.3,0l193.5-199.8c56.3-58.1,53-154.3-9.8-207.9l0,0C407.5,15.9,326,24.3,275.7,76.2L256,96.5z"/>
          </svg>
          <span class="heart-outside"></span>
        </label>

        <p class="main-like-num">{{ description.main_likenum }}</p>
      </div>

      <!-- 分割线 -->
      <div style="margin: 0px 50px 0px 50px">
        <el-divider></el-divider>
      </div>
    </div>

    <!-- 发表评论窗口 -->
    <div class="post-comment-box">
      <!-- 输入框 -->
      <div class="input-comment-box">
        <el-input type="textarea" :autosize="{ minRows: 5 }" v-model="myuser.content" placeholder="发表评论..." maxlength="1000" show-word-limit></el-input>
      </div>

      <!-- 其他内容 -->
      <div class="my-user-box">
        <!-- 用户头像 -->
        <el-avatar class="my-avatar" :size="40" :src="myuser.myavatarUrl" @error="errorHandler">
          <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
        </el-avatar>

        <!-- 用户信息 -->
        <el-link class="my-username-link" type="primary" :underline="false" @click="gotoArticleEdit">{{ myuser.username }}</el-link>

        <!-- 发表评论按钮 -->
        <div class="input-button-box">
          <el-button type="info" @click="submitcomment">发表评论</el-button>
        </div>
      </div>
    </div>

    <!-- 评论区 -->
    <div class="comment-list">
      <!-- 评论窗口 -->
      <div class="comment-box" v-for="(item, index) in comment" :key="index">
        <!-- 评论内容 -->
        <p class="comment-content">{{ item.content }}</p>

        <!-- 其他内容 -->
        <div class="comment-user-box">

          <!-- 评论者头像 -->
          <el-avatar class="comment-avatar" :size="40" :src="item.avatarUrl" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>

          <!-- 评论者信息 -->
          <div class="comment-user-info">
            <el-link class="comment-username-link" type="primary" :underline="false" @click="gotoArticleEdit">{{ item.username }}</el-link>
            <p class="comment-time">{{ item.sendtime }}</p>
          </div>

          <!-- 点赞区 -->
          <div class="comment-like-box">
            <label class="heart-label" @change="change_comment_likenum(index)">
              <input type="checkbox" v-model="item.comment_checklike"></input>
              <svg class="heart-svg" viewBox="0 0 532 532">
                <path id="commentheart" transform="translate(10, 10)" d="M256,96.5l-19.7-20.3C186.1,24.3,104.5,15.9,49.7,62.6c-62.8,53.6-66.1,149.8-9.9,207.9l193.5,199.8 c12.5,12.9,32.8,12.9,45.3,0l193.5-199.8c56.3-58.1,53-154.3-9.8-207.9l0,0C407.5,15.9,326,24.3,275.7,76.2L256,96.5z"/>
              </svg>
              <span class="heart-outside"></span>
            </label>

            <p class="comment-like-num">{{ item.comment_likenum }}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- 回到顶部 -->
    <el-backtop />
  </div>
</template>

<script>
import {mapGetters} from "vuex"

export default {
  name: "ReadArticle",
/*  props: {
    article_type: {
      type: String,
      require: true
    },
    article_userid: {
      type: Number,
      require: true
    },
    article_id: {
      type: Number,
      require: true
    }
  },*/

  data() {
    return {
      description: {
        anonymousstate: false,
        userid: 1111111111,
        username: '不存在用户',
        title: '',
        mainavatarUrl: '',
        type: '贴子',
        newstype: '校园卡丢失',
        subjecttype: '避雷',
        content: '',
        sendtime: '0000-00-00',
        viewnum: 0,
        main_checklike: false,
        main_likenum: 0,
        topstate: false,
        hotstate: false
      },
      imgList: [],
      myuser: {
        userid: '',
        username: '',
        myavatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
        content: ''
      },
      comment: [
        {
          userid: '2020000000',
          username: 'firstuser',
          avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
          content: '这是一条评论这是一条评论这是一条评论这是一条评论',
          sendtime: '2021-12-12',
          comment_checklike: false,
          comment_likenum: 10
        },
        {
          userid: '2021111111',
          username: 'nextuser',
          avatarUrl: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
          content: '第二条评论第二条评论第二条评论第二条评论第二条评论',
          sendtime: '2021-12-24',
          comment_checklike: false,
          comment_likenum: 20
        }
      ]
    }
  },
  methods: {
    errorHandler() {
      return true;
    },
    submitcomment() {
      let _this = this;

      if (this.myuser.content.length < 20 || this.myuser.content.length > 1000) {
        this.$message({
          showClose: true,
          message: '评论内容长度在 20 到 1000 个字符',
          type: 'error'
        });
      } else {
        this.$axios.post('', {content: _this.myuser.content}).then((response) => {
          this.myuser.content = '';
          if(response.data.code == 200) {
            this.$message({
              showClose: true,
              message: response.data.msg,
              type: 'success'
            })
            this.$router.push({path: '/articleread'});
          } else {
            this.$message({
              showClose: true,
              message: response.data.msg,
              type: 'error'
            });
          }
        })
      }
    },
    gotoArticleEdit() {
      this.$router.push({path: '/articleedit'});
    },
    getArticle() {
      let _this = this;
      this.myuser.username = this.currentUser.username;
      this.description.type = this.article_type;

      if(this.description.type == '帖子')
      {
        this.$axios.get('/news/view?userid=' + this.article_userid + '&newsid=' + this.article_id)
            .then((response) => {
              console.log(response);
              _this.description.anonymousstate = response.data.data.anonymousstate;
              if(_this.description.anonymousstate == true) {
                _this.description.userid = 0;
                _this.description.username = '匿名用户';
                _this.description.mainavatarUrl = '';
              } else {
                _this.description.userid = response.data.data.userid;
                _this.description.username = response.data.data.username;
                _this.description.mainavatarUrl = response.data.data.headimage;
              }
              _this.description.title = response.data.data.newstitle;
              _this.description.content = response.data.data.content;
              _this.description.sendtime = response.data.data.sendtime;
              _this.description.viewnum = response.data.data.viewnum;
              _this.description.main_likenum = response.data.data.likenum;
              _this.description.newstype = response.data.data.newstypename;
              _this.description.topstate = response.data.data.topstate;
              _this.description.hotstate = response.data.data.hotstate;
              _this.imgList = response.data.data.imgUrls;
            }).catch(()=>{console.log("失败")});
      } else {
        this.$axios.get('/subject/view?userid='+ this.article_userid +'&subjectid=' + this.article_id)
            .then((response) => {
              console.log(response);
              _this.description.anonymousstate = response.data.data.anonymousstate;
              if(_this.description.anonymousstate == true) {
                _this.description.userid = 0;
                _this.description.username = '匿名用户';
                _this.description.mainavatarUrl = '';
              } else {
                _this.description.userid = response.data.data.userid;
                _this.description.username = response.data.data.username;
                _this.description.mainavatarUrl = response.data.data.headimage;
              }
              _this.description.title = response.data.data.subjecttitle;
              _this.description.content = response.data.data.content;
              _this.description.sendtime = response.data.data.sendtime;
              _this.description.viewnum = response.data.data.viewnum;
              _this.description.main_likenum = response.data.data.likenum;
              _this.description.subjecttype = response.data.data.subjecttypename;
              _this.description.topstate = response.data.data.topstate;
              _this.description.hotstate = response.data.data.hotstate;
              _this.imgList = response.data.data.imgUrls;
            }).catch(()=>{console.log("失败")});
      }
    },
    change_main_likenum() {
      if(this.description.main_checklike) {
        this.description.main_likenum ++;
      } else {
        this.description.main_likenum --;
      }
    },
    change_comment_likenum(index) {
      if(this.comment[index].comment_checklike) {
        this.comment[index].comment_likenum ++;
      } else {
        this.comment[index].comment_likenum --;
      }
    }
  },
  created:function (){
    console.log("CREATED");
    console.log(this.article_id,this.article_type,this.article_userid);
    this.getArticle();
  },
  computed:{
    ...mapGetters(["currentUser","isAuthenticated"]),
    article_type(){
      return this.$route.query.article_type
    },
    article_userid(){
      return this.$route.query.article_userid
    },
    article_id(){
      return this.$route.query.article_id
    },
  },
}
</script>

<style scoped>
.banner {
  background-color: #313131;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: 10px -20px 10px -20px;
  padding: 30px;
  max-height: 300px;
  box-shadow: 0px 2px 10px -2px rgba(0,0,0,0.75) inset, 0px -2px 10px -2px rgba(0,0,0,0.75) inset;
}

h1 {
  color: white;
  font-family: 微软雅黑;
  margin: 0px 50px 0px 50px;
  padding: 0;
  font-size: 2.5rem;
  letter-spacing: +0.15rem;
  position: relative;
}

.main-user-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-end;
  margin-top: 30px;
  padding: 0;
  position: relative;
}

.main-avatar {
  margin: 0px 20px 0px 50px;
  position: relative;
}

.main-user-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0;
  position: relative;
}

.main-username-link {
  color: white;
  font-family: 微软雅黑;
  margin: 0px 0px 5px 0px;
  padding: 0;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.main-username-link:hover {
  color: #909399;
}

.article-time {
  color: white;
  font-family: 微软雅黑;
  margin: 0;
  padding: 0;
  font-size: 10px;
  font-weight: 100;
  letter-spacing: +0.05rem;
  position: relative;
}

.main-tag {
  margin: 0px 10px 0px 20px;
  position: relative;
}

.child-tag {
  margin: 0px 10px 0px 10px;
  position: relative;
}

.article-box {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: 0px -20px 0px -20px;
  padding: 30px;
  position: relative;
}

.article-content {
  font-family: 微软雅黑;
  margin: 0px 50px 0px 50px;
  padding: 0;
  font-size: 1.5rem;
  /*letter-spacing: +0.15rem;*/
  position: relative;
}

.images-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin: 40px 50px 0px 50px;
  padding: 0;
  position: relative;
}

.image-slot {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

.like-box {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-top: 50px;
}

.heart-svg {
  width: 50px;
  position: relative;
  z-index: 10;
}

#heart {
  fill: #eee;
  stroke-width: 20px;
  stroke: red;
  stroke-dasharray: 1600;
  stroke-dashoffset: 1600;
  stroke-linecap: round;
}

.heart-outside, .heart-outside::after {
  display: block;
  width: 7px;
  height: 7px;
  margin-bottom: 5px;
  background-color: transparent;
  border-radius: 50%;
  position: absolute;
  transform: scale(0);

  box-shadow: 0 -35px 0 red, 0 35px 0 red, -35px 0 0 red, 35px 0 0 red;
}

.heart-outside::after {
  content: '';
  transform: rotate(45deg);
}

input[type="checkbox"] {
  display: none;
}

.heart-label {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

input[type="checkbox"]:checked + svg #heart {
  animation: 0.8s draw-heart linear forwards;
}

@keyframes draw-heart {
  0% {
    stroke-dashoffset: 1600;
  }
  80% {
    stroke-dashoffset: 0;
    fill: #eee;
  }
  100% {
    fill: red;
    stroke-dashoffset: 0;
  }
}

input[type="checkbox"]:checked ~ .heart-outside {
  animation: .5s blink ease-in-out forwards .85s;
}

input[type="checkbox"]:checked ~ .heart-outside::after {
  animation: .5s blink-after ease-in-out forwards .85s;
}

@keyframes blink {
  0% {
    transform: scale(0.5);
    opacity: .8;
  }
  50% {
    transform: scale(1);
    opacity: .8;
  }
  100% {
    transform: scale(1.1);
    opacity: 0;
  }
}

@keyframes blink-after {
  0% {
    transform: rotate(45deg) scale(0.5);
    opacity: .8;
  }
  50% {
    transform: rotate(45deg) scale(1);
    opacity: .8;
  }
  100% {
    transform: rotate(45deg) scale(1.1);
    opacity: 0;
  }
}

input[type="checkbox"]:checked + .heart-svg {
  animation: 0.8s heart-pop linear forwards;
}

@keyframes heart-pop {
  0% {
    transform: scale(1);
  }
  70% {
    transform: scale(1);
  }
  80% {
    transform: scale(1.2);
  }
  100% {
    transform: scale(1);
  }
}

.main-like-num {
  color: black;
  font-family: 微软雅黑;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.post-comment-box {
  background:#E6E6E6;
  border-radius:4px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: -10px 250px 20px 250px;
  padding: 0px 0px 20px 0px;
  position: relative;
}

.el-textarea /deep/ .el-textarea__inner:focus {
  border-color:#17181A;
}

.my-user-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-end;
  margin-top: 20px;
  padding: 0;
  position: relative;
}

.my-avatar {
  margin: 0px 20px 0px 20px;
  position: relative;
}

.my-username-link {
  color: black;
  font-family: 微软雅黑;
  margin: 0px 0px 10px 0px;
  padding: 0;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.my-username-link:hover {
  color: #909399;
}

.input-button-box {
  display: flex;
  flex-direction: row-reverse;
  justify-content: flex-start;
  margin-left: auto;
  margin-right: 20px;
  position: relative;
}

.comment-list {
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: 0px 250px 0px 250px;
  padding: 0px 0px 20px 0px;
  position: relative;
}

.comment-box {
  background:#E6E6E6;
  border-radius:4px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: 15px 0px 15px 0px;
  padding: 0px 0px 20px 0px;
  position: relative;
}

.comment-content {
  background: white;
  border-radius:4px;
  font-family: 微软雅黑;
  font-size: 1rem;
  margin: 0;
  padding: 30px 20px 30px 20px;
  position: relative;
}

.comment-user-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: flex-end;
  margin-top: 20px;
  padding: 0;
  position: relative;
}

.comment-avatar {
  margin: 0px 20px 0px 20px;
  position: relative;
}

.comment-user-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0;
  position: relative;
}

.comment-username-link {
  color: black;
  font-family: 微软雅黑;
  margin: 0px 0px 5px 0px;
  padding: 0;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.comment-username-link:hover {
  color: #909399;
}

.comment-time {
  font-family: 微软雅黑;
  margin: 0;
  padding: 0;
  font-size: 10px;
  font-weight: 100;
  letter-spacing: +0.05rem;
  position: relative;
}

.comment-like-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: auto;
  margin-right: 20px;
  transform: scale(0.9);
}

#commentheart {
  fill: white;
  stroke-width: 20px;
  stroke: red;
  stroke-dasharray: 1600;
  stroke-dashoffset: 1600;
  stroke-linecap: round;
}

input[type="checkbox"]:checked + svg #commentheart {
  animation: 0.8s draw-commentheart linear forwards;
}

@keyframes draw-commentheart {
  0% {
    stroke-dashoffset: 1600;
  }
  80% {
    stroke-dashoffset: 0;
    fill: white;
  }
  100% {
    fill: red;
    stroke-dashoffset: 0;
  }
}

.comment-like-num {
  color: black;
  font-family: 微软雅黑;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  margin-left: 20px;
  position: relative;
}

</style>