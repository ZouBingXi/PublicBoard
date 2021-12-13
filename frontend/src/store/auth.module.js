//import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER,
  GET_USER_INFO
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH, SET_ERROR } from "./mutations.type";


const state = {
  errors: null,
  user: {username:"1378113965",usertype:"管理员"},
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
    return new Promise((resolve,reject) => {
      axios.post('/user/login',user)
          .then(({data})=>{
            if(data.code==200){
              context.dispatch(GET_USER_INFO);
              resolve(data);
            }
            else{
              context.commit(SET_ERROR,data.msg);
              reject(data);
            }
          })
          .catch(()=>console.log('网络问题'));
    })
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [CHECK_AUTH](context) {
    if(JwtService.getUser())
      context.commit(SET_AUTH,JwtService.getUser());
  },
  [GET_USER_INFO](context){
    axios.get('/user/showprofile')
        .then(({data})=>{
          context.commit(SET_AUTH,data.data);
    })
  },
  [UPDATE_USER](context){

  }
}

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },

  [SET_AUTH](state, user) {
    state.isAuthenticated = true;
    state.user = user;
    JwtService.saveUser(state.user);
  },
  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    state.errors = {};
    JwtService.destroyUser();
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};
