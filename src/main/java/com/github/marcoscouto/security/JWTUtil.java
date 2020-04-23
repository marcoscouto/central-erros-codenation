package com.github.marcoscouto.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Integer expiration;

    public String generateToken(String username){
        return Jwts
                .builder()
                .setSubject(username)
                .setExpiration(getExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    private Date getExpirationDate(){
        Instant instant = LocalDate
                .now()
                .plusDays(2)
                .atStartOfDay(ZoneId.systemDefault())
                .toInstant();
        return Date.from(instant);
    }

    public boolean isValid(String token) {
        Claims claims = getClaims(token);
        if(!claims.isEmpty()){
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            if(!username.isEmpty() && expirationDate != null && new Date().before(expirationDate)){
                return true;
            }
        }

        return false;
    }

    private Claims getClaims(String token) {
        try{
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);
        if(!claims.isEmpty()) return claims.getSubject();
        return null;
    }
}
