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
        <el-avatar class="main-avatar" :size="50" :src="description.mainavatarUrl" @error="errorHandler">
          <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
        </el-avatar>

        <!-- 作者信息 -->
        <div class="main-user-info">
          <el-link class="main-username-link" type="primary" :underline="false">{{ description.username }}</el-link>
          <p class="article-time">{{ description.sendtime }}</p>
        </div>

        <!-- 其他信息 -->
        <div class="main-other-info">
          <!-- 标签 -->
          <div>
<!--            <el-tag class="main-tag" type="info" effect="dark">{{ description.type }}</el-tag>-->
            <el-tag class="child-tag" type="info">{{ description.newstype }}</el-tag>
          </div>

          <!-- 点赞/浏览 -->
          <div class="like-view-box">
            <!-- 点赞 -->
            <div class="like-box">
              <label class="heart-label" @change="change_main_likenum">
                <input type="checkbox" v-model="description.main_checklike">
                <svg class="heart-svg" viewBox="0 0 532 532">
                  <path id="heart" transform="translate(10, 10)" d="M256,96.5l-19.7-20.3C186.1,24.3,104.5,15.9,49.7,62.6c-62.8,53.6-66.1,149.8-9.9,207.9l193.5,199.8 c12.5,12.9,32.8,12.9,45.3,0l193.5-199.8c56.3-58.1,53-154.3-9.8-207.9l0,0C407.5,15.9,326,24.3,275.7,76.2L256,96.5z"/>
                </svg>
                <span class="heart-outside"></span>
              </label>

              <p class="main-like-num">{{ description.main_likenum }}</p>
            </div>

            <!-- 浏览 -->
            <div class="view-box">
              <svg class="eye-svg" viewBox="0 0 576 512">
                <path fill="#eee" d="M288 144a110.94 110.94 0 0 0-31.24 5 55.4 55.4 0 0 1 7.24 27 56 56 0 0 1-56 56 55.4 55.4 0 0 1-27-7.24A111.71 111.71 0 1 0 288 144zm284.52 97.4C518.29 135.59 410.93 64 288 64S57.68 135.64 3.48 241.41a32.35 32.35 0 0 0 0 29.19C57.71 376.41 165.07 448 288 448s230.32-71.64 284.52-177.41a32.35 32.35 0 0 0 0-29.19zM288 400c-98.65 0-189.09-55-237.93-144C98.91 167 189.34 112 288 112s189.09 55 237.93 144C477.1 345 386.66 400 288 400z"></path>
              </svg>

              <p class="main-view-num">{{ description.main_viewnum }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- 文章本体 -->
    <div class="article-box">
      <!-- 文章内容 -->
      <pre class="article-content">{{ description.content }}</pre>

      <!-- 配图 -->
      <div class="images-list" v-show="imgList.length > 0">
        <el-image
            style="width: 200px; height: 200px; margin-right: 20px; margin-bottom: 20px; box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);"
            v-for="(item, index) in imgList"
            :key="index"
            :src="item"
            fit="cover"
            :preview-src-list="imgList">
          <template v-slot:error class="image-slot">
<!--            <i style="font-size:40px" class="el-icon-picture-outline"></i>-->
            <el-skeleton style="width: 200px">
              <template v-slot:template>
                <el-skeleton-item variant="image" style="width: 200px; height: 200px;" />
              </template>
            </el-skeleton>
          </template>
          <template v-slot:placeholder class="image-slot">
<!--            加载中<span class="dot">...</span>-->
            <el-skeleton style="width: 200px" animated>
              <template v-slot:template>
                <el-skeleton-item variant="image" style="width: 200px; height: 200px;" />
              </template>
            </el-skeleton>
          </template>
        </el-image>
      </div>

      <!-- 分割线 -->
      <div style="margin: 0px 120px 0px 120px">
        <el-divider></el-divider>
      </div>
    </div>

    <!-- 发表评论窗口 -->
    <div class="post-comment-box">
      <!-- 输入框 -->
      <div class="input-comment-box">
        <el-form :model="myuser" :rules="rules" ref="myuser" label-width="0" hide-required-asterisk>
          <el-form-item label="" prop="comment_content">
            <el-input type="textarea" :autosize="{ minRows: 5 }" v-model="myuser.comment_content" placeholder="发表评论..." maxlength="200" show-word-limit></el-input>
          </el-form-item>
        </el-form>
<!--        <el-input type="textarea" :autosize="{ minRows: 5 }" v-model="myuser.content" placeholder="发表评论..." maxlength="200" show-word-limit></el-input>-->
      </div>

      <!-- 其他内容 -->
      <div class="my-user-box">
        <!-- 用户头像 -->
        <el-avatar class="my-avatar" :size="40" :src="myuser.myavatarUrl" @error="errorHandler">
          <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
        </el-avatar>

        <!-- 用户信息 -->
        <el-link class="my-username-link" type="primary" :underline="false">{{ myuser.username }}</el-link>

        <!-- 发表评论按钮 -->
        <div class="input-button-box">
          <el-button type="info" size="medium" @click="submitcomment('myuser')">发表评论</el-button>
        </div>
      </div>
    </div>

    <!-- 评论区 -->
    <div class="comment-list">
      <!-- 评论窗口 -->
      <div class="comment-box" v-for="(item, index) in comment" :key="index">
        <!-- 其他内容 -->
        <div class="comment-user-box">
          <!-- 评论者头像 -->
          <el-avatar class="comment-avatar" :size="40" :src="item.headImage" @error="errorHandler">
            <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
          </el-avatar>

          <!-- 评论者名字 -->
          <el-link class="comment-username-link" type="primary" :underline="false">{{ item.username }}</el-link>

          <!-- 评论时间 -->
          <p class="comment-time">{{ item.commenttime }}</p>
        </div>

        <!-- 评论内容 -->
        <pre class="comment-content">{{ item.content }}</pre>

        <!-- 回复信息 -->
        <div class="reply-info-box">
          <!-- 回复量 -->
          <div class="comment-svg-box">
            <svg class="comment-svg" viewBox="0 0 512 512" @click="item.showreply = !item.showreply">
              <path fill="#909399" d="M144 208c-17.7 0-32 14.3-32 32s14.3 32 32 32 32-14.3 32-32-14.3-32-32-32zm112 0c-17.7 0-32 14.3-32 32s14.3 32 32 32 32-14.3 32-32-14.3-32-32-32zm112 0c-17.7 0-32 14.3-32 32s14.3 32 32 32 32-14.3 32-32-14.3-32-32-32zM256 32C114.6 32 0 125.1 0 240c0 47.6 19.9 91.2 52.9 126.3C38 405.7 7 439.1 6.5 439.5c-6.6 7-8.4 17.2-4.6 26S14.4 480 24 480c61.5 0 110-25.7 139.1-46.3C192 442.8 223.2 448 256 448c141.4 0 256-93.1 256-208S397.4 32 256 32zm0 368c-26.7 0-53.1-4.1-78.4-12.1l-22.7-7.2-19.5 13.8c-14.3 10.1-33.9 21.4-57.5 29 7.3-12.1 14.4-25.7 19.9-40.2l10.6-28.1-20.6-21.8C69.7 314.1 48 282.2 48 240c0-88.2 93.3-160 208-160s208 71.8 208 160-93.3 160-208 160z"></path>
            </svg>

            <p class="comment-reply-num">{{ item.replynum }}</p>
          </div>

          <!-- 回复按钮 -->
          <div class="reply-button-box">
            <el-button type="info" size="mini" @click="submitreply(item.commentid)">回复</el-button>
          </div>
        </div>

<!--        &lt;!&ndash; 回复输入框 &ndash;&gt;-->
<!--        <el-dialog title="发表回复" :visible.sync="dialogFormVisible" width="30%">-->
<!--          <el-form :model="ruleForm" :rules="rules" ref="myForm" label-width="0" hide-required-asterisk>-->
<!--            <el-form-item label="" prop="content">-->
<!--              <el-input type="textarea" :autosize="{ minRows: 5 }" v-model="ruleForm.content" placeholder="发表回复..." maxlength="200" show-word-limit></el-input>-->
<!--            </el-form-item>-->
<!--          </el-form>-->
<!--          <div slot="footer" class="dialog-footer">-->
<!--            <el-button class="cancel-button" type="info" plain @click="cancelreply('ruleForm')">取 消</el-button>-->
<!--            <el-button type="info" @click="submitreply(index)">确 定</el-button>-->
<!--          </div>-->
<!--        </el-dialog>-->

        <!-- 回复区 -->
        <el-collapse-transition>
          <div class="reply-list" v-show="item.replynum > 0 && item.showreply == true">
            <!-- 回复窗口 -->
            <div class="reply-box" v-for="(reply_item, reply_index) in item.replys" :key="reply_index" v-show="reply_item.targetid == 0">
              <!-- 其他内容 -->
              <div class="reply-user-box">
                <!-- 回复者头像 -->
                <el-avatar class="reply-avatar" :size="30" :src="reply_item.headImage" @error="errorHandler">
                  <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                </el-avatar>

                <!-- 回复者名字 -->
                <el-link class="reply-username-link" type="primary" :underline="false">{{ reply_item.username }}</el-link>

                <!-- 回复时间 -->
                <p class="reply-time">{{ reply_item.commenttime }}</p>
              </div>

              <!-- 回复内容 -->
              <pre class="reply-content">{{ reply_item.content }}</pre>
            </div>
          </div>
        </el-collapse-transition>
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
        username: '',
        title: '',
        mainavatarUrl: '',
        // type: '帖子',
        newstype: '校园卡丢失',
        // subjecttype: '避雷',
        content: "",
        sendtime: '',
        main_viewnum: 0,
        main_checklike: false,
        main_likenum: 0
      },
      imgList: [],
      myuser: {
        // userid: 1111111111,
        username: '',
        myavatarUrl: '',
        comment_content: ''
      },
      comment: [
        {
          commentid: 1,
          userid: 1111111111,
          username: '',
          headImage: '',
          commenttime: '',
          content: '',
          replynum: 2,
          showreply: false,
          replys: [
            {
              replyid: 1,
              targetid: 0,
              commentid: 1,
              userid: 1111111111,
              username: '',
              headImage: '',
              commenttime: '',
              content: ''
            }
          ]
        }
      ],
      rules: {
        content: [
          {required: true, message: '请输入回复内容', trigger: 'blur'},
          {min: 5, max: 200, message: '回复内容长度在 5 到 200 个字符', trigger: 'blur'}
        ]
      }
    }
  },
  methods: {
    errorHandler() {
      return true;
    },
    submitcomment(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          this.$axios.post('/news/comment', {
            newsid: _this.article_id,
            content: _this.myuser.comment_content,
          }).then((response) => {
            console.log(response);
            this.$refs[formName].resetFields();
            if(response.data.code == 200) {
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'success'
              });
              _this.dialogFormVisible = false;
              this.getArticle();
            }
            else {
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'error'
              });
            }
          });
        } else {
          this.$message({
            showClose: true,
            message: '请正确填写评论',
            type: 'error'
          });
          return false;
        }
      });
    },
    submitreply(cid) {
     /* let _this = this;*//*报错，删掉了*/

      this.$prompt('', '回复', {
        inputType: 'textarea',
        inputPlaceholder: '发表回复...',
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        inputValidator: (value) => {
          if(!value) {
            return '回复内容不能为空';
          } else {
            if(value.length < 5 || value.length > 200) {
              return '回复内容长度在 5 到 200 个字符'
            }
          }
        },
        inputErrorMessage: '回复内容长度在 5 到 200 个字符'
      }).then(({ value }) => {
        this.$axios.post('/news/reply', {
          commentid: cid,
          content: value
        }).then((response) => {
          console.log(response);
          if(response.data.code == 200) {
            this.$message({
              showClose: true,
              message: response.data.msg,
              type: 'success'
            });
            this.getArticle();
          }
          else {
            this.$message({
              showClose: true,
              message: response.data.msg,
              type: 'error'
            });
          }
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消输入'
        });
      });
    },
    getArticle() {
      let _this = this;
      this.myuser.username = this.currentUser.username;
      this.myuser.myavatarUrl = this.currentUser.headimage;
      // this.description.type = this.article_type;

      this.$axios.get('/news/view?newsid=' + this.article_id)
          .then((response) => {
            console.log(response);
            _this.description.anonymousstate = response.data.data.anonymousstate;
            _this.description.userid = response.data.data.userid;
            _this.description.username = response.data.data.username;
            _this.description.mainavatarUrl = response.data.data.headimage;
            _this.description.title = response.data.data.newstitle;
            _this.description.content = response.data.data.content;
            _this.description.sendtime = response.data.data.sendtime;
            _this.description.main_viewnum = response.data.data.viewnum;
            _this.description.main_checklike = response.data.data.isLike;
            _this.description.main_likenum = response.data.data.likenum;
            _this.description.newstype = response.data.data.newstypename;
            _this.imgList = response.data.data.imgUrls;
            _this.comment = response.data.data.comments;
            console.log(_this.comment);
          }).catch(()=>{console.log("失败")});
    },
    change_main_likenum() {
      // if(this.description.main_checklike) {
      //   this.description.main_likenum ++;
      // } else {
      //   this.description.main_likenum --;
      // }

      let _this = this;
      let likestate = 0;
      this.$axios.get('/news/likenews?newsid=' + this.article_id)
          .then((response) => {
            console.log('点赞测试')
            console.log(response);
            _this.description.main_likenum = response.data.data[0];
            likestate = response.data.data[1];
            if(likestate == 0) {
              _this.description.main_checklike = false;
            } else {
              _this.description.main_checklike = true;
            }
          }).catch(()=>{console.log("失败")});
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
  margin: 0px;
  padding: 30px;
  /*max-height: 300px;*/
  box-shadow: 0px 2px 10px -2px rgba(0,0,0,0.75) inset, 0px -2px 10px -2px rgba(0,0,0,0.75) inset;
}

