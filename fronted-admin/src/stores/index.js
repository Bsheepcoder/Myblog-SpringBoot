// store/index.js
import { createStore } from 'vuex';

const store = createStore({
    state:{
        isAuthenticated: false, // 用户登录状态，默认未登录
    },
    mutations: {
        // 更新用户登录状态
        updateAuthentication(state, isAuthenticated) {
            state.isAuthenticated = isAuthenticated;
        },
        // 其他 mutations...
    },
    actions: {
        // 登录操作，异步调用后端接口验证用户身份，并更新登录状态
        login({ commit }) {
            // 调用后端接口验证用户身份
            // 成功后更新登录状态
            commit('updateAuthentication', true);
        },
        // 登出操作，更新登录状态为未登录
        logout({ commit }) {
            // 清除本地存储的用户信息等操作...
            commit('updateAuthentication', false);
        },
        // 其他 actions...
    },
    // 其他配置...
});

export default store;