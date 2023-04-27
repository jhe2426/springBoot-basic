package com.haeun.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haeun.board.dto.request.board.PatchBoardRequestDto;
import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.board.GetBoardListResponseDto;
import com.haeun.board.dto.response.board.GetBoardResponseDto;
import com.haeun.board.entity.BoradEntity;
import com.haeun.board.repository.BoardRepository;
import com.haeun.board.repository.UserRepository;
import com.haeun.board.service.BoardService;

@Service
public class BoardServiceImplement implements BoardService {

    private UserRepository userRepository;
    private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImplement(
        UserRepository userRepository, 
        BoardRepository boardRepository
    ) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Override
    public ResponseEntity<ResponseDto> postBoard(PostBoardRequestDto dto) {
        ResponseDto body = null;

        String boardWriterEmail = dto.getBoardWriterEmail();

        try {
            // TODO : 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(boardWriterEmail);
            if(!existedUserEmail) {
                ResponseDto  errorBody = new ResponseDto("NU", "Non-Existent User Email"); 
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody); // UNAUTHORIZED : 401에러
            }
            
            BoradEntity boardEntity = new BoradEntity(dto);
            boardRepository.save(boardEntity);

            body = new ResponseDto("SU", "Success");

        } catch (Exception exeption) {
            // TODO : 데이터베이스  오류 반환
            exeption.printStackTrace();
            ResponseDto errorBody = new ResponseDto("DE","Database Error");
            return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        }

        // TODO : 성공 반환
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
     // 반환하는 타입이 정상적일 떄와 비정상적일 때의 경우 2가지를 받기로 설계를 했으므로 
     //GetBoardResponseDto는 ResponseDto를 상속하고 있으므로 ResponseDto형와 GetBoardResponseDto형을 같이 받을 수 있게 하기 위해서 사용한 것
     //반환이 되는 것을 명시적으로 하기 위해서 저렇게 제너릭을 한 것
    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardNumber) { //<? super GetBoardResponseDto>  : <? super T> : 와일드 카드의 하한 제한. T와 그 조상들만 가능
        throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        throw new UnsupportedOperationException("Unimplemented method 'getBoardList'");
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3() {
        throw new UnsupportedOperationException("Unimplemented method 'getBoardTop3'");
    }

    @Override
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto) {
        throw new UnsupportedOperationException("Unimplemented method 'patchBoard'");
    }

    @Override
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteBoard'");
    }
    
}