h1 {
  color: white;
  font-family: 微软雅黑;
  margin: 0px 120px 0px 120px;
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
  margin: 20px 120px 0px 120px;
  padding: 0;
  position: relative;
}

.main-avatar {
  margin: 0px 20px 10px 0px;
  position: relative;
}

.main-user-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0;
  margin-bottom: 7px;
  position: relative;
}

.main-username-link {
  color: white;
  font-family: 微软雅黑;
  margin: 0px 0px 10px 0px;
  padding: 0;
  font-size: 18px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.main-username-link:hover {
  color: #909399;
}

.article-time {
  color: #909399;
  font-family: 微软雅黑;
  margin: 0;
  padding: 0;
  font-size: 14px;
  font-weight: 400;
  letter-spacing: +0.05rem;
  position: relative;
}

.main-other-info {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0;
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

.like-view-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0px 10px 0px 20px;
  position: relative;
}

.like-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 15px 20px 0px 0px;
  position: relative;
}

.heart-svg {
  width: 20px;
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
  width: 4px;
  height: 4px;
  margin-bottom: 5px;
  background-color: transparent;
  border-radius: 50%;
  position: absolute;
  transform: scale(0);

  box-shadow: 0 -20px 0 red, 0 20px 0 red, -20px 0 0 red, 20px 0 0 red;
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
  color: white;
  font-family: 微软雅黑;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  margin: 0px 0px 0px 10px;
  position: relative;
}

