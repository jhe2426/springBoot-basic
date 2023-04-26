package com.haeun.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.haeun.board.dto.request.user.PostUserRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("") 
    public ResponseEntity<ResponseDto> postUser(
        @Valid @RequestBody PostUserRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = userService.postUser(requestBody);
        return response;
    }
}
