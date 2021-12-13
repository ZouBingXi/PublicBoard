<template>
  <div style="position: relative">
    <div class="banner"></div>
    <div class="infoForm">
      <el-upload action="123" name="avatar" :http-request="myupload" :show-file-list="false">
        <el-avatar :size="150" :src="currentUser.headimage" fit="fill" style="border: 5px solid white" shape="square"/>
      </el-upload>
      <el-form v-model="currentUser" label-width="auto">
        <el-form-item v-for="(item,index) in personalInof" :prop="item.prop" :label="item.label">
          <template>
            <div v-show="item.edit">
              <el-input v-model="item.data" style="border: none;width: 250px;margin-right: 10px"></el-input>
              <el-button @click="updateprofile(index)">保存</el-button>
              <el-button @click="item.edit=!item.edit">取消</el-button>
            </div>
            <div v-show="!item.edit">
              {{currentUser[item.prop]}}
              <a v-if="item.disabled" class="el-icon-edit" style="margin-left: 10px;cursor: pointer" @click="item.edit=!item.edit;item.data=currentUser[item.prop]"></a>
            </div>
          </template>
        </el-form-item>
      </el-form>
      <el-button type="danger" @click="logout">退出登录</el-button>
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import {PURGE_AUTH} from "@/store/mutations.type";
import {GET_USER_INFO} from "@/store/actions.type";
import router from "@/router";
import Banner from "@/components/Banner";

export default {
  name: "Setting",
  components:{
    Banner
  },
  data(){
    return{
      personalInof:[
        {prop:"userid",label:"用户id:",disabled:false,edit:false,data:''},
        {prop:"username",label:"用户名:",disabled:true,edit:false,data:''},
        {prop:"email",label:"邮箱:",disabled:true,edit:false,data:''},
        {prop:"usertype",label:"用户类型:",disabled:false,edit:false,data:''},
        {prop:"banstate",label:"状态:",disabled:false,edit:false,data:''},
      ]
    }
  },
  methods:{
    logout(){
      axios.get("user/logout").then(()=>{
        this.$store.commit(PURGE_AUTH);
      this.$router.push({path:"/"})
      })
    },
    myupload(context){
      console.log(context);
      let formData = new FormData();
      formData.append("avatar",context.file);
      axios.post("user/updateprofile",formData)
          .then(re=>
          {
            console.log(re);
            this.$store.dispatch(GET_USER_INFO);
          });
    },
    updateprofile(index){
      if(this.personalInof[index].data!=this.currentUser[this.personalInof[index].prop]){
        let formData = new FormData();
        formData.append(this.personalInof[index].prop,this.personalInof[index].data);
        axios.post("user/updateprofile",formData)
            .then(re=>
            {
              console.log(re);
              this.$store.dispatch(GET_USER_INFO);
            });
      }
      this.personalInof[index].edit=!this.personalInof[index].edit;
    }
  },
  computed:{
    ...mapGetters(['currentUser'])
  }
}
</script>

<style scoped>
.banner{
  background: #eeeeee;
  margin: 10px 0px 10px 0px;
  padding: 30px;
  height: 150px;
  text-align: center;
}
.infoForm{
  position: absolute;
  width: 600px;
  margin: auto;
  top: 100px;
  left: 0;
  right: 0;
  bottom: 0;

}

</style>