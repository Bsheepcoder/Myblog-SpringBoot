package com.backend.common.utils;

import com.backend.common.utils.mybatis.IdGeneratorUtil;
import com.backend.config.comm.holder.DefaultInfoContextHolder;
import com.backend.entity.AccountEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */
public class CUtil {

    public static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 下划线转驼峰
     * user_name  ---->  userName
     * userName   --->  userName
     *
     * @param underlineStr 带有下划线的字符串
     * @return 驼峰字符串
     */
    public static String toCamelCase(String underlineStr) {
        if (underlineStr == null) {
            return null;
        }
        // 分成数组
        char[] charArray = underlineStr.toCharArray();
        // 判断上次循环的字符是否是"_"
        boolean underlineBefore = false;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0, l = charArray.length; i < l; i++) {
            // 判断当前字符是否是"_",如果跳出本次循环
            if (charArray[i] == 95) {
                underlineBefore = true;
            } else if (underlineBefore) {
                // 如果为true，代表上次的字符是"_",当前字符需要转成大写
                buffer.append(charArray[i] -= 32);
                underlineBefore = false;
            } else {
                // 不是"_"后的字符就直接追加
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 驼峰转 下划线
     * userName  ---->  user_name
     * user_name  ---->  user_name
     *
     * @param camelCaseStr 驼峰字符串
     * @return 带下滑线的String
     */
    public static String toUnderlineCase(String camelCaseStr) {
        if (camelCaseStr == null) {
            return null;
        }
        // 将驼峰字符串转换成数组
        char[] charArray = camelCaseStr.toCharArray();
        StringBuffer buffer = new StringBuffer();
        //处理字符串
        for (int i = 0, l = charArray.length; i < l; i++) {
            if (charArray[i] >= 65 && charArray[i] <= 90) {
                buffer.append("_").append(charArray[i] += 32);
            } else {
                buffer.append(charArray[i]);
            }
        }
        return buffer.toString();
    }

    /**
     * 获取当前用户信息，转内部表
     * @return
     */
    public static AccountEntity getCurrentUserInfo(){
        return DefaultInfoContextHolder.getCurrentUser();
    }

    /**
     * 非空判断
     * @param object
     * @return
     */
    public static boolean isNotEmpty(Object object) {
        if (object != null && !object.equals("") && !object.equals("null")) {
            return (true);
        }
        return (false);
    }

    /**
     * 空判断
     * @param object
     * @return
     */
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }
    /**
     * create by: dingwj
     * description: 对象属性复制
     * create time: 14:48 2022/6/1
     *
     * @Param: null
     * @return
     */
    public static <T> T convert(T from,T to){
        BeanUtils.copyProperties(from, to);
        return to;
    }

    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getNowDateTime() {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return datetimeFormat.format(new Date());
    }
    /**
     * 获取当前日期字符串
     * @return
     */
    public static String getNowDate() {
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd");
        return datetimeFormat.format(new Date());
    }
    /**
     * 手动生成主键ID
     * @return
     */
    public static String getNewId() {
        return IdGeneratorUtil.getSysNewId();
    }

    /**
     * 获取类的所有属性，包括父类
     *
     * @param object
     * @return
     */
    public static Field[] getAllFields(Object object) {
        Class<?> clazz = object.getClass();
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null) {
            fieldList.addAll(new ArrayList<>(Arrays.asList(clazz.getDeclaredFields())));
            clazz = clazz.getSuperclass();
        }
        Field[] fields = new Field[fieldList.size()];
        fieldList.toArray(fields);
        return fields;
    }

    /**
     * String类型 转换为Date, 如果参数长度为10 转换格式”yyyy-MM-dd“ 如果参数长度为19 转换格式”yyyy-MM-dd
     * HH:mm:ss“ * @param text String类型的时间值
     */
    public static Date strToDate(String text) throws Exception {
        if (CUtil.isEmpty(text)) {
            return null;
        } else {
            if (text.indexOf(":") == -1 && text.length() == 10) {
                return dateFormat.parse(text);
            } else if (text.indexOf(":") > 0 && text.length() == 19) {
                return datetimeFormat.parse(text);
            } else if (text.length() == 4) {
                return yearFormat.parse(text);
            } else {
                throw new Exception("时间格式转化出错", new Throwable());
            }
        }
    }

    /**
     * 获取请求ip地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request){
        String ipAddress = null;
        try {
            ipAddress = request.getHeader("X-Real-IP");
            if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("x-forwarded-for"); // 获取 x-forwarded-for 头
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if (ipAddress.equals("127.0.0.1")) {
                    // 根据网卡取本机配置的IP
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                // = 15
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 字符串转数字
     * @param s
     * @param df
     * @return
     */
    public static int getInt(String s, Integer df) {
        if (s == null || s == "") {
            return df;
        }
        try {
            return (Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
