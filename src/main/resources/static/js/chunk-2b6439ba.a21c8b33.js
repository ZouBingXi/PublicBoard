(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2b6439ba"],{"04d0":function(e,t,n){"use strict";n.r(t);var o=n("7a23"),l=function(e){return Object(o["M"])("data-v-d682260e"),e=e(),Object(o["K"])(),e},r={id:"background"},i={id:"base"},a=l((function(){return Object(o["n"])("div",{id:"edit-background"},null,-1)})),c={id:"edit-combo"},u=l((function(){return Object(o["n"])("div",{id:"edit-text"},[Object(o["n"])("p",{id:"edit-text1"},"发布")],-1)})),s={id:"input-form"},d=l((function(){return Object(o["n"])("i",{class:"el-icon-plus"},null,-1)})),b={id:"input-button"},f=Object(o["p"])("确认发布"),m=Object(o["p"])("取消");function p(e,t,n,l,p,g){var h=Object(o["S"])("el-divider"),j=Object(o["S"])("el-input"),O=Object(o["S"])("el-form-item"),w=Object(o["S"])("el-radio-button"),v=Object(o["S"])("el-radio-group"),q=Object(o["S"])("el-upload"),y=Object(o["S"])("el-form"),F=Object(o["S"])("el-button"),k=Object(o["S"])("el-image"),V=Object(o["S"])("el-dialog"),x=Object(o["S"])("el-backtop");return Object(o["J"])(),Object(o["m"])("div",r,[Object(o["n"])("div",i,[a,Object(o["n"])("div",c,[u,Object(o["q"])(h),Object(o["n"])("div",s,[Object(o["q"])(y,{model:p.ruleForm,rules:p.rules,ref:"ruleForm","label-width":"100px","label-position":"top",class:"demo-ruleForm"},{default:Object(o["hb"])((function(){return[Object(o["q"])(O,{id:"input-form-item1",label:"标题",prop:"title"},{default:Object(o["hb"])((function(){return[Object(o["q"])(j,{modelValue:p.ruleForm.title,"onUpdate:modelValue":t[0]||(t[0]=function(e){return p.ruleForm.title=e}),placeholder:"请填写标题"},null,8,["modelValue"])]})),_:1}),Object(o["q"])(O,{id:"input-form-item2",label:"类型",prop:"newstype"},{default:Object(o["hb"])((function(){return[Object(o["q"])(v,{fill:"#909399",modelValue:p.ruleForm.newstype,"onUpdate:modelValue":t[1]||(t[1]=function(e){return p.ruleForm.newstype=e})},{default:Object(o["hb"])((function(){return[Object(o["q"])(w,{label:"校园卡丢失"}),Object(o["q"])(w,{label:"寻物"}),Object(o["q"])(w,{label:"寻主"}),Object(o["q"])(w,{label:"捞人"}),Object(o["q"])(w,{label:"求助"}),Object(o["q"])(w,{label:"避雷"}),Object(o["q"])(w,{label:"树洞"}),Object(o["q"])(w,{label:"指南"}),Object(o["q"])(w,{label:"课程"})]})),_:1},8,["modelValue"])]})),_:1}),Object(o["q"])(O,{id:"input-form-item5",label:"内容",prop:"content"},{default:Object(o["hb"])((function(){return[Object(o["q"])(j,{type:"textarea",autosize:{minRows:5},modelValue:p.ruleForm.content,"onUpdate:modelValue":t[2]||(t[2]=function(e){return p.ruleForm.content=e}),placeholder:"请填写内容",maxlength:"1000","show-word-limit":""},null,8,["modelValue"])]})),_:1}),Object(o["q"])(O,{id:"input-form-item6",label:"插入图片（ 限制 3 张大小不超过 2MB 的 JPG 格式图片 ）"},{default:Object(o["hb"])((function(){return[Object(o["q"])(q,{action:"#","list-type":"picture-card","auto-upload":!1,"file-list":p.fileList,"on-preview":g.handlePreview,"on-remove":g.handleRemove,"on-change":g.handleChange,"before-remove":g.handleBeforeRemove,"before-upload":g.handleBeforeUpload,multiple:"",limit:3,"on-exceed":g.handleExceed,accept:"image/jpeg"},{default:Object(o["hb"])((function(){return[d]})),_:1},8,["file-list","on-preview","on-remove","on-change","before-remove","before-upload","on-exceed"])]})),_:1})]})),_:1},8,["model","rules"])]),Object(o["q"])(h),Object(o["n"])("div",b,[Object(o["q"])(F,{id:"input-button1",type:"info",onClick:t[3]||(t[3]=function(e){return g.submitForm("ruleForm")})},{default:Object(o["hb"])((function(){return[f]})),_:1}),Object(o["q"])(F,{id:"input-button2",type:"info",plain:"",onClick:t[4]||(t[4]=function(e){return g.gotoHome("ruleForm")})},{default:Object(o["hb"])((function(){return[m]})),_:1})])])]),Object(o["n"])("div",null,[Object(o["q"])(V,{id:"img-enlarge",modelValue:p.dialogVisible,"onUpdate:modelValue":t[5]||(t[5]=function(e){return p.dialogVisible=e})},{default:Object(o["hb"])((function(){return[Object(o["q"])(k,{style:{width:"100%"},src:p.dialogImageUrl,fit:"fit"},null,8,["src"])]})),_:1},8,["modelValue"])]),Object(o["q"])(x)])}var g=n("5530"),h=(n("d3b7"),n("159b"),n("99af"),n("b0c0"),n("5502")),j={data:function(){return{ruleForm:{title:"",newstype:"校园卡丢失",content:""},rules:{title:[{required:!0,message:"请填写标题",trigger:"blur"},{max:25,message:"长度不超过 25 个字符",trigger:"blur"}],newstype:[{required:!0,message:"请选择贴子类型",trigger:"change"}],content:[{required:!0,message:"请填写内容",trigger:"blur"},{max:1e3,message:"长度不能超过 1000 个字符",trigger:"blur"}]},dialogImageUrl:"",dialogVisible:!1,fileList:[]}},methods:{submitForm:function(e){var t=this;this.$refs[e].validate((function(n){if(!n)return t.$message({showClose:!0,message:"发布失败",type:"error"}),!1;var o=t,l=0;switch(o.ruleForm.newstype){case"校园卡丢失":l=1;break;case"寻物":l=2;break;case"寻主":l=3;break;case"捞人":l=4;break;case"求助":l=5;break;case"避雷":l=6;break;case"树洞":l=7;break;case"指南":l=8;break;case"课程":l=9;break}var r=new FormData;0!=t.fileList.length&&(console.log("fileList展示"),console.log(t.fileList),t.fileList.forEach((function(e){return r.append("image",e.raw)}))),r.append("newstitle",o.ruleForm.title),r.append("content",o.ruleForm.content),r.append("newstypeid",l),console.log("param展示"),0!=t.fileList.length&&console.log(r.getAll("image")),console.log(r.getAll("newstitle")),console.log(r.getAll("content")),console.log(r.getAll("newstypeid"));var i={headers:{"Content-Type":"multipart/form-data"}};t.$axios.post("/news/addwithimages",r,i).then((function(n){200==n.data.code?(t.$refs[e].resetFields(),t.$message({showClose:!0,message:n.data.msg,type:"success"}),t.$router.push({path:"/"})):t.$message({showClose:!0,message:n.data.msg,type:"error"})}))}))},gotoHome:function(e){this.$refs[e].resetFields(),this.$router.push({path:"/"})},handlePreview:function(e){this.dialogImageUrl=e.url,this.dialogVisible=!0},handleRemove:function(e,t){console.log("删除操作"),this.fileList.pop(),console.log(e,t),console.log(this.fileList)},handleExceed:function(e,t){this.$message.warning("当前限制最多插入 3 个文件，本次选择了 ".concat(e.length," 个文件，共选择了 ").concat(e.length+t.length," 个文件"))},handleBeforeUpload:function(e){console.log(e)},handleBeforeRemove:function(e){return this.$confirm("确定移除 ".concat(e.name,"？"))},handleChange:function(e,t){var n="image/jpeg"===e.raw.type,o=e.size/1024/1024<2;n?o?(console.log("添加操作"),this.fileList.push(e)):(this.$message.error("上传图片大小不能超过 2MB!"),t.pop()):(this.$message.error("上传图片只能是 JPG 格式!"),t.pop()),console.log(e,t),console.log(this.fileList)}},computed:Object(g["a"])({},Object(h["b"])(["currentUser","isAuthenticated"]))},O=(n("54d9"),n("6b0d")),w=n.n(O);const v=w()(j,[["render",p],["__scopeId","data-v-d682260e"]]);t["default"]=v},"54d9":function(e,t,n){"use strict";n("db3b")},"99af":function(e,t,n){"use strict";var o=n("23e7"),l=n("da84"),r=n("d039"),i=n("e8b5"),a=n("861d"),c=n("7b0b"),u=n("07fa"),s=n("8418"),d=n("65f0"),b=n("1dde"),f=n("b622"),m=n("2d00"),p=f("isConcatSpreadable"),g=9007199254740991,h="Maximum allowed index exceeded",j=l.TypeError,O=m>=51||!r((function(){var e=[];return e[p]=!1,e.concat()[0]!==e})),w=b("concat"),v=function(e){if(!a(e))return!1;var t=e[p];return void 0!==t?!!t:i(e)},q=!O||!w;o({target:"Array",proto:!0,forced:q},{concat:function(e){var t,n,o,l,r,i=c(this),a=d(i,0),b=0;for(t=-1,o=arguments.length;t<o;t++)if(r=-1===t?i:arguments[t],v(r)){if(l=u(r),b+l>g)throw j(h);for(n=0;n<l;n++,b++)n in r&&s(a,b,r[n])}else{if(b>=g)throw j(h);s(a,b++,r)}return a.length=b,a}})},db3b:function(e,t,n){}}]);
//# sourceMappingURL=chunk-2b6439ba.a21c8b33.js.map