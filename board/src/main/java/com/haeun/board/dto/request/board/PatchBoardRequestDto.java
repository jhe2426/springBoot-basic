package com.haeun.board.dto.request.board;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatchBoardRequestDto {
    @NotBlank
    @Email
    private String userEmail;
    @NotNull
    private Integer boardNumber; //왜 Integer형?? 실수 값으로 받으려고 Integer을 @NotNull어노테이션을 사용하고 싶어서 int는 기본형 타입이라서 Null이라는 게 없음 그래서 Integer로 사용한 것
    //boardNumber 빈 값인지 유효성 검사를 하기 위해 Integer 참조형 변수 타입으로 선언한 것
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private String boardImageUrl;
}
