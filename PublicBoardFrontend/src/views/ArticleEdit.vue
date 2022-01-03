<template>
  <!-- 页面背景 -->
  <div id="background">

    <!-- 基础页面 -->
    <div id="base">

      <!-- 发布背景 -->
      <div id="edit-background">
      </div>

      <!-- 发布组合 -->
      <div id="edit-combo">

        <!-- 发布文本 -->
        <div id="edit-text">
          <p id="edit-text1">发布</p>
        </div>

        <!-- 分割线 -->
        <el-divider></el-divider>

        <!-- 输入表单 -->
        <div id="input-form">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" label-position="top" class="demo-ruleForm">

            <el-form-item id="input-form-item1" label="标题" prop="title">
              <el-input v-model="ruleForm.title" placeholder="请填写标题"></el-input>
            </el-form-item>

            <el-form-item id="input-form-item2" label="类型" prop="newstype">
              <el-radio-group fill="#909399" v-model="ruleForm.newstype">
                <el-radio-button label="校园卡丢失"></el-radio-button>
                <el-radio-button label="寻物"></el-radio-button>
                <el-radio-button label="寻主"></el-radio-button>
                <el-radio-button label="捞人"></el-radio-button>
                <el-radio-button label="求助"></el-radio-button>
                <el-radio-button label="避雷"></el-radio-button>
                <el-radio-button label="树洞"></el-radio-button>
                <el-radio-button label="指南"></el-radio-button>
                <el-radio-button label="课程"></el-radio-button>
              </el-radio-group>
            </el-form-item>

            <el-form-item id="input-form-item5" label="内容" prop="content">
              <el-input type="textarea" :autosize="{ minRows: 5 }" v-model="ruleForm.content" placeholder="请填写内容" maxlength="1000" show-word-limit></el-input>
            </el-form-item>

            <el-form-item id="input-form-item6" label="插入图片（ 限制 3 张大小不超过 2MB 的 JPG 格式图片 ）">
              <!-- 上传图片 -->
              <el-upload
                  action="#"
                  list-type="picture-card"
                  :auto-upload="false"
                  :file-list="fileList"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :on-change="handleChange"
                  :before-remove="handleBeforeRemove"
                  :before-upload="handleBeforeUpload"
                  multiple
                  :limit="3"
                  :on-exceed="handleExceed"
                  accept="image/jpeg">
                <i class="el-icon-plus"></i>
              </el-upload>
            </el-form-item>
          </el-form>
        </div>

        <!-- 分割线 -->
        <el-divider></el-divider>

        <!-- 按钮组 -->
        <div id="input-button">
          <el-button id="input-button1" type="info" @click="submitForm('ruleForm')">确认发布</el-button>
          <el-button id="input-button2" type="info" plain @click="gotoHome('ruleForm')">取消</el-button>
        </div>
      </div>
    </div>

    <!-- 放大图片 -->
    <div>
      <el-dialog id="img-enlarge" v-model="dialogVisible">
        <el-image
            style="width: 100%"
            :src="dialogImageUrl"
            fit="fit"
        ></el-image>
<!--        <img width="100%" :src="dialogImageUrl" alt="">-->
      </el-dialog>
    </div>

    <!-- 回到顶部 -->
    <el-backtop />
  </div>
</template>

<script>
import {mapGetters} from "vuex"

