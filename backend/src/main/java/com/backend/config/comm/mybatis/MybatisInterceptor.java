package com.backend.config.comm.mybatis;

import com.backend.common.core.utils.BlogUtil;
import com.backend.common.core.utils.CUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间=>初始化
 */
@Slf4j
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.debug("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.debug("------sqlCommandType------" + sqlCommandType);

        if (parameter == null) {
            return invocation.proceed();
        }
        String userId = CUtil.getCurrentUserInfo().getAid().toString();
        if (SqlCommandType.INSERT == sqlCommandType) {
            Field[] fields = CUtil.getAllFields(parameter);
            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("createUserId".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createdBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createdBy == null || local_createdBy.equals("")) {
                            if (CUtil.isNotEmpty(userId)) {
                                // 登录人账号
                                field.setAccessible(true);
                                field.set(parameter, userId);
                                field.setAccessible(false);
                            }
                        }
                    }
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createDate == null || local_createDate.equals("")) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            Field[] fields = null;
            if (parameter instanceof ParamMap) {
                ParamMap<?> p = (ParamMap<?>) parameter;
                if (p.containsKey("et")) {
                    parameter = p.get("et");
                } else {
                    parameter = p.get("param1");
                }
                if (parameter == null) {
                    return invocation.proceed();
                }
                fields = CUtil.getAllFields(parameter);
            } else {
                fields = CUtil.getAllFields(parameter);
            }

            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("updateUserId".equals(field.getName())) {
                        //获取登录用户信息
                        if (CUtil.isNotEmpty(userId)) {
                            // 登录账号
                            field.setAccessible(true);
                            field.set(parameter, userId);
                            field.setAccessible(false);
                        }
                    }
                    if ("updateTime".equals(field.getName())) {
                        field.setAccessible(true);
                        field.set(parameter, new Date());
                        field.setAccessible(false);
                    }
                } catch (Exception e) {
                    // 日志记录 - try{}catch(){}需手动记录，（e.printStackTrace() 只会在控制台中打印，不会输出到日志）
                    log.error(BlogUtil.containChinese(e.getMessage()) ? e.getMessage() : "系统内部异常", e);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
