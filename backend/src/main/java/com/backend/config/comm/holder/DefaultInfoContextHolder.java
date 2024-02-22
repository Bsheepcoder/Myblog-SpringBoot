package com.backend.config.comm.holder;

import com.backend.entity.AccountEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */

public class DefaultInfoContextHolder {

    /*************************************当前用户信息********************************************************************/
    private static final String CURRENT_USER="currentUser";

    private static final ThreadLocal<HashMap<String,Object>> LOOKUP_KEY_HOLDER = new ThreadLocal() {
        protected Object initialValue() {
            Map<String,Object> map=new HashMap();
            map.put(CURRENT_USER,new AccountEntity());//用户信息 初始化
            return map;
        }
    };

    /**
     * 设置当前用户信息
     * @param accountEntity
     */
    public static void setCurrentUser(AccountEntity accountEntity){
        HashMap<String, Object> map = LOOKUP_KEY_HOLDER.get();
        if(accountEntity==null){
            accountEntity=new AccountEntity();
        }
        map.put(CURRENT_USER,accountEntity);
    }

    /**
     * 获取当前用户信息
     * @return
     */
    public static AccountEntity getCurrentUser(){
        HashMap<String, Object> map = LOOKUP_KEY_HOLDER.get();
        return (AccountEntity) map.get(CURRENT_USER);
    }
}
