package com.backend.common.core.utils.mybatis;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description: id生成
 */
public class IdGeneratorUtil {

    private static final AtomicLong counter = new AtomicLong(1);

    /**
     * 数据库自增主键序列最大值
     */
    public static final Integer SYS_DB_ID_MAX = 9999;

    private static long getAndAddOne() {
        final long id = counter.getAndAdd(1);
        if (id >= SYS_DB_ID_MAX) {
            counter.getAndSet(1);
        }
        return id;
    }

    /**
     * 输入日期格式，返回格式内的内容，输入格式以-分隔
     * 12位
     *
     * @return 时间戳去年前两位和3位微妙 如200806155214
     **/
    private static String getTimeYearMonthDay() {
        return new SimpleDateFormat("yyMMddHHmmss").format(new Date());
    }

    public static String getSysNewId() {
        return getTimeYearMonthDay() + String.format("%04d", getAndAddOne());
    }

}