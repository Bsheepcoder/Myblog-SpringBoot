package com.backend.common.utils;

/**
 * @author: qxd
 * @date: 2024/2/23
 * @description:
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    /** 有效期为 60 * 60 *1000  一个小时 */
    public static final Long JWT_TTL = 60 * 60 *1000L;

    /** 设置秘钥明文 */
    public static final String JWT_KEY = "guoer111";

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=JwtUtil.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("guoer111")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }

    public static void main(String[] args) throws Exception {
//        String jwt = createJWT("123");
//        //输出jwt eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjQ4MGI2YTQ2MDY0NzQxYjM4N2YxZTAzZDY5M2U4MiIsInN1YiI6IjEyMyIsImlzcyI6InNnIiwiaWF0IjoxNjUwMjU0NTEwLCJleHAiOjE2NTAyNTgxMTB9.V6n-x9rGCa2jHfN5ZR2lnC5vf3rkzPlUqLSI_FY2Whc
        //解密jwt
        Claims claims = parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2YjQ4MGI2YTQ2MDY0NzQxYjM4N2YxZTAzZDY5M2U4MiIsInN1YiI6IjEyMyIsImlzcyI6InNnIiwiaWF0IjoxNjUwMjU0NTEwLCJleHAiOjE2NTAyNTgxMTB9.V6n-x9rGCa2jHfN5ZR2lnC5vf3rkzPlUqLSI_FY2Whc");
        String subject = claims.getSubject();
        System.out.println(subject);
        System.out.println(claims);
    }

    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }


}