.view-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 15px 20px 0px 0px;
}

.eye-svg {
  width: 23px;
  position: relative;
  z-index: 10;
}

.main-view-num {
  color: white;
  font-family: 微软雅黑;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  margin: 0px 0px 0px 10px;
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
  margin: 0px 120px 0px 120px;
  padding: 0;
  font-size: 1.5rem;
  /*letter-spacing: +0.15rem;*/
  position: relative;
}

pre {
  white-space: pre-wrap;           /* css-3 */
  white-space: -moz-pre-wrap;      /* Mozilla, since 1999 */
  white-space: -pre-wrap;          /* Opera 4-6 */
  white-space: -o-pre-wrap;        /* Opera 7 */
  word-wrap: break-word;           /* Internet Explorer 5.5+ */
}

.images-list {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin: 40px 120px 0px 120px;
  padding: 0;
  position: relative;
}

.image-slot {
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%,-50%);
}

.post-comment-box {
  background: white;
  /*border: 1px solid #DCDFE6;*/
  /*box-sizing: border-box;*/
  border-radius:4px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: -10px 250px 20px 250px;
  padding: 0px 0px 15px 0px;
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
  margin-top: 15px;
  padding: 0;
  position: relative;
}

.my-avatar {
  margin: 0px 15px 0px 15px;
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
  margin-right: 15px;
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
  background: white;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  border-radius:4px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: 15px 0px 15px 0px;
  padding: 0;
  position: relative;
}

