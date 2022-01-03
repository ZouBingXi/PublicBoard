import {
    LOGIN,
    LOGOUT,
/*    REGISTER,
    CHECK_AUTH,
    UPDATE_USER,*/
    GET_USER_INFO
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH/*, SET_ERROR*/ } from "./mutations.type";

import axios from "../plugins/axios";
import store from "@/store/index";
import router from "@/router";
import {ElMessage} from "element-plus";

const state = {
    user: {},
    isAuthenticated:false,
};

const getters = {
    currentUser(state) {
        return state.user;
    },
    isAuthenticated(state) {
        return state.isAuthenticated;
    }
};

const actions = {
    [LOGIN](context, user) {
        return new Promise((resolve, reject) => {
            axios.post('/user/login', user)
                .then(({data}) => {
                    if (data.code == 200) {
                        context.dispatch(GET_USER_INFO);
                        resolve();
                    } else
                        reject(data);
                })
        })
    },
    [LOGOUT](context) {
        axios.get("user/logout");
        context.commit(PURGE_AUTH);
    },
    [GET_USER_INFO](context){
        axios.get('/user/showprofile')
            .then(({data})=>{
                if(data.code==403) {
                    store.commit(PURGE_AUTH);
                    router.push({path: '/login'});
                    ElMessage('请先登录！')
                }
                else
                    context.commit(SET_AUTH,data.data);
            })
    },
}

const mutations={
    [SET_AUTH](state, user) {
        console.log(user);
        state.isAuthenticated = true;
        state.user = user;
    },
    [PURGE_AUTH](state) {
        state.isAuthenticated = false;
        state.user = {};
    }
}
export default {
    state,
    actions,
    mutations,
    getters
};