package com.haeun.firstproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //스프링의 기본 설정을 바꿀 수 있게 해주는 어노테이션(바꿀 인터페이스를 구현을 하여 설정을 바꿀 수 있음)
//CORS
//다른 도메인에서의 자원을 호출하는 행위에 제한이 없을 경우 안전을 보장하기 힘듦. 
//CORS(Cross-Origin Resource Sharing)는 이렇게 시스템 수준에서 타 도메인 간 자원 호출을 승인하거나 차단하는 것을 결정하는 것임
public class CorsConfig  implements WebMvcConfigurer{
    
    @Override
    public void addCorsMappings(CorsRegistry registry) { //CORS정책 설정 바꾸줘는 메서드
        registry
            .addMapping("/**") //어떠한 path에 대해서 허용을 할 것인지에 대해서 작성해주는 것
            .allowedMethods("*") //어떠한 Http Method에 대해서 허용을 할 것인지에 대해서 작성해주는 것
            .allowedOrigins("*"); //어떠한 출처에 대해서 허용을 할 것인지에 대해서 작성해주는 
            //.allowedOrigins설정은 보안상 프론트엔드 Origin 프로토콜, 호스트, 포트 번호만 허용해주는 것이 맞음
    }

}
