package com.haeun.firstproject.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.haeun.firstproject.provider.JwtTokenProvider;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

    }

     //* Request Header 중 "Authorization" : "Bearer eyJh...." Bearer를 뺀 데이터를 가져오겠다는 것 
    private String parseToken(HttpServletRequest request) {
        
        //* Request Header 중 "Authorization" : "Bearer eyJh...." Header의 Authorization에 있는 값을 가져오겠다는 것
        String token = request.getHeader("Authorization"); //Header의 Authorization에 있는 값을 가져오겠다는 것 Header에 Authorization이름을 가지는 것이 있음
        
        boolean hasToken = token != null && token.equalsIgnoreCase("null");
        if (!hasToken) return null; //토큰이 없다면 null을 반환

        //받은 토큰이 Bearer로 지정되어있나
        //* "Bearer eyJh...." 띄어쓰기 또한 꼭 포함 시켜야함
        boolean isBearer = token.startsWith("Bearer ");
        if (!isBearer) return null; //토큰이 Bearer로 지정되어 있지 않다면 null을 반환하겠다.

        //* "Bearer eyJh...." 여기에서 7번부터 값을 가져오겠다는 것(실제 토큰만 가져오겠다는 것)
        String jwt = token.substring(7); //8번째 부터 값을 가져오겠다.
        return jwt;
    
    }

    
}
