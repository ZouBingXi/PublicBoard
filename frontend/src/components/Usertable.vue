<template>
  <div>

      <el-table :data="table.data" style="width: 100%;min-width: 800px"  stripe>
        <el-table-column v-for="item in table.tableColumn"
                         :prop="item.prop"
                         :label="item.label"
                         :min-width="item.minWidth"
                         align="center"
                         show-overflow-tooltip
                         sortable  />

        <el-table-column label="状态" min-width="60px">
          <template slot-scope="scope">
            <span :style="{ color: scope.row.banstate==='封禁状态'? '#ED3F14' : 'green' }">
              {{scope.row.banstate}}
            </span>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="250px" align="left">
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
      this.$axios.get("/user/admin/checkuser?size="+this.pagesize+"&page="+this.currentpage)
          .then((response)=>{
            this.table.data=response.data.data.list;
            this.total=response.data.data.total;
            console.log('请求用户表成功');
            console.log(response);
          })
          .catch(()=>{console.log("请求用户表失败")})
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
    {prop:"userid",label:"账号",minWidth:""},
    {prop:"username",label:"用户名",minWidth:""},
    {prop:"usertype",label:"用户类型",minWidth:""},
    {prop:"logintime",label:"登陆时间",minWidth:"110px"}
  ],
  data:[],
}
</script>

<style scoped>

</style>