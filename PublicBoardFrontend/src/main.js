import axios from './plugins/axios'
import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import installElementPlus from './plugins/element'
import infiniteScroll from 'vue-infinite-scroll'


import './myscss.scss'
import {GET_USER_INFO} from "@/store/actions.type";

if(/^localhost:9090/.test(window.location.host)){
    axios.defaults.baseURL='/apis';
}

const app = createApp(App)
installElementPlus(app)
app.use(store).use(router).mount('#app');
app.use(infiniteScroll);

console.log(infiniteScroll,"这里")

store.dispatch(GET_USER_INFO);

app.config.globalProperties.$axios=axios;
