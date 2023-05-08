package com.haeun.board.dto.request.board2;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardRequestDto2 {
    //JWT 토큰으로 인증및 인가를 해줄 것임
    //@NotBlank
    // @Email
    // private String boardWriterEmail;
    @NotBlank
    private String boardTitle;
    @NotBlank
    private String boardContent;
    private String boardImageUrl;
}