export default {
  data() {
    return {
      ruleForm: {
        title: '',
        newstype: '校园卡丢失',
        content: ''
      },
      rules: {
        title: [
          { required: true, message: '请填写标题', trigger: 'blur' },
          { max: 25, message: '长度不超过 25 个字符', trigger: 'blur' }
        ],
        newstype: [
          { required: true, message: '请选择贴子类型', trigger: 'change' }
        ],
        content: [
          { required: true, message: '请填写内容', trigger: 'blur' },
          { max: 1000, message: '长度不能超过 1000 个字符', trigger: 'blur' }
        ]
      },
      dialogImageUrl: '',
      dialogVisible: false,
      fileList: []
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this;
          let typenum = 0;

          switch (_this.ruleForm.newstype) {
            case '校园卡丢失' : typenum = 1;
              break;
            case '寻物' : typenum = 2;
              break;
            case '寻主' : typenum = 3;
              break;
            case '捞人' : typenum = 4;
              break;
            case '求助' : typenum = 5;
              break;
            case '避雷' : typenum = 6;
              break;
            case '树洞' : typenum = 7;
              break;
            case '指南' : typenum = 8;
              break;
            case '课程' : typenum = 9;
              break;
          }

          let param = new FormData();
          if (this.fileList.length != 0) {
            console.log('fileList展示')
            console.log(this.fileList);
            this.fileList.forEach(img => param.append("image", img.raw));
          }
          param.append("newstitle", _this.ruleForm.title);
          param.append("content", _this.ruleForm.content);
          param.append("newstypeid", typenum);
          console.log('param展示')
          if (this.fileList.length != 0) {
            console.log(param.getAll("image"));
          }
          console.log(param.getAll("newstitle"));
          console.log(param.getAll("content"));
          console.log(param.getAll("newstypeid"));

          let config = {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }

          this.$axios.post('/news/addwithimages', param, config).then((response) => {
            if(response.data.code == 200) {
              this.$refs[formName].resetFields();
              this.$message({
                showClose: true,
                message: response.data.msg,
                type: 'success'
              });
              this.$router.push({path: '/'});
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
            message: '发布失败',
            type: 'error'
          });
          return false;
        }
      });
    },
    gotoHome(formName) {
      this.$refs[formName].resetFields();
      this.$router.push({path: '/'});
    },
    handlePreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleRemove(file, fileList) {
      console.log('删除操作');
      this.fileList.pop();
      console.log(file, fileList);
      console.log(this.fileList);
    },
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制最多插入 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    handleBeforeUpload(file) {
      console.log(file);
    },
    handleBeforeRemove(file) {
      return this.$confirm(`确定移除 ${ file.name }？`);
    },
    handleChange(file, fileList) {
      const isJPG = file.raw.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG 格式!');
        fileList.pop();
      } else if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 2MB!');
        fileList.pop();
      } else {
        console.log('添加操作');
        this.fileList.push(file);
      }
      console.log(file, fileList);
      console.log(this.fileList);
    }
  },
  computed:{
    ...mapGetters(["currentUser","isAuthenticated"])
  }
}
</script>

<style scoped>
#base {
  position:absolute;
  left:50%;
  width:1024px;
  z-index:0;
  transform:translate(-50%);
}

#edit-combo {
  /*background:#E6E6E6;*/
  border-radius:10px;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  margin:10px 0px 10px 0px;
  padding:20px 50px 50px 50px;
  position:absolute;
  left:100px;
  top:0px;
  width:724px;
}

.el-divider {
  /*background-color:#909399;*/
}

#edit-text {
  border-width:0px;
  position:relative;
  left:162px;
  top:0px;
  width:400px;
  height:52px;
  display:flex;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:40px;
  text-align:center;
}
#edit-text1 {
  position:relative;
  align-self:flex-start;
  margin:0px 0px 0px 0px;
  padding:0px 0px 0px 0px;
  box-sizing:border-box;
  width:100%;
  border-width:0px;
  word-wrap:break-word;
  text-transform:none;
}

#input-form {
  position:relative;
  top:0px;
  font-family:'微软雅黑';
  font-weight:600;
  font-style:normal;
  font-size:15px;
}

.el-input /deep/ .el-input__inner:focus{
  border-color:#17181A;
}

.el-textarea /deep/ .el-textarea__inner:focus{
  border-color:#17181A;
}

#input-button {
  border-width:0px;
  position:relative;
  left:257px;
  top:10px;
  font-family:'微软雅黑';
  font-weight:400;
  font-style:normal;
  font-size:15px;
}

#input-button1 {
  width:100px;
}

#input-button2 {
  width:100px;
}

</style>