package com.haeun.firstproject.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.haeun.firstproject.provider.JwtTokenProvider;
import com.haeun.firstproject.provider.UserRole;
import com.haeun.firstproject.service.MainService;

// @Component 서버의 Bean을 나열해 놓는 곳에 해당 객체가 올라갈 수 있게 해주는 어노테이션 @Autowired로 주입을 할 수 있게 됨 
// @Componen 패키지 스캔 안에 이 어노테이션은 "이 클래스를 정의했으니 빈으로 등록해줘." 라는 뜻
@Service // Component인데 개발자에게 여기 클래스는 Service작업을 한다를 알려주기 위해서 사용된다고 함
public class MainServiceImplement implements MainService{

    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    public MainServiceImplement(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String hello() {
       return "Hello";
    }

    //JWT 생성하는 서비스 여기서 role을 추가해주면 되는 것 role을 주소에서 값을 받아오는 것은 아님
    @Override
    public String getJwt(String data) {
        int role = 1;
        String jwt = jwtTokenProvider.create(data,role);
        return jwt;
    }

    @Override
    public UserRole validJwt(String jwt) {
       UserRole subject = jwtTokenProvider.validate(jwt);
       return subject;
    }
    

}
