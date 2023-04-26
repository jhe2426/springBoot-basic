package com.haeun.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//* CORS 정책 : 다른 출처와 자원을 공유 하려고 할 때의 정책(다른 출처의 리소스에 접근하는 것을 제어하는 보안 메커니즘이다.)
//* Origin이란 프로토콜, 호스트, 포트 번호를 의미
@Configuration //설정하는 파일이다.를 알리는 어노테이션
public class CorsConfig  implements WebMvcConfigurer{
    
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")    
            .allowedOrigins("*")//모든 출처 허용
            .allowedMethods("*");//모든 메서드에 대해서 적용하겠다
    }
}
