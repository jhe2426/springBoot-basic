package com.haeun.board.service;

import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.request.auth.SignInRequestDto;
import com.haeun.board.dto.request.auth.SignUpRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.auth.SignInResponseDto;

public interface AuthService {
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
