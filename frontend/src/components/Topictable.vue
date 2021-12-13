<template>
  <div>

    <el-table :data="table.data" style="width: 100%; min-width: 1000px" stripe>
      <el-table-column v-for="item in table.tableColumn"
                       :prop="item.prop"
                       :label="item.label"
                       :min-width="item.minWidth"
                       align="center"
                       show-overflow-tooltip
                       sortable  />

      <el-table-column fixed="right" label="操作" align="left" width="250px">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" />
        </template>
        <template>
          <el-button size="mini" >查看</el-button>
          <el-button size="mini" type="danger">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="block" style="text-align: center;margin: 10px">
      <el-pagination
          :current-page.sync="currentpage"
          :page-sizes.sync="pagesizes"
          :page-size.sync="pagesize"
          :total.sync="total"
          layout="total, sizes, prev, pager, next, jumper"
          background>
      </el-pagination>
    </div>

    <!--el-button @click="gettable">请求表</el-button>
    <el-button @click="test">测试</el-button-->
  </div>
</template>

<script>
export default {
  name: "Usertable",
  data(){
    return{
      search:'',
      pagesizes:[10, 20, 50, 100],
      currentpage:1,
      pagesize:10,
      total:1,
      table,
    };
  },
  methods:{
    gettable(){
      this.$axios.get("/subject/admin/checksubject?page="+this.pagesize+"&page="+this.currentpage)
          .then((response)=>{
            this.table.data=response.data.data.list;
            this.total=response.data.data.total;
            console.log('请求话题表成功');
            console.log(response);
          })
          .catch(()=>{console.log("请求话题表失败")})
    }
  },
  created:function (){
    this.gettable();
  },
  watch:{
    currentpage(){
      this.gettable();
    },
    pagesize(){
      this.gettable();
    }
  }
}

const table={
  tableColumn:[
    {prop:"subjecttitle",label:"话题标题",minWidth:"150px"},
    {prop:"subjecttypename",label:"类型",minWidth:"50px"},
    {prop:"userid",label:"作者账号",minWidth:"120px"},
    {prop:"sendtime",label:"发帖时间"},
    {prop:"viewnum",label:"浏览量",minWidth:"60px"},
    {prop:"likenum",label:"点赞量",minWidth:"60px"},
  ],
  data:[],
}
</script>

<style scoped>

</style>