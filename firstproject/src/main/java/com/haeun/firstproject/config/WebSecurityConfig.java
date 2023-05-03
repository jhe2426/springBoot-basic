package com.haeun.firstproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.haeun.firstproject.filter.JwtAuthenticationFilter;

// @EnableWebSecurity webSecurity가 호출 될 때 저 어노테이션이 선언된 클래스가 호출 됨
// @EnableWebSecurity`**: Spring Security의 웹 보안 지원을 사용하도록 설정하는 어노테이션이다.
@EnableWebSecurity
// @Configuration: 이 클래스가 Spring Framework의 구성 클래스임을 나타내는 어노테이션이다.
@Configuration
public class WebSecurityConfig {
    
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public WebSecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    protected SecurityFilterChain configuer(HttpSecurity httpSecurity) throws Exception{ //여기서 발생하는 모든 예외는 호출부로 다 떠넘기겠다.
        httpSecurity.cors().and()
                                .csrf().disable() //csrf는 백엔드 개발자는 세션을 관리하지 않기 때문에 disable로 설정 서버 개발자가 세션 관리한다고 함
                                .httpBasic().disable()
                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() //세션을 생성하지 않음
                                .authorizeRequests().antMatchers("/jwt/**").permitAll() // /jwt/** 이 패턴으로 요청오는 것은 접근 권한을 모두 허용한다.
                                .anyRequest().authenticated();
                                
        //UsernamePasswordAuthenticationFilter.class이전에 jwtAuthenticationFilter필터 작업을 해야해서 아래와 같이 작성함
        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
