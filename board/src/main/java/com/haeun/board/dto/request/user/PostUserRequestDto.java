package com.haeun.board.dto.request.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostUserRequestDto {
    @NotBlank //NULL 공백 띄어쓰기 안 됨
    @Email
    private String userEmail;
    @NotBlank
    private String userPassword;
    @NotBlank
    private String userNickname;
    @NotBlank
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$") //개발자 지정 유효성 검사 작성 하는 거
    private String userPhoneNumber;
    @NotBlank
    private String userAddress;
    private String userProfileImageUrl;
}
