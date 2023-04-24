package com.haeun.firstproject.service.implement;

import org.springframework.stereotype.Service;

import com.haeun.firstproject.service.RestApiService;

@Service //구현되는 클래스에 선언을 하면 됨
//@Service생략하면 아래와 같은 오류가 밠생
//Consider defining a bean of type 'com.haeun.firstproject.service.implement.RestApiServiceImplement' in your configuration. 
public class RestApiServiceImplement implements RestApiService {
    
    public String getMethod() {
        return "Return to Service Layer";
    }

}
