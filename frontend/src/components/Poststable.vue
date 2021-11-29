<template>
  <div>
    <template>
      <el-table :data="table.data" style="width: 100%;min-width: 1100px"  stripe>
        <el-table-column v-for="item in table.table_colum" :prop="item.prop" :label="item.label" sortable  />
        <el-table-column fixed="right" label="操作" min-width="150px" align="right">
          <template #header>
            <el-input v-model="search" size="mini" placeholder="Type to search" />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" @click="checkmore(scope.row)">查看</el-button>
            <el-button size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <div class="block" style="text-align: center;margin: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="table.currentpage"
          :page-sizes="table.pagesizes"
          :page-size="table.pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="table.total"
          background>
      </el-pagination>
    </div>
    <!--el-button @click="gettable">请求表</el-button>
    <el-button @click="test">测试</el-button-->


    <el-dialog
        :title="'标题:  '+title"
        :visible.sync="dialogVisible">
      <h4>内容：</h4>
      <span>{{ content }}</span>
    </el-dialog>


  </div>
</template>

<script>

export default {
  name: "Poststable",
  data(){
    return{
      search:'',
      table,
      dialogVisible: false,
      title:'',
      content:'',
    };
  },
  methods:{
    gettable(){
      this.$axios.get("/news/admin/checknews?page="+this.table.currentpage+"&size="+this.table.pagesize)
          .then((response)=>{
            this.table.data=response.data.data.list;
            this.table.total=response.data.data.total;
            console.log(response);
          })
          .catch(()=>{console.log("失败")})
    },
    handleSizeChange(size){
      this.table.pagesize=size;
      this.gettable();
    },
    handleCurrentChange(currentpage){
      this.table.currentpage=currentpage;
      this.gettable();
    },
    checkmore(row){
      this.dialogVisible=true;
      this.title=row.newstitle;
      this.content=row.content;
    }
  },
  created:function (){
    this.gettable();
  },
}

const table={
  table_colum:[
    {prop:"newsid",label:"帖子编号"},
    {prop:"userid",label:"作者账号"},
    {prop:"typename",label:"帖子类型"},
    {prop:"newstitle",label:"帖子标题"},
    {prop:"sendtime",label:"发帖时间"},
    {prop:"viewnum",label:"浏览量"},
    {prop:"likenum",label:"点赞量"},
  ],
  data:[
    {newsid:123456, userid:123456, newstype:123456, newstitle:123456, sendtime:123465, viewnum:123456, likenum:123456,content:"asdfsfgdfgdfagddfsdfgffdssafgdfadgssadfafgfdsfdasdfagggggggafgafgasgsagfsag"},
  ],
  pagesizes:[10, 20, 50, 100],
  currentpage:1,
  pagesize:10,
  total:1,

}
</script>

<style scoped>

</style>