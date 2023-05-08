package com.haeun.board.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.haeun.board.filter.JwtAuthenticationFilter;

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
            .anyRequest().authenticated(); //나머지 요청은 인증된 사용자만 접근할 수 있도록 설정한다.

            // JwtAuthenticationFilter를 HttpSecurity필터에 추가한다. 이 필터는 요청이 들어올 때마다 JWT 인증을 처리한다.
             //UsernamePasswordAuthenticationFilter.class이전에 jwtAuthenticationFilter필터 작업을 해야해서 아래와 같이 작성함
            httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}
