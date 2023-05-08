package com.haeun.board.provider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    //JWT 생성
    public String create(String email) {

        Date expiredDate = Date.from(Instant.now().plus(1,ChronoUnit.HOURS));

        String jwt = 
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .compact();

        return jwt;
    }

    //JWT 검증 및 토큰에 들어있는 subject를(User의 email이 들어있음) 반환해주는 기능
    public String validate(String jwt) {
        Claims claims = 
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();

        return claims.getSubject();
    }
}
