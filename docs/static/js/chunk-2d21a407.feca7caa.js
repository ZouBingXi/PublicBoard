(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d21a407"],{bb5f:function(e,t,a){"use strict";a.r(t);a("ac1f"),a("841c");var n=a("7a23"),l=Object(n["p"])("查看"),i=Object(n["p"])("删除"),c={class:"block",style:{"text-align":"center",margin:"10px"}};function r(e,t,a,r,o,p){var u=Object(n["S"])("el-table-column"),b=Object(n["S"])("el-input"),s=Object(n["S"])("el-button"),d=Object(n["S"])("el-table"),g=Object(n["S"])("el-pagination");return Object(n["J"])(),Object(n["m"])("div",null,[Object(n["q"])(d,{data:o.table.data,style:{width:"100%","min-width":"1000px"},stripe:""},{default:Object(n["hb"])((function(){return[(Object(n["J"])(!0),Object(n["m"])(n["b"],null,Object(n["Q"])(o.table.tableColumn,(function(e,t){return Object(n["J"])(),Object(n["k"])(u,{prop:e.prop,label:e.label,"min-width":e.minWidth,key:t,align:"center","show-overflow-tooltip":"",sortable:""},null,8,["prop","label","min-width"])})),128)),Object(n["q"])(u,{fixed:"right",label:"操作",align:"left",width:"250px"},{header:Object(n["hb"])((function(){return[Object(n["q"])(b,{modelValue:o.search,"onUpdate:modelValue":t[0]||(t[0]=function(e){return o.search=e}),size:"mini",placeholder:"Type to search"},null,8,["modelValue"])]})),default:Object(n["hb"])((function(){return[Object(n["n"])("template",null,[Object(n["q"])(s,{size:"mini"},{default:Object(n["hb"])((function(){return[l]})),_:1}),Object(n["q"])(s,{size:"mini",type:"danger"},{default:Object(n["hb"])((function(){return[i]})),_:1})])]})),_:1})]})),_:1},8,["data"]),Object(n["n"])("div",c,[Object(n["q"])(g,{"current-page":o.currentpage,"onUpdate:current-page":t[1]||(t[1]=function(e){return o.currentpage=e}),"page-sizes":o.pagesizes,"onUpdate:page-sizes":t[2]||(t[2]=function(e){return o.pagesizes=e}),"page-size":o.pagesize,"onUpdate:page-size":t[3]||(t[3]=function(e){return o.pagesize=e}),total:o.total,"onUpdate:total":t[4]||(t[4]=function(e){return o.total=e}),layout:"total, sizes, prev, pager, next, jumper",background:""},null,8,["current-page","page-sizes","page-size","total"])])])}var o={name:"Usertable",data:function(){return{search:"",pagesizes:[10,20,50,100],currentpage:1,pagesize:10,total:1,table:p}},methods:{gettable:function(){var e=this;this.$axios.get("/subject/admin/checksubject?page="+this.pagesize+"&page="+this.currentpage).then((function(t){e.table.data=t.data.data.list,e.total=t.data.data.total,console.log("请求话题表成功"),console.log(t)})).catch((function(){console.log("请求话题表失败")}))}},created:function(){this.gettable()},watch:{currentpage:function(){this.gettable()},pagesize:function(){this.gettable()}}},p={tableColumn:[{prop:"subjecttitle",label:"话题标题",minWidth:"150px"},{prop:"subjecttypename",label:"类型",minWidth:"50px"},{prop:"userid",label:"作者账号",minWidth:"120px"},{prop:"sendtime",label:"发帖时间"},{prop:"viewnum",label:"浏览量",minWidth:"60px"},{prop:"likenum",label:"点赞量",minWidth:"60px"}],data:[]},u=a("6b0d"),b=a.n(u);const s=b()(o,[["render",r]]);t["default"]=s}}]);
//# sourceMappingURL=chunk-2d21a407.feca7caa.js.map