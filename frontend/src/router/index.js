import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import("@/views/Home"),
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
  {
    path: '/articleedit',
    name: 'ArticleEdit',
    component: () => import("@/views/ArticleEdit")
  },
  {
    path: '/admin',
    name: 'Admin',
    component: () => import("@/views/Admin"),
    children:[
      {
        path:"usertable",
        name:"Usertable",
        component:()=>import("@/components/Usertable")
      },
      {
        path:"topictable",
        name:"Topictable",
        component:()=>import("@/components/Topictable")
      },
      {
        path:"poststable",
        name:"Poststable",
        component:()=>import("@/components/Poststable")
      }
      ,
      {
        path:"logtable",
        name:"Logtable",
        component:()=>import("@/components/Logtable")
      }
    ]
  }

]

const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router
