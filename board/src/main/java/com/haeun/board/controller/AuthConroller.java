package com.haeun.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haeun.board.dto.request.auth.SignInRequestDto;
import com.haeun.board.dto.request.auth.SignUpRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.auth.SignInResponseDto;
import com.haeun.board.service.AuthService;

// 인증(인증 서버),파일(정적 리소스 서버), 소켓(소켓서버)들은 CRUD가 아닌 
// 이러한 아이들은 서버를 전부 다 따로 둔다고함 그래서 api라는 단어를 안 붙인다고 함 지금은 서버를 두고 사용하는 것이 아니라 요청 path에 api를 붙이고 있는 거
@RestController
@RequestMapping("api/v2/auth")
public class AuthConroller {
    
    private AuthService authService;

    @Autowired
    public AuthConroller(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("sign-up")
    public ResponseEntity<ResponseDto> signUp(
       @Valid @RequestBody SignUpRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
        @Valid @RequestBody SignInRequestDto requestBody
    ) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    } 
}
