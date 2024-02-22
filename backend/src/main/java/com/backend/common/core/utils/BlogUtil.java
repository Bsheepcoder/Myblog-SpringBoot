package com.backend.common.core.utils;

import com.alibaba.fastjson.JSONObject;
import com.backend.common.core.entity.RegexpConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author: qxd
 * @date: 2024/2/22
 * @description:
 */
public abstract class BlogUtil {
    private static final Logger log = LoggerFactory.getLogger(BlogUtil.class);
    private static final String UNKNOW = "unknown";

    public BlogUtil() {
    }

    public static String camelToUnderscore(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        } else {
            String[] arr = StringUtils.splitByCharacterTypeCamelCase(value);
            if (arr.length == 0) {
                return value;
            } else {
                StringBuilder result = new StringBuilder();
                IntStream.range(0, arr.length).forEach((i) -> {
                    if (i != arr.length - 1) {
                        result.append(arr[i]).append("_");
                    } else {
                        result.append(arr[i]);
                    }

                });
                return StringUtils.lowerCase(result.toString());
            }
        }
    }

    public static String underscoreToCamel(String value) {
        StringBuilder result = new StringBuilder();
        String[] arr = value.split("_");
        String[] var3 = arr;
        int var4 = arr.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String s = var3[var5];
            result.append(String.valueOf(s.charAt(0)).toUpperCase()).append(s.substring(1));
        }

        return result.toString();
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null && "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static boolean match(String regex, String value) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static void makeResponse(HttpServletResponse response, String contentType, int status, Object value) throws IOException {
        response.setContentType(contentType);
        response.setStatus(status);
        response.getOutputStream().write(JSONObject.toJSONString(value).getBytes());
    }

    public static void makeSuccessResponse(HttpServletResponse response, Object value) throws IOException {
        makeResponse(response, "application/json", 200, value);
    }

    public static void makeFailureResponse(HttpServletResponse response, Object value) throws IOException {
        makeResponse(response, "application/json", 500, value);
    }

    public static void makeJsonResponse(HttpServletResponse response, int status, Object value) throws IOException {
        makeResponse(response, "application/json", status, value);
    }

    public static Mono<Void> makeWebFluxResponse(ServerHttpResponse response, String contentType, HttpStatus status, Object value) {
        response.setStatusCode(status);
        response.getHeaders().add("Content-Type", contentType);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JSONObject.toJSONString(value).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static String getHttpServletRequestIpAddress() {
        HttpServletRequest request = getHttpServletRequest();
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static String getServerHttpRequestIpAddress(ServerHttpRequest request) {
        HttpHeaders headers = request.getHeaders();
        String ip = headers.getFirst("x-forwarded-for");
        if (ip != null && !ip.isEmpty() && !"unknown".equalsIgnoreCase(ip) && ip.contains(",")) {
            ip = ip.split(",")[0];
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("HTTP_X_FORWARDED_FOR");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = headers.getFirst("X-Real-IP");
        }

        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = ((InetSocketAddress)Objects.requireNonNull(request.getRemoteAddress())).getAddress().getHostAddress();
        }

        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    public static boolean containChinese(String value) {
        if (StringUtils.isBlank(value)) {
            return Boolean.FALSE;
        } else {
            Matcher matcher = RegexpConstant.CHINESE.matcher(value);
            return matcher.find();
        }
    }

    public static void printSystemUpBanner(Environment environment) {
        String banner = "-----------------------------------------\n服务启动成功，时间：" + DateUtil.formatFullTime(LocalDateTime.now(), "yyyy-MM-dd HH:mm:ss") + "\n服务名称：" + environment.getProperty("spring.application.name") + "\n端口号：" + environment.getProperty("server.port") + "\n-----------------------------------------";
        System.out.println(banner);
    }
}
