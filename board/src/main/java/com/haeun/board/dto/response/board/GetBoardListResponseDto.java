package com.haeun.board.dto.response.board;

import java.util.List;

import com.haeun.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
//* 같은 Dto 멤버 변수를 가지지만 기능이 다르다고 하면 Dto클래스를 따로 만들어야 함
public class GetBoardListResponseDto extends ResponseDto {
    private List<BoardSummary> boardList;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class BoardSummary{
    private int boardNumber;
    private String boardTitle;
    private String boardContent;
    private String boardImageUrl;
    private String boardWriteDatetime;
    private int viewCount;
    private String boardWriterEmail;
    private String boardWriterNickname;
    private String boardWriterProfileImageUrl;
    private int commentCount;
    private int likeCount;
}