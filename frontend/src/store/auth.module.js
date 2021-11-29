//import ApiService from "@/common/api.service";
//import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH, SET_ERROR } from "./mutations.type";


const state = {
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

};

const mutations = {
  [LOGIN](state, data) {
    state.user=data;
    state.isAuthenticated = true;
  },
  [LOGOUT](state, data) {
    state.user= {};
    state.isAuthenticated = false;
  },
/*  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    state.errors = {};
    JwtService.destroyToken();
  }*/
};

export default {
  state,
  actions,
  mutations,
  getters
};
