(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5af2cdd2"],{"09c6":function(e,t,n){},3702:function(e,t,n){"use strict";n("9c9d")},"581c":function(e,t,n){"use strict";n("e976")},7942:function(e,t,n){"use strict";n.r(t);var a=n("7a23"),r=function(e){return Object(a["M"])("data-v-f4758828"),e=e(),Object(a["K"])(),e},o={class:"headerContain"},s=r((function(){return Object(a["n"])("div",{class:"banner"},null,-1)})),c={class:"whiteBackground myShadow"},l=r((function(){return Object(a["n"])("div",{class:"avatar-icon"},[Object(a["n"])("i",{class:"el-icon-camera-solid",style:{"font-size":"40px","margin-top":"40px"}}),Object(a["n"])("p",null,"修改我的头像")],-1)})),i={class:"infoContain"},u={class:"info",style:{"border-bottom":"none"}},d={class:"username"},m=Object(a["p"])("返回我的主页>"),b={class:"infoTitle"},h={class:"infoValue"},f=["onClick"];function g(e,t,n,r,g,j){var O=this,p=Object(a["S"])("el-avatar"),w=Object(a["S"])("el-upload"),v=Object(a["S"])("router-link"),C=Object(a["S"])("ChangeEmail"),P=Object(a["S"])("ChangePassword"),q=Object(a["S"])("ChangeUserName");return Object(a["J"])(),Object(a["m"])("div",o,[s,Object(a["n"])("div",c,[Object(a["q"])(w,{class:"avatar","show-file-list":!1,"http-request":j.myupload,"before-upload":j.beforeAvatarUpload},{default:Object(a["hb"])((function(){return[Object(a["q"])(p,{src:O.currentUser.headimage,shape:"square",fit:"contain",size:160,style:{"border-radius":"20px"}},null,8,["src"]),l]})),_:1},8,["http-request","before-upload"]),Object(a["n"])("div",i,[Object(a["n"])("div",u,[Object(a["n"])("span",d,Object(a["W"])(this.currentUser.username),1),Object(a["n"])("span",{class:"link",onClick:t[0]||(t[0]=function(e){return g.showChangeUserName=!g.showChangeUserName})},"编辑"),Object(a["q"])(v,{to:{path:"/personalpage"},class:"link",style:{float:"right","margin-top":"21px","text-decoration":"none"}},{default:Object(a["hb"])((function(){return[m]})),_:1})]),(Object(a["J"])(!0),Object(a["m"])(a["b"],null,Object(a["Q"])(j.list,(function(e){return Object(a["J"])(),Object(a["m"])("div",{class:"info",key:e.title},[Object(a["n"])("p",b,Object(a["W"])(e.title),1),Object(a["n"])("span",h,Object(a["W"])(e.value),1),e.canEdit?(Object(a["J"])(),Object(a["m"])("span",{key:0,class:"editLink",onClick:function(t){return j.show(e.flag)}},"编辑",8,f)):Object(a["l"])("",!0)])})),128))])]),g.showChangeEmail?(Object(a["J"])(),Object(a["k"])(C,{key:0,onExit:t[1]||(t[1]=function(e){return g.showChangeEmail=!1})})):Object(a["l"])("",!0),g.showChangePassword?(Object(a["J"])(),Object(a["k"])(P,{key:1,onExit:t[2]||(t[2]=function(e){return g.showChangePassword=!1})})):Object(a["l"])("",!0),g.showChangeUserName?(Object(a["J"])(),Object(a["k"])(q,{key:2,onExit:t[3]||(t[3]=function(e){return g.showChangeUserName=!1})})):Object(a["l"])("",!0)])}var j=n("5530"),O={class:"changeEmailPage"},p=Object(a["n"])("div",{class:"transparentBackground"},null,-1),w={class:"Box"},v=Object(a["n"])("h1",{class:"TitleText"},"修改邮箱",-1),C=Object(a["p"])("确定"),P=Object(a["p"])("取消");function q(e,t,n,r,o,s){var c=this,l=Object(a["S"])("el-input"),i=Object(a["S"])("el-form-item"),u=Object(a["S"])("el-button"),d=Object(a["S"])("el-form");return Object(a["J"])(),Object(a["m"])("div",O,[p,Object(a["n"])("div",w,[v,Object(a["q"])(d,{model:o.ChangeEmailForm,rules:o.rules,ref:"ChangeEmailForm","label-position":"top"},{default:Object(a["hb"])((function(){return[Object(a["q"])(i,{label:"新邮箱：",prop:"email"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.ChangeEmailForm.email,"onUpdate:modelValue":t[0]||(t[0]=function(e){return o.ChangeEmailForm.email=e}),maxlength:"30",placeholder:"请输入新邮箱"},null,8,["modelValue"])]})),_:1}),Object(a["q"])(i,{label:"验证码：",prop:"code"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.ChangeEmailForm.code,"onUpdate:modelValue":t[1]||(t[1]=function(e){return o.ChangeEmailForm.code=e}),maxlength:"6",placeholder:"请输入验证码"},{append:Object(a["hb"])((function(){return[Object(a["q"])(u,{onClick:s.getcode,disabled:o.buttonDisabled},{default:Object(a["hb"])((function(){return[Object(a["p"])(Object(a["W"])(s.buttonText),1)]})),_:1},8,["onClick","disabled"])]})),_:1},8,["modelValue"])]})),_:1})]})),_:1},8,["model","rules"]),Object(a["q"])(u,{onClick:s.ChangeEmail,type:"primary"},{default:Object(a["hb"])((function(){return[C]})),_:1},8,["onClick"]),Object(a["q"])(u,{onClick:t[2]||(t[2]=function(e){return c.$emit("exit")}),type:"primary"},{default:Object(a["hb"])((function(){return[P]})),_:1})])])}var x=n("7864"),F=n("6c33"),k={name:"ChangeEmail",data:function(){return{ChangeEmailForm:{email:"",code:""},buttonDisabled:!1,timeToSend:null,rules:{email:[{required:!0,message:"请输入邮箱",trigger:"blur"},{required:!0,pattern:/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,message:"请检查邮箱格式",trigger:"blur"}],code:[{required:!0,message:"请输入验证码",trigger:"blur"},{min:6,max:6,message:"验证码为6位",trigger:"blur"}]}}},methods:{ChangeEmail:function(){var e=this;this.$refs["ChangeEmailForm"].validate().then((function(){var t={email:e.ChangeEmailForm.email,code:e.ChangeEmailForm.code};e.$axios.post("/user/changeemail",t).then((function(t){var n=t.data;200==n.code?(console.log("修改邮箱成功",n),x["a"].success("修改邮箱成功"),e.$store.dispatch(F["a"]),e.$emit("exit")):(console.log("修改失败",n),x["a"].error(n.msg))}))}))},getcode:function(){var e=this;this.$refs["ChangeEmailForm"].validateField("email",(function(t){t||e.$axios.get("/user/sendcode?flag=noexist&to="+e.ChangeEmailForm.email).then((function(t){var n=t.data;200==n.code?e.setTimer():x["a"].error(n.msg)}))}))},setTimer:function(){var e=this;this.buttonDisabled=!0,this.timeToSend=30;var t=setInterval((function(){1==e.timeToSend?(clearInterval(t),e.buttonDisabled=!1):e.timeToSend--}),1e3)}},computed:{buttonText:function(){return this.buttonDisabled?this.timeToSend+"秒后重新发送":"点击发送验证码"}}},y=(n("581c"),n("6b0d")),U=n.n(y);const E=U()(k,[["render",q]]);var S=E,_={class:"changePassword"},$=Object(a["n"])("div",{class:"transparentBackground"},null,-1),V={class:"Box"},N=Object(a["n"])("h1",{class:"TitleText"},"修改密码",-1),T=Object(a["p"])("确定"),J=Object(a["p"])("取消");function B(e,t,n,r,o,s){var c=this,l=Object(a["S"])("el-input"),i=Object(a["S"])("el-form-item"),u=Object(a["S"])("el-form"),d=Object(a["S"])("el-button");return Object(a["J"])(),Object(a["m"])("div",_,[$,Object(a["n"])("div",V,[N,Object(a["q"])(u,{model:o.changePasswordForm,rules:o.rules,ref:"changePasswordForm","label-position":"top",onKeypress:Object(a["jb"])(s.ChangePassword,["enter"])},{default:Object(a["hb"])((function(){return[Object(a["q"])(i,{label:"原密码：",prop:"oldPassword"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.changePasswordForm.oldPassword,"onUpdate:modelValue":t[0]||(t[0]=function(e){return o.changePasswordForm.oldPassword=e}),maxlength:"20",placeholder:"请输入原密码","show-password":""},null,8,["modelValue"])]})),_:1}),Object(a["q"])(i,{label:"新密码：",prop:"newPassword"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.changePasswordForm.newPassword,"onUpdate:modelValue":t[1]||(t[1]=function(e){return o.changePasswordForm.newPassword=e}),maxlength:"20",placeholder:"请输入新密码","show-password":""},null,8,["modelValue"])]})),_:1}),Object(a["q"])(i,{label:"确认密码：",prop:"confirmPassword"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.changePasswordForm.confirmPassword,"onUpdate:modelValue":t[2]||(t[2]=function(e){return o.changePasswordForm.confirmPassword=e}),maxlength:"20",placeholder:"请再次输入密码","show-password":""},null,8,["modelValue"])]})),_:1})]})),_:1},8,["model","rules","onKeypress"]),Object(a["q"])(d,{onClick:s.ChangePassword,type:"primary"},{default:Object(a["hb"])((function(){return[T]})),_:1},8,["onClick"]),Object(a["q"])(d,{onClick:t[3]||(t[3]=function(e){return c.$emit("exit")}),type:"primary"},{default:Object(a["hb"])((function(){return[J]})),_:1})])])}var D={name:"changePassword",data:function(){var e=this,t=function(t,n,a){e.changePasswordForm.newPassword===n?a():a(new Error("两次密码不一致"))};return{changePasswordForm:{oldPassword:"",newPassword:"",confirmPassword:""},rules:{oldPassword:[{required:!0,message:"请输入原密码",trigger:"blur"}],newPassword:[{required:!0,message:"请输入新密码",trigger:"blur"},{pattern:/(?=.*[0-9])(?=.*[a-zA-Z]).{6,20}/,message:"密码中必须包含字母、数字，至少6个字符，最多20个字符。",trigger:"blur"}],confirmPassword:[{required:!0,validator:t,trigger:"blur"}]}}},methods:{ChangePassword:function(){var e=this;this.$refs["changePasswordForm"].validate().then((function(){var t={oldPassword:e.changePasswordForm.oldPassword,newPassword:e.changePasswordForm.newPassword};e.$axios.post("/user/changepassword",t).then((function(t){var n=t.data;200==n.code?(console.log("修改密码成功",n),x["a"].success("修改密码成功"),e.$emit("exit")):(console.log("修改失败",n),x["a"].error(n.msg))}))}))}}};n("f129");const z=U()(D,[["render",B]]);var A=z,K={class:"changeUsername"},I=Object(a["n"])("div",{class:"transparentBackground"},null,-1),W={class:"Box"},Z=Object(a["n"])("h1",{class:"TitleText"},"修改用户名",-1),M=Object(a["p"])("确定"),G=Object(a["p"])("取消");function L(e,t,n,r,o,s){var c=this,l=Object(a["S"])("el-input"),i=Object(a["S"])("el-form-item"),u=Object(a["S"])("el-form"),d=Object(a["S"])("el-button");return Object(a["J"])(),Object(a["m"])("div",K,[I,Object(a["n"])("div",W,[Z,Object(a["q"])(u,{model:o.changeUsernameForm,rules:o.rules,ref:"changeUsernameForm","label-position":"top",onKeypress:Object(a["jb"])(s.ChangeName,["enter"])},{default:Object(a["hb"])((function(){return[Object(a["q"])(i,{label:"新用户名：",prop:"newName"},{default:Object(a["hb"])((function(){return[Object(a["q"])(l,{modelValue:o.changeUsernameForm.newName,"onUpdate:modelValue":t[0]||(t[0]=function(e){return o.changeUsernameForm.newName=e}),maxlength:"20",placeholder:"请输入新用户名"},null,8,["modelValue"])]})),_:1})]})),_:1},8,["model","rules","onKeypress"]),Object(a["q"])(d,{onClick:s.ChangeName,type:"primary"},{default:Object(a["hb"])((function(){return[M]})),_:1},8,["onClick"]),Object(a["q"])(d,{onClick:t[1]||(t[1]=function(e){return c.$emit("exit")}),type:"primary"},{default:Object(a["hb"])((function(){return[G]})),_:1})])])}var Q={name:"changePassword",data:function(){return{changeUsernameForm:{newName:""},rules:{newName:[{required:!0,message:"请输入用户名",trigger:"blur"}]}}},methods:{ChangeName:function(){var e=this;this.$refs["changeUsernameForm"].validate().then((function(){var t=new FormData;t.append("username",e.changeUsernameForm.newName),e.$axios.post("user/updateprofile",t).then((function(t){console.log(t),e.$store.dispatch(F["a"]),e.$emit("exit")}))}))}}};n("c753");const H=U()(Q,[["render",L]]);var R=H,X=n("f154"),Y=n.n(X),ee=n("5502"),te={name:"PersonalSettingPage",components:{ChangeEmail:S,ChangePassword:A,ChangeUserName:R},data:function(){return{headimg:Y.a,showChangeEmail:!1,showChangePassword:!1,showChangeUserName:!1}},computed:Object(j["a"])({list:function(){return[{title:"用户ID",value:this.currentUser.userid},{title:"邮箱",value:this.currentUser.email,canEdit:!0,flag:"showChangeEmail"},{title:"密码",value:"已设置",canEdit:!0,flag:"showChangePassword"},{title:"用户类型",value:this.currentUser.usertype},{title:"状态",value:this.currentUser.banstate}]}},Object(ee["b"])(["currentUser"])),methods:{myupload:function(e){var t=this;console.log(e);var n=new FormData;n.append("avatar",e.file),this.$axios.post("user/updateprofile",n).then((function(e){console.log(e),t.$store.dispatch(F["a"])}))},beforeAvatarUpload:function(e){var t="image/jpeg"===e.type,n=e.size/1024/1024<2;return t||this.$message.error("上传头像图片只能是 JPG"),n||this.$message.error("上传头像图片大小不能超过 2MB!"),t&&n},show:function(e){this[e]=!this[e]}}};n("3702");const ne=U()(te,[["render",g],["__scopeId","data-v-f4758828"]]);t["default"]=ne},"9c9d":function(e,t,n){},c349:function(e,t,n){},c753:function(e,t,n){"use strict";n("09c6")},e976:function(e,t,n){},f129:function(e,t,n){"use strict";n("c349")}}]);
//# sourceMappingURL=chunk-5af2cdd2.60a47baf.js.map