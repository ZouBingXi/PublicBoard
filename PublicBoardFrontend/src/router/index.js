import { createRouter, createWebHashHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login')
  },
  {
    path:'/forgetpassword',
    name:'ForgetPassword',
    component: () => import('../views/ForgetPassword')
  },
  {
    path:'/test',
    name:'test',
    component: ()=>import('../components/Test')
  },
  {
    path:'/personalpage',
    name:'PersonalPage',
    component: ()=>import('../views/PersonalPage')
  },
  {
    path:'/personalinfopage',
    name:'personalIfoPage',
    component: ()=>import('../views/PersonalInfoPage')
  },
  {
    path:'/articleread',
    name:'articleRead',
    component: ()=>import('../views/ArticleRead')
  },
  {
    path:'/articleedit',
    name:'articleEdit',
    component: ()=>import('../views/ArticleEdit')
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

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
