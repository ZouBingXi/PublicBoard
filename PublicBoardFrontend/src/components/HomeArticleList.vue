<template>
  <div class="articleList">
    <div v-if="articles.length!=0">
      <ArticlePreView
          v-for="(item,index) in articles"
          :username="item.username"
          :headimage="item.headimage"
          :title="item.newstitle"
          :content="item.content"
          :type='item.newstypename'
          :userid="item.userid"
          :articleid="item.newsid"
          :image="item.imgUrls"
          :likenum="item.likenum"
          :viewnum="item.viewnum"
          :sendtime="item.sendtime"
          :key="index"
      >
      </ArticlePreView>
    </div>
    <div v-else style="width: 100%;text-align: center;margin-top: 20px">
      暂无帖子
    </div>

    <div style="text-align: center;margin: 10px">
      <el-pagination
          v-model:current-page="pageNum"
          v-model:page-sizes="pagesizes"
          v-model:page-size="pageSize"
          v-model:total="total"
          layout="total, sizes, prev, pager, next, jumper"
          pager-count="5"
          background
          hide-on-single-page>
      </el-pagination>
    </div>


  </div>
</template>

<script>
import ArticlePreView from "./ArticlePreView";

export default {
  name: "ArticleList",
  props:{
    search:{
      type:String,
      default:''
    },
    sort: {
      default:null
    },
    type:{
      default:null
    },
  },
  components:{
    ArticlePreView
  },
  data(){
    return{
      pageNum:1,
      pageSize:5,
      pagesizes:[1,5, 10, 20, 50],
      total:1,
      articles:[],
    }
  },
  methods:{
    getlist(){
      var body={
        key:this.search,
        sort:this.sort,
        pageSize:this.pageSize,
        pageNum:this.pageNum,
      }
      if(this.type!=''){
        body.type=this.type;
      }
      console.log("搜索发送结果",body,"this.search:",this.search,"this.sort:",this.sort,"this.type:",(this.type || ''));
      this.$axios.post('/news/search',body).then(({data})=>{
        if(data.code==200){
          console.log(data)
          this.articles=data.data.list;
          this.total=data.data.total;
          console.log(this.articles);
        }
        else{
          this.articles=[];
          this.total=0;
        }

      })
    },
  },
  watch:{
    pageNum(){
      this.getlist()
    },
    pageSize(){
      this.getlist()
    },
    search(){
      this.getlist()
    },
    sort(){
      this.getlist()
    },
    type(){
      this.getlist()
    }
  },
  created() {
    this.getlist();
  }
}
</script>

<style scoped>
.articleList{
  width: 700px;
}
</style>