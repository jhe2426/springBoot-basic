package com.haeun.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.entity.resultSet.BoardListResultSet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
//@AllArgsConstructor List<BoardSummary>의 생성자가 생기는데 List<BoardListResultSet>를 같은 타입으로 인식해 이 변수를 생성자를 통해 초기화 시키려고 한다고 컴파일에서 인식을 해서 
// @AllArgsConstructor을 삭제한 것
//* 같은 Dto 멤버 변수를 가지지만 기능이 다르다고 하면 Dto클래스를 따로 만들어야 함
public class GetBoardListResponseDto extends ResponseDto {
    private List<BoardSummary> boardList;

    public GetBoardListResponseDto(List<BoardListResultSet> resultSet) {
        super("SU", "Success");

        List<BoardSummary> boardList = new ArrayList<BoardSummary>();

        for (BoardListResultSet result: resultSet) {

        }
        
        this.boardList = boardList;
    }

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