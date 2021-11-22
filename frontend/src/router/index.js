import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import("@/views/Home")
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import("@/views/Login")
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import("@/views/Register")
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
      }
    ]
  }

]

const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

export default router
