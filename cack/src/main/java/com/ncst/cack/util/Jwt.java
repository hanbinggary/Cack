package com.ncst.cack.util;

import com.ncst.cack.model.LoginInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author liuxuelian
 * @date 2021/03/24
 */
public class Jwt {

    private static final Long Expiration = 43200000L; // 12小时
    private static final String SECRET = "2021cack888";

    public static String generateToken(LoginInfo loginInfo) {
        Claims claims = Jwts.claims().setSubject(loginInfo.getUsername());
        claims.put("id", loginInfo.getId());
        claims.put("roleName", loginInfo.getRoleName());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + Expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public static boolean validateToken(String token, LoginInfo loginInfo) {
        if (isTokenExpired(token)) return false;
        return parseToken(token)
                .getId()
                .equals(loginInfo.getId());
    }

    public static String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private static LoginInfo parseToken(String token) {
        Claims body = getClaimsFromToken(token);
        return new LoginInfo().setId(Long.parseLong(body.get("id").toString()));
    }

    private static boolean isTokenExpired(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }
}
