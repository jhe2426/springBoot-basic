package com.haeun.board.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haeun.board.common.utill.CustomResponse;
import com.haeun.board.dto.request.board.PatchBoardRequestDto;
import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.board.GetBoardListResponseDto;
import com.haeun.board.dto.response.board.GetBoardResponseDto;
import com.haeun.board.entity.BoardEntity;
import com.haeun.board.entity.BoradEntity;
import com.haeun.board.entity.CommentEntity;
import com.haeun.board.entity.LikyEntity;
import com.haeun.board.entity.UserEntity;
import com.haeun.board.repository.BoardRepository;
import com.haeun.board.repository.CommentRepository;
import com.haeun.board.repository.LikyRepository;
import com.haeun.board.repository.UserRepository;
import com.haeun.board.service.BoardService;

@Service
public class BoardServiceImplement implements BoardService {

    private UserRepository userRepository;
    private BoardRepository boardRepository;
    private CommentRepository commentRepository;
    private LikyRepository likyRepository;

    @Autowired
    public BoardServiceImplement(
        UserRepository userRepository, 
        BoardRepository boardRepository,
        CommentRepository commentRepository,
        LikyRepository likyRepository
    ) {
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.likyRepository = likyRepository;
    } //스프링 공식 문서에서 이렇게 주입을 해라고 권장한다고 함 편하게는 룸북 어노테이션을 사용하면 됨(final을 선언한 변수는 자동으로 주입해주는 어노테이션을 사용하면 됨)

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
            
            BoardEntity boardEntity = new BoardEntity(dto);
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
        
        GetBoardResponseDto body = null;
        ResponseDto errorBody = null; //이렇게 만들어 주는 것이 위의 메서드에서는 직접 만들었는데 그거 보다 나은 방법임

        try {

            if (boardNumber == null) return CustomResponse.vaildationFaild();


             // TODO : 존재하지 않는 게시물 번호
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            //이런식으로 데이터베이스에 받오는 값이 null인 값을 받아 올 수 있는 것이 개발자가 작성한 쿼리 메서드를 사용해서 가능한 것이다.
            if (boardEntity == null) return CustomResponse.notExistBoardNumber();

            
        
            String boardWriterEmail = boardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByEmail(boardWriterEmail); // 게시물을 작성한 유저의 정보를 데이터베이스에서 가져오는 거
            //* 매개 변수를 집어 넣을 때 항상 데이터 타입을 잘 검증을 해줘야 함 매개변수 boardNumber은 Integer인데 각 각의 레포지토리에서 boardNumber을 받을 때는 
            //* int형임 Integer가 int형으로 형변환은 되지만 문제가 되는 것은 int는 null값을 받지 못하므로 매겨변수로 들어오는 boardNumber의 null값이 들어올 수 있는 경우를 예외 처리 해줘야 함
            List<CommentEntity>  commentEntities = commentRepository.findByBoardNumber(boardNumber);
            List<LikyEntity> likyEntities = likyRepository.findByBoardNumber(boardNumber);

        } catch (Exception exception) {
            // TODO : 데이터베이스  오류 반환
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return ResponseEntity.status(HttpStatus.OK).body(body);

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
