import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import { Message } from 'element-ui';
import {CHECK_AUTH} from "@/store/actions.type";
import {PURGE_AUTH} from "@/store/mutations.type"
Vue.config.productionTip = false

if(/^localhost:9090/.test(window.location.host)){
  axios.defaults.baseURL='/apis';
}

axios.interceptors.response.use(re=>{
    if(re.data.code==403){
        store.commit(PURGE_AUTH);
        router.push({path:'/login'});
        Message('请先登录！')
    }
    else
        return re;
})

router.beforeEach((to, from, next) =>{
      Promise.all([store.dispatch(CHECK_AUTH)]).then(next);
    window.scrollTo(0,0);
});

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
