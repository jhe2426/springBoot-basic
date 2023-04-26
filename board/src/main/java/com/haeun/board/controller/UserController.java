package com.haeun.board.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haeun.board.dto.request.user.PostUserRequestDto;
import com.haeun.board.dto.response.ResponseDto;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @PostMapping("") 
    public ResponseEntity<ResponseDto> postUser(
        @Valid @RequestBody PostUserRequestDto requestBody
    ) {
        return null;
    }
}
