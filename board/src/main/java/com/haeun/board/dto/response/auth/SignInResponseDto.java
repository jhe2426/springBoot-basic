package com.haeun.board.dto.response.auth;

import com.haeun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDto  extends ResponseDto{
    private String token;
    private int expirationDate;

    public SignInResponseDto(String token) {
        super("SU", "Success");
        this.token = token;
        this.expirationDate = 3600; //초 단위로 한 시간임
    }
}
