<template>
  <div>
    <el-menu mode="horizontal" :default-active="type"  active-text-color="black" @select="changetype">
      <el-menu-item index="1">避雷</el-menu-item>
      <el-menu-item index="2">树洞</el-menu-item>
      <el-menu-item index="3">指南</el-menu-item>
      <el-menu-item index="4">课程</el-menu-item>
    </el-menu>
    <article-pre-view
        v-for="item in articles"
        :username="item.username"
        :headimage="item.headimage"
        :title="item.subjecttitle"
        :content="item.content"
        type='话题'
        :userid="item.userid"
        :articleid="item.subjectid"
        :image="item.imgUrls"
    >
    </article-pre-view>

    <div style="text-align: center;margin: 10px">
      <el-pagination
          :current-page.sync="currentpage"
          :page-sizes.sync="pagesizes"
          :page-size.sync="pagesize"
          :total.sync="total"
          layout="total, sizes, prev, pager, next, jumper"
          hide-on-single-page>
      </el-pagination>
    </div>
  </div>

</template>

<script>
import ArticlePreView from "@/components/ArticlePreView";

export default {
  name: "NewsList",
  components:{
    ArticlePreView
  },
  data(){
    return{
      pagesizes:[10, 20, 50, 100],
      currentpage:1,
      pagesize:10,
      total:1,
      type:'1',
      articles:[],
    }
  },
  methods:{
    getlist(){
      this.$axios.get("/subject/viewDiffSubject?typeid="+this.type+"&page="+this.currentpage+"&size="+this.pagesize)
          .then(({data})=>{
            this.articles=data.data.list;
            this.total=data.data.total;
            console.log('获取文章列表成功');
            console.log(data);
          })
          .catch(()=>{console.log("请求帖子表失败");this.articles=[]})
    },
    changetype(index){
      this.type=index;
    },
  },
  watch:{
    currentpage(){
      this.getlist();
    },
    pagesize(){
      this.getlist();
    },
    type(){
      this.getlist();
    }
  },
  created() {
    this.getlist();
  }
}
</script>

<style scoped>

</style>
<!--
[
{
"newsid": 1,
"userid": 2019010101,
"username": "test-user",
"headimage": "http://120.78.10.173:9000/test/avatar/05fe71557e164b32b503708271365dfd.png",
"newstitle": "a测试发帖",
"content": null,
"sendtime": "2021-11-26",
"viewnum": 0,
"likenum": 0,
"newstypename": "校园卡丢失",
"anonymousstate": false,
"topstate": false,
"hotstate": false,
"imgUrls": [
"http://120.78.10.173:9000/test/news/43109dd1222f4024b2418461a7b34c6d.jpg",
"http://120.78.10.173:9000/test/news/9781c78417c0480ca1b2496e57cac03c.jpg",
"http://120.78.10.173:9000/test/news/cede03b1bc80418397172b2d680d5292.jpg",
"http://120.78.10.173:9000/test/news/546153285bcc489683373b51a92aa0cb.jpg",
"http://120.78.10.173:9000/test/news/ff2ecff10d1142b2beda6d9b15fbf765.jpg"
]
},
{
"newsid": 3,
"userid": 2019010101,
"username": "test-user",
"headimage": "http://120.78.10.173:9000/test/avatar/05fe71557e164b32b503708271365dfd.png",
"newstitle": "图片帖子测试",
"content": null,
"sendtime": "2021-12-13",
"viewnum": 7,
"likenum": 0,
"newstypename": "校园卡丢失",
"anonymousstate": false,
"topstate": false,
"hotstate": false,
"imgUrls": [
"http://120.78.10.173:9000/test/news/43109dd1222f4024b2418461a7b34c6d.jpg",
"http://120.78.10.173:9000/test/news/9781c78417c0480ca1b2496e57cac03c.jpg",
"http://120.78.10.173:9000/test/news/cede03b1bc80418397172b2d680d5292.jpg",
"http://120.78.10.173:9000/test/news/546153285bcc489683373b51a92aa0cb.jpg",
"http://120.78.10.173:9000/test/news/ff2ecff10d1142b2beda6d9b15fbf765.jpg"
]
}
]-->
