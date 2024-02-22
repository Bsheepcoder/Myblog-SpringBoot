package com.backend.config.comm.p6spy;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */

import com.backend.entity.AccountEntity;
import com.backend.common.core.utils.CUtil;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义日志
 * 默认com.p6spy.engine.spy.appender.SingleLineFormat
 * @author kangqing
 * @date 2020/9/7 10:58
 */
@Slf4j
public class P6SpyLogger implements MessageFormattingStrategy {

    public P6SpyLogger() {
    }

    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        if(StringUtils.isBlank(sql)){
            return "";
        }
        StringBuffer message = new StringBuffer();
        //1.搜集用户信息
        AccountEntity currentUserInfo = CUtil.getCurrentUserInfo();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.append(" | 时间 " + formatter.format(date));
        if(currentUserInfo!=null&&StringUtils.isNotBlank(currentUserInfo.getUsername())&&StringUtils.isNotBlank(currentUserInfo.getUsername())){
            message.append(" | 用户 " +currentUserInfo.getUsername()+" "+ currentUserInfo.getUsername());
        }
        //2.搜集请求地址
        ServletRequestAttributes servletRequestAttributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes!=null){
            HttpServletRequest request = servletRequestAttributes.getRequest();
            message.append(" | IP地址 ").append(CUtil.getIp(request)).append(" | 方法地址 ").append(request.getRequestURI());
        }
        message.append(" | 耗时 ").append(elapsed).append(" ms | SQL 语句：").append("\n").append(sql.replaceAll("\\s+", " ")).append(";");
        return message.toString();
    }


}