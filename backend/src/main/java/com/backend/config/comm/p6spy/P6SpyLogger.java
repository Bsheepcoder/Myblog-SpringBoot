package com.backend.config.comm.p6spy;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.time.LocalDateTime;

/**
 * 自定义日志
 * 默认com.p6spy.engine.spy.appender.SingleLineFormat
 * @author kangqing
 * @date 2020/9/7 10:58
 */
public class P6SpyLogger implements MessageFormattingStrategy {

    /**
     * 日志格式
     * @param connectionId 连接id
     * @param now 当前时间
     * @param elapsed 耗时多久
     * @param category 类别
     * @param prepared mybatis带占位符的sql
     * @param sql 占位符换成参数的sql
     * @param url sql连接的 url
     * @return 自定义格式日志
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? "P6SpyLogger " + LocalDateTime.now() + " | elapsed " + elapsed + "ms | category " + category + " | connection " + connectionId + " | url " + url + " | sql \n" + sql : "";
    }
}