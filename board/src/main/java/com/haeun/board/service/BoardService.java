package com.haeun.board.service;

import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.request.board.PatchBoardRequestDto;
import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.request.board2.PatchBoardRequestDto2;
//동일한 이름의 java파일을 import를 하지 못 함
//import com.haeun.board.dto.request.board2.PostBoardRequestDto;
import com.haeun.board.dto.request.board2.PostBoardRequestDto2;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.board.GetBoardListResponseDto;
import com.haeun.board.dto.response.board.GetBoardResponseDto;
//* 인터페이스를 컨트롤러에 주입을 하게 되는데 주의점 : 해당 인터페이스를 구현한 클래스가 없으면 주입이 안 됨
public interface BoardService {
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto);
    public ResponseEntity<ResponseDto> postBoard(String userEmail, PostBoardRequestDto2 dto); //오버로드한 것
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber);
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList();//* Top3가  boardList에 포함되는 것이라서 GetBoardListResponseDto같은 Dto 클래스를 사용하는 것
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3();
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto);
    public ResponseEntity<ResponseDto> patchBoard(String userEmail, PatchBoardRequestDto2 dto);
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber);
}
