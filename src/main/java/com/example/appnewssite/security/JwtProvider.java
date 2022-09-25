package com.example.appnewssite.security;

import com.example.appnewssite.entity.Role;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtProvider {

    private static final long expireTime = 1000 * 60 * 60 * 24;

    private static final String secretKey = "mahfiysozhechkimbilmasin";

    public String generateToken(String username, Role role) {

        Date expireDate = new Date(System.currentTimeMillis() + expireTime);

        return Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(expireDate)
                .setExpiration(expireDate)
                .claim("roles", role.toString())
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
