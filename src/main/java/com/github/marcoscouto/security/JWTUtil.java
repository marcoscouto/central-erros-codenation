package com.github.marcoscouto.security;

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

}
