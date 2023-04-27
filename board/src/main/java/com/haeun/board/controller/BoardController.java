package com.haeun.board.controller;
//* 클라이언트, 컨트롤, 서비스 각 각의 레이아웃의 데이터 이동은 DTO를 이용한다. 
//* 컨트롤러가 클라이언트와 서비스의 가운데 위치에 존재하므로 컨트롤러 안에 DTO폴더를 넣어서 사용한다.
//* 서비스, 레포지토리, 데이터베이스,각 각의 레이아웃의 데이터 이동은 Entity를 이용한다.
//* 레포지토리가 서비스와 데이터베이스의 가운데 위치에 존재하므로 컨트롤러 안에 Entity폴더를 넣어서 사용한다.

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.haeun.board.dto.response.board.GetBoardListResponseDto;
import com.haeun.board.dto.response.board.GetBoardResponseDto;
import com.haeun.board.dto.request.board.PatchBoardRequestDto;
import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.response.ResponseDto;

import com.haeun.board.service.BoardService;

@RestController
@RequestMapping("/api/v1/board")
public class BoardController {
    
    private BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //* 1. 게시물 작성 */
    @PostMapping("")
    public ResponseEntity<ResponseDto> postBoard(
        @Valid @RequestBody PostBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = boardService.postBoard(requestBody);
        return response;
    }

    //* 2. 특정 게시물 조회 */
    // @GetMapping("/{boardNumber}") //pathvariable로 받는 변수는 Dto로 받을 필요가 없음
    // public ResponseEntity<? super GetBoardListResponseDto> getBoard(
    //     @PathVariable("boardNumber") Integer boardNumber
    // ){
    //     ResponseEntity<? super GetBoardListResponseDto> response = boardService.getBoard(boardNumber);
    //     return response;
    // }

        @GetMapping("/{boardNumber}")
        public ResponseEntity<? super GetBoardResponseDto> getBoard(
            @PathVariable("boardNumber") Integer boardNubmer
        ){
            ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardNubmer);
            return response;
        }

    //* 3. 게시물 목록 조회 */
    @GetMapping("/list")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        ResponseEntity<? super GetBoardListResponseDto> response = 
        boardService.getBoardList();

        return response;
    }

    //* 4. top3 게시물 목록 조회 */
    @GetMapping("/top3")
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3() {
        ResponseEntity<? super GetBoardListResponseDto> response =  boardService.getBoardTop3();
        return response;
    }

    //* 5. 특정 게시물 수정 */
    @PatchMapping("")
    public ResponseEntity<ResponseDto> patchBoard(
        @Valid @RequestBody PatchBoardRequestDto requestBody
    ) {
        ResponseEntity<ResponseDto> response = boardService.patchBoard(requestBody);
        return response;
    }

    //* 6. 특정 게시물 삭제 */
    @DeleteMapping("/{userEmail}/{boardNumber}")
    public ResponseEntity<ResponseDto> deleteBoard(
        @PathVariable("userEmail") String userEmail, @PathVariable("boardNumber") Integer boardNumber
    ){
        ResponseEntity<ResponseDto> response = boardService.deleteBoard(userEmail, boardNumber);
        return response;
    }


    //board/{boardNumber} 
    //board/list
    //board/top3
    //셋 다 같은 url패턴이므로 만약  board/{boardNumber}만 계속 읽게 되면 
    //board/{boardNumber} 의 순서를 맨 마지막에 위치하도록 하면 //board/list, //board/top3가 읽혀진다.
    //컴퓨터는 위에서부터 밑으로 코드를 읽기 때문이다.
}
