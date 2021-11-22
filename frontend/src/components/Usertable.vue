<template>
  <div>
    <template>
      <el-table :data="table" style="width: 100%;min-width: 800px" stripe>
        <el-table-column prop="userid" label="账号" sortable  />
        <el-table-column prop="username" label="用户名"sortable/>
        <el-table-column prop="banstate" label="禁用状态" sortable/>
        <el-table-column prop="usertype" label="用户类型"sortable />
        <el-table-column prop="logintime" label="登陆时间"sortable />
        <el-table-column
            fixed="right"
            label="操作"
            min-width="150px"
            align="right">
          <template #header>
            <el-input v-model="search" size="mini" placeholder="Type to search" />
          </template>
          <template slot-scope="scope">
            <el-button size="mini" >查看</el-button>
            <el-button size="mini" type="danger">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>

    <div class="block" style="text-align: center">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentpage"
          :page-sizes="pagesizes"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
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
      totalpage:1,
      table:[
        {
          userid:123456,
          username:123456,
          banstate:123456,
          usertype:123456,
          logintime:123465
        },
      ],
    };
  },
  methods:{
    test(){
      console.log(this.pagesize);
      console.log(this.$route.path);
    },
    gettable(){
      console.log("/user/admin/checkuser?size="+this.pagesize+"&page="+this.currentpage);
      this.$axios.get("/user/admin/checkuser?size="+this.pagesize+"&page="+this.currentpage)
          .then((response)=>{
            this.table=response.data.data.list;
            this.totalpage=response.data.data.pages;
            console.log(response);
          })
          .catch(()=>{console.log("失败")})
    },
    handleSizeChange(size){
      this.pagesize=size;
      this.gettable();
    },
    handleCurrentChange(currentpage){
      this.currentpage=currentpage;
      this.gettable();
    }
  },
  created:function (){
    this.gettable();
  },
  computed:{
    total(){
      return this.totalpage*this.pagesize;
    }
  }
}
</script>

<style scoped>

</style>