package com.haeun.board.service;

import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.request.user.PostUserRequestDto;
import com.haeun.board.dto.response.ResponseDto;

public interface UserService {
    
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto);

}