.comment-user-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin: 15px 15px 15px 15px;
  padding: 0;
  position: relative;
}

.comment-avatar {
  margin: 0px 15px 0px 0px;
  position: relative;
}

.comment-username-link {
  color: black;
  font-family: 微软雅黑;
  margin: 0px 0px 0px 0px;
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
  color: #909399;
  font-family: 微软雅黑;
  margin: 0px 15px 0px 15px;
  padding: 0;
  font-size: 10px;
  font-weight: 400;
  letter-spacing: +0.05rem;
  position: relative;
}

.comment-content {
  background: white;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  border-radius:4px;
  font-family: 微软雅黑;
  font-size: 1rem;
  margin: -1px;
  padding: 20px 20px 20px 20px;
  position: relative;
}

.reply-info-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin: 15px 15px 15px 15px;
  padding: 0;
  position: relative;
}

.comment-svg-box {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0;
  position: relative;
}

.comment-svg {
  cursor: pointer;
  width: 20px;
  position: relative;
  z-index: 10;
}

.comment-reply-num {
  color: #909399;
  font-family: 微软雅黑;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  margin: 0px 0px 0px 10px;
  position: relative;
}

.reply-button-box {
  display: flex;
  flex-direction: row-reverse;
  justify-content: flex-start;
  margin-left: auto;
  position: relative;
}

