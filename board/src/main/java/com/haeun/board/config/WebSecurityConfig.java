package com.haeun.board.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.haeun.board.common.utill.CustomResponse;
import com.haeun.board.filter.JwtAuthenticationFilter;

//* 이 안에서만 아래의 해당 클래스가 사용되므로 내부 class로 사용하는 것
class FailedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"code\": \"AF\", \"message\": \"Authentication Failed\"}");; // response를 적는 것 contentType이  JSON이므로 JSON을 문자열로 받으면 됨 ""이 기호를 문자열로 받기 위해서 \ 역 슬레시를 사용한 것
    }

}


@EnableWebSecurity
@Configuration // 설정을 바꾼 것이다.를 서버에게 알려주는 어노테이션
public class WebSecurityConfig {
    
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception{//여기에서 발생하는 오류는 호출부로 다 던짐

        httpSecurity
            //CORS(Cross-Origin Resource Sharing)를 활성화
            .cors().and()
            //CSRF(Cross-Site Request Forgery) 및 기본 인증을 비활성화합니다.
            .csrf().disable()
            .httpBasic().disable()
            //세션 생성 정책을 STATELESS로 설정하여 서버가 세션을 생성하지 않도록 한다.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers("/api/v1/**", "/api/v2/auth/**").permitAll()
            // .antMatchers("/api/v1/board/list", "/api/v1/board/top3").permitAll() 아래 줄과 같은 의미
            .antMatchers(HttpMethod.GET, "/api/v2/board/**").permitAll()
            .anyRequest().authenticated().and() //나머지 요청은 인증된 사용자만 접근할 수 있도록 설정한다.
            .exceptionHandling().authenticationEntryPoint(new FailedAuthenticationEntryPoint()); //* 인증이 실패 되었을 때 반환되는 오류를 개발자가 작성한 오류로 보이도록 작성하는 코드
            // JwtAuthenticationFilter를 HttpSecurity필터에 추가한다. 이 필터는 요청이 들어올 때마다 JWT 인증을 처리한다.
             //UsernamePasswordAuthenticationFilter.class이전에 jwtAuthenticationFilter필터 작업을 해야해서 아래와 같이 작성함
            httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
