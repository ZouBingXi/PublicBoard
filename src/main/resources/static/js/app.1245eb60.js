(function(e){function t(t){for(var r,c,o=t[0],i=t[1],s=t[2],d=0,l=[];d<o.length;d++)c=o[d],Object.prototype.hasOwnProperty.call(a,c)&&a[c]&&l.push(a[c][0]),a[c]=0;for(r in i)Object.prototype.hasOwnProperty.call(i,r)&&(e[r]=i[r]);f&&f(t);while(l.length)l.shift()();return u.push.apply(u,s||[]),n()}function n(){for(var e,t=0;t<u.length;t++){for(var n=u[t],r=!0,c=1;c<n.length;c++){var o=n[c];0!==a[o]&&(r=!1)}r&&(u.splice(t--,1),e=i(i.s=n[0]))}return e}var r={},c={app:0},a={app:0},u=[];function o(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-17a9d45f":"7f7a69a0","chunk-2593777a":"2795d75d","chunk-2d0a3a89":"303677b4","chunk-2d0b9d35":"3e44a502","chunk-2d0c11f5":"955fe264","chunk-2d208299":"be74de44","chunk-2d21a407":"a0439531","chunk-428ce0a9":"700ec876","chunk-46f8e490":"be8169a5","chunk-6ba56812":"648549ce","chunk-7ada1ae3":"dfb03e10","chunk-c2c0ee46":"3e695d8e","chunk-d27b4650":"009b5d0e"}[e]+".js"}function i(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-17a9d45f":1,"chunk-2593777a":1,"chunk-428ce0a9":1,"chunk-46f8e490":1,"chunk-6ba56812":1,"chunk-7ada1ae3":1,"chunk-c2c0ee46":1,"chunk-d27b4650":1};c[e]?t.push(c[e]):0!==c[e]&&n[e]&&t.push(c[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{"chunk-17a9d45f":"61cb0051","chunk-2593777a":"bc4fddca","chunk-2d0a3a89":"31d6cfe0","chunk-2d0b9d35":"31d6cfe0","chunk-2d0c11f5":"31d6cfe0","chunk-2d208299":"31d6cfe0","chunk-2d21a407":"31d6cfe0","chunk-428ce0a9":"ba6377d9","chunk-46f8e490":"fbd1f43a","chunk-6ba56812":"b364f1dc","chunk-7ada1ae3":"84523d40","chunk-c2c0ee46":"84523d40","chunk-d27b4650":"efc714bb"}[e]+".css",a=i.p+r,u=document.getElementsByTagName("link"),o=0;o<u.length;o++){var s=u[o],d=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(d===r||d===a))return t()}var l=document.getElementsByTagName("style");for(o=0;o<l.length;o++){s=l[o],d=s.getAttribute("data-href");if(d===r||d===a)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||a,u=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");u.code="CSS_CHUNK_LOAD_FAILED",u.request=r,delete c[e],f.parentNode.removeChild(f),n(u)},f.href=a;var h=document.getElementsByTagName("head")[0];h.appendChild(f)})).then((function(){c[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var u=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=u);var s,d=document.createElement("script");d.charset="utf-8",d.timeout=120,i.nc&&d.setAttribute("nonce",i.nc),d.src=o(e);var l=new Error;s=function(t){d.onerror=d.onload=null,clearTimeout(f);var n=a[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),c=t&&t.target&&t.target.src;l.message="Loading chunk "+e+" failed.\n("+r+": "+c+")",l.name="ChunkLoadError",l.type=r,l.request=c,n[1](l)}a[e]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:d})}),12e4);d.onerror=d.onload=s,document.head.appendChild(d)}return Promise.all(t)},i.m=e,i.c=r,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)i.d(n,r,function(t){return e[t]}.bind(null,r));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/",i.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],d=s.push.bind(s);s.push=t,s=s.slice();for(var l=0;l<s.length;l++)t(s[l]);var f=d;u.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";n("85ec")},4360:function(e,t,n){"use strict";var r,c,a=n("2b0e"),u=n("2f62"),o=n("ade3"),i=(n("d3b7"),n("e9c4"),"user"),s=function(){return JSON.parse(window.localStorage.getItem(i))},d=function(e){window.localStorage.setItem(i,JSON.stringify(e))},l=function(){window.localStorage.removeItem(i)},f={getUser:s,saveUser:d,destroyUser:l},h=n("6c33"),p=n("90d2"),m={errors:null,user:{username:"1378113965",usertype:"管理员"},isAuthenticated:!1},b={currentUser:function(e){return e.user},isAuthenticated:function(e){return e.isAuthenticated}},g=(r={},Object(o["a"])(r,h["c"],(function(e,t){return new Promise((function(n,r){axios.post("/user/login",t).then((function(t){var c=t.data;200==c.code?(e.dispatch(h["b"]),n(c)):(e.commit(p["c"],c.msg),r(c))})).catch((function(){return console.log("网络问题")}))}))})),Object(o["a"])(r,h["d"],(function(e){e.commit(p["a"])})),Object(o["a"])(r,h["a"],(function(e){f.getUser()&&e.commit(p["b"],f.getUser())})),Object(o["a"])(r,h["b"],(function(e){axios.get("/user/showprofile").then((function(t){var n=t.data;e.commit(p["b"],n.data)}))})),Object(o["a"])(r,h["e"],(function(e){})),r),k=(c={},Object(o["a"])(c,p["c"],(function(e,t){e.errors=t})),Object(o["a"])(c,p["b"],(function(e,t){e.isAuthenticated=!0,e.user=t,f.saveUser(e.user)})),Object(o["a"])(c,p["a"],(function(e){e.isAuthenticated=!1,e.user={},e.errors={},f.destroyUser()})),c),v={state:m,actions:g,mutations:k,getters:b};a["default"].use(u["a"]);t["a"]=new u["a"].Store({state:{},mutations:{},actions:{},modules:{a:v}})},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d"),n("ac1f"),n("00b4"),n("d3b7"),n("3ca3"),n("ddb0");var r=n("2b0e"),c=n("bc3a"),a=n.n(c),u={},o=a.a.create(u);o.interceptors.request.use((function(e){return e}),(function(e){return Promise.reject(e)})),o.interceptors.response.use((function(e){return e}),(function(e){return Promise.reject(e)})),Plugin.install=function(e,t){e.axios=o,window.axios=o,Object.defineProperties(e.prototype,{axios:{get:function(){return o}},$axios:{get:function(){return o}}})},r["default"].use(Plugin);Plugin;var i=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("TheHeader"),n("router-view")],1)},s=[],d=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("el-header",{staticClass:"myheader"},[n("el-menu",{attrs:{mode:"horizontal",router:"","default-active":e.$route.path.match(/\/[a-z]*/)[0],"active-text-color":"black"}},[n("router-link",{staticClass:"mylink",attrs:{to:{path:"/"}}},[e._v("PublicBoard")]),e.isAuthenticated?n("el-menu-item",{attrs:{index:"/information"}},[n("span",{staticClass:"el-icon-s-custom",staticStyle:{margin:"0px"}}),e._v(e._s(e.currentUser.username)+" ")]):e._e(),e._l(e.menu,(function(t){return n("el-menu-item",{key:t.index,attrs:{index:t.index}},[n("span",{class:t.icon,staticStyle:{margin:"0px"}}),e._v(e._s(t.text)+" ")])})),"管理员"===e.currentUser.usertype&&e.isAuthenticated?n("el-menu-item",{attrs:{index:"/admin"}},[n("span",{staticClass:"el-icon-menu",staticStyle:{margin:"0px"}}),e._v("Admin Page ")]):e._e(),n("el-menu-item",{attrs:{index:"/"}},[n("span",{staticClass:"el-icon-s-home",staticStyle:{margin:"0px"}}),e._v("Home ")])],2)],1)},l=[],f=n("5530"),h=n("2f62"),p={name:"TheHeader",data:function(){return{menu_notAuthenticated:[{index:"/register",icon:"el-icon-edit-outline",text:"Sign Up"},{index:"/login",icon:"el-icon-edit-outline",text:"Sign In"}],menu_isAuthenticated:[{index:"/setting",icon:"el-icon-s-tools",text:"Setting"},{index:"/articleedit",icon:"el-icon-edit-outline",text:"New Article"}]}},computed:Object(f["a"])(Object(f["a"])({},Object(h["b"])(["currentUser","isAuthenticated"])),{},{menu:function(){return this.isAuthenticated?this.menu_isAuthenticated:this.menu_notAuthenticated}}),props:["test"],watch:{test:function(){console.log("this is test")}}},m=p,b=(n("d2ff"),n("2877")),g=Object(b["a"])(m,d,l,!1,null,"ae827c04",null),k=g.exports,v={name:"app",components:{TheHeader:k}},y=v,x=(n("034f"),Object(b["a"])(y,i,s,!1,null,null,null)),w=x.exports,j=n("a18c"),O=n("4360"),_=n("5c96"),A=n.n(_);n("8aa1");r["default"].use(A.a);var S=n("6c33"),P=n("90d2");r["default"].config.productionTip=!1,/^localhost:9090/.test(window.location.host)&&(axios.defaults.baseURL="/apis"),axios.interceptors.response.use((function(e){if(403!=e.data.code)return e;O["a"].commit(P["a"]),j["a"].push({path:"/login"}),Object(_["Message"])("请先登录！")})),j["a"].beforeEach((function(e,t,n){Promise.all([O["a"].dispatch(S["a"])]).then(n),window.scrollTo(0,0)})),new r["default"]({router:j["a"],store:O["a"],render:function(e){return e(w)}}).$mount("#app")},"6c33":function(e,t,n){"use strict";n.d(t,"a",(function(){return r})),n.d(t,"c",(function(){return c})),n.d(t,"d",(function(){return a})),n.d(t,"e",(function(){return u})),n.d(t,"b",(function(){return o}));var r="checkAuth",c="login",a="logout",u="updateUser",o="getUserInfo"},"85ec":function(e,t,n){},"8aa1":function(e,t,n){},"90d2":function(e,t,n){"use strict";n.d(t,"a",(function(){return r})),n.d(t,"b",(function(){return c})),n.d(t,"c",(function(){return a}));var r="logOut",c="setUser",a="setError"},9646:function(e,t,n){},a18c:function(e,t,n){"use strict";n("d3b7"),n("3ca3"),n("ddb0");var r=n("2b0e"),c=n("8c4f");n("4360");r["default"].use(c["a"]);var a=[{path:"/",name:"Home",component:function(){return n.e("chunk-d27b4650").then(n.bind(null,"bb51"))},children:[{path:"",name:"NewsList",component:function(){return n.e("chunk-c2c0ee46").then(n.bind(null,"dc4d"))}},{path:"subjectslist",name:"SubjectsList",component:function(){return n.e("chunk-7ada1ae3").then(n.bind(null,"9baa"))}}]},{path:"/register",name:"Register",component:function(){return n.e("chunk-428ce0a9").then(n.bind(null,"73cf"))}},{path:"/login",name:"Login",component:function(){return n.e("chunk-46f8e490").then(n.bind(null,"a55b"))}},{path:"/articleedit",name:"ArticleEdit",component:function(){return n.e("chunk-6ba56812").then(n.bind(null,"04d0"))}},{path:"/setting",name:"Setting",component:function(){return n.e("chunk-2593777a").then(n.bind(null,"4ef5"))}},{path:"/articleread",name:"ReadArticle",component:function(){return n.e("chunk-17a9d45f").then(n.bind(null,"71db"))}},{path:"/admin",name:"Admin",component:function(){return n.e("chunk-2d0b9d35").then(n.bind(null,"3530"))},children:[{path:"usertable",name:"Usertable",component:function(){return n.e("chunk-2d0a3a89").then(n.bind(null,"02b3"))}},{path:"topictable",name:"Topictable",component:function(){return n.e("chunk-2d21a407").then(n.bind(null,"bb5f"))}},{path:"poststable",name:"Poststable",component:function(){return n.e("chunk-2d208299").then(n.bind(null,"a42b"))}},{path:"logtable",name:"Logtable",component:function(){return n.e("chunk-2d0c11f5").then(n.bind(null,"4564"))}}]}],u=new c["a"]({base:"/",routes:a});t["a"]=u},d2ff:function(e,t,n){"use strict";n("9646")}});
//# sourceMappingURL=app.1245eb60.js.map