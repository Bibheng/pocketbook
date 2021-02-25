package com.matthew.pocketbook.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.matthew.pocketbook.common.exception.CustomException;

import java.util.Date;
import java.util.Map;

/**
 * JWT工具类
 *
 * @author Matthew
 * @date 2021-01-28 18:24
 **/
public class JwtUtil {
    /**
     * 加密生成jwt
     *
     * @param map     要加密的数据 size = 1
     * @param secret  密钥
     * @param timeOut 超时时间
     * @return java.lang.String
     * @author Matthew
     * @date 2021-01-28 19:00
     */
    public static String encode(Map<String, String> map, String secret, long timeOut) {
        // 失效时间
        Date invalidTime = new Date(System.currentTimeMillis() + timeOut);
        // 私钥加密
        Algorithm algorithm = Algorithm.HMAC256(secret);
        // 构建载荷
        JWTCreator.Builder builder = JWT.create();
        map.forEach(builder::withClaim);
        builder.withExpiresAt(invalidTime);
        // 签名
        return builder.sign(algorithm);
    }

    /**
     * 解密jwt
     *
     * @param token  解密后的map
     * @param secret 密钥
     * @return java.util.Map<java.lang.String   ,   com.auth0.jwt.interfaces.Claim>
     * @author Matthew
     * @date 2021-01-28 22:21
     */
    public static Map<String, Claim> decode(String token, String secret) {
        if (token == null || token.length() == 0) {
            throw new CustomException("token为空:" + token);
        }
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaims();
    }
}
