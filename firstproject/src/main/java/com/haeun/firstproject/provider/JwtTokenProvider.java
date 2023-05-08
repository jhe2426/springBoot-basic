package com.haeun.firstproject.provider;


import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//provider : 무언가를 만들어서 뱉어주는 것을 provider 폴더 안에 코드를 작성한다고 함
//Jwt <- t가 Token임 jwtToken은 잘못된 말임
@Component 
public class JwtTokenProvider {
    //Jwt를 사용하려면 시큐리티 키가 필요하다고 함
    //${jwt.scurity-key}는 application.properties안에 선언되어있는 값을 가져올 때 저런식으로 작성한다고 함
    //* JWT 생성 혹은 검증에 사용될 시크릿 키 만드는 거
    //* 시크릿 키 같은 데이터는 보안에 유의해야 하기 때문에
    //* application.properties 또는 환경변수(이렇게는 테스트 개발할 때 자주 사용되는 방식이라고 함)로 등록해서 사용함
    @Value("${jwt.secret-key}")//민감한 정보이므로 이렇게 사용한다고 함 SECRET_KEY = "Secret1!" 이런식으로 작성하지 않음 깃허브에 올라가면 정보가 전부 다 노출되므로
    private String SECRET_KEY;



// 일반 유저와 관리자 유저와 권한이 다름
// security자체에서 할 수는 있지만 쉽지 않음
// 그래서 아래의 로직을 사용하면 됨
// 관리자 로그인
// 아이디 비밀번호를 받아서 -> 데이터베이스에서 User 가져오기 -> 판별(일반, 관리자) -> JWT 생성(관리자든 일반이든 생성해줘야함) JWT은 JSON이므로 ->
//  {"id": "qwer", "role": "nomal | admin"} 이 형식으로 만들어 줄 수 있음



    //* JWT 생성 메서드
    //JWT  생성할 시 만료시간을 꼭 작성해줘야 함(주로 한시간이라고 함)
    public String create(String id, int role) { //* 토큰 생성 id와 role을 설정을 해주어서 토큰을 만들어주면 되고, 관리자 api에서 만약 role이 1이 보통 유저인데 1이 관리자 api 요청을 하려고하면 바로 오류를 리턴해주면 됨
        //만료 날짜
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));//현재 시간에서 한시간 더한 시간을 데이터 값으로 가지고 있음

        //SignatureAlgorithm.HS256 : 암호화하는 방식
        String jwt = 
            Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                // .setSubject(subject)
                // .setIssuedAt(new Date())
                // .setExpiration(expiredDate) //만료시간 설정
                .claim("id", id)
                .claim("role", role)
                .compact();
        return jwt;
    }

    //* JWT 검증
    public UserRole validate(String jwt) {
        Claims claims = 
            Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt)
                .getBody();

        String id = (String) claims.get("id");
        int role = (Integer) claims.get("role");
        System.out.println(id+ " " + role);
        return new UserRole(id,role);
    }
}
