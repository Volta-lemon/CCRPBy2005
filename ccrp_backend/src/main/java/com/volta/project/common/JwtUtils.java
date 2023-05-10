package com.volta.project.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {
    private static String  signKey = "sc2005"; //秘钥
    private static Long expire = 43200000L; //令牌有效时间12h

    /**
     * 生产JWT令牌
     * @param claims
     * @return
     */
    public static String generateJwt(Map<String ,Object> claims){
        String jwt = Jwts.builder()
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setExpiration(new Date(System.currentTimeMillis()+expire))
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt
     * @return
     */
    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