.reply-list {
  background: white;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  border-radius:4px;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: -1px;
  padding: 0px 0px 0px 80px;
  position: relative;
}

.reply-box {
  background: white;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  border-radius:4px;
  /*box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);*/
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  /*align-items: center;*/
  margin: -1px;
  padding: 0;
  position: relative;
}

.reply-user-box {
  display: flex;
  flex-wrap: wrap;
  justify-content: flex-start;
  align-items: center;
  margin: 15px 15px 15px 15px;
  padding: 0;
  position: relative;
}

.reply-avatar {
  margin: 0px 15px 0px 0px;
  position: relative;
}

.reply-username-link {
  color: black;
  font-family: 微软雅黑;
  margin: 0px 0px 0px 0px;
  padding: 0;
  font-size: 15px;
  font-weight: 600;
  letter-spacing: +0.05rem;
  position: relative;
}

.reply-username-link:hover {
  color: #909399;
}

.reply-time {
  color: #909399;
  font-family: 微软雅黑;
  margin: 0px 15px 0px 15px;
  padding: 0;
  font-size: 10px;
  font-weight: 400;
  letter-spacing: +0.05rem;
  position: relative;
}

.reply-content {
  background: white;
  border: 1px solid #DCDFE6;
  box-sizing: border-box;
  border-radius:4px;
  font-family: 微软雅黑;
  font-size: 1rem;
  margin: -1px;
  padding: 20px 20px 20px 20px;
  position: relative;
}

.cancel-button {
  /*background-color: white;*/
}

</style>