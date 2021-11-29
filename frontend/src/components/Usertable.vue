<template>
  <div>
    <template>
      <el-table :data="table.data" style="width: 100%;min-width: 800px"  stripe>
        <el-table-column v-for="item in table.table_colum" :prop="item.prop" :label="item.label" sortable  />

        <el-table-column fixed="right" label="操作" min-width="150px" align="right">
          <template #header>
            <el-input v-model="search" size="mini" placeholder="Type to search" />
          </template>
          <template>
            <el-button size="mini" >查看</el-button>
            <el-button size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>

        <el-table-column label="状态">
          <template slot-scope="scope">
            <span :style="{ color: scope.row.banstate === '正常状态' ? 'green' : '#ED3F14' }">{{scope.row.banstate}}</span>
          </template>
        </el-table-column>

      </el-table>
    </template>

    <div class="block" style="text-align: center;margin: 10px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          v-model="test"
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
  </div>
</template>

<script>
export default {
  name: "Usertable",
  data(){
    return{
      search:'',
      table,
    };
  },
  methods:{
    gettable(){
      this.$axios.get("/user/admin/checkuser?size="+this.table.pagesize+"&page="+this.table.currentpage)
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
    setcell({ row, column, rowIndex, columnIndex }){
      if (row.banstate==="封禁状态")
        return 'color:red;';
      else if(row.banstate==="正常状态")
        return 'color:green;';
          }
  },
  created:function (){
    this.gettable();
  },
  computed:{
  }
}

const table={
  table_colum:[
    {prop:"userid",label:"账号"},
    {prop:"username",label:"用户名"},
    {prop:"usertype",label:"用户类型"},
    {prop:"logintime",label:"登陆时间"}
  ],
  data:[
    {userid:123456, username:123456, banstate:"封禁状态", usertype:123456, logintime:123465},
    {userid:123456, username:123456, banstate:"正常状态", usertype:123456, logintime:123465}
  ],
  pagesizes:[10, 20, 50, 100],
  currentpage:1,
  pagesize:10,
  total:1,

}
</script>

<style scoped>

</style>