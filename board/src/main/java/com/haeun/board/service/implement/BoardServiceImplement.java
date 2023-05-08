package com.haeun.board.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haeun.board.common.utill.CustomResponse;
import com.haeun.board.dto.request.board.PatchBoardRequestDto;
import com.haeun.board.dto.request.board.PostBoardRequestDto;
import com.haeun.board.dto.request.board2.PatchBoardRequestDto2;
import com.haeun.board.dto.request.board2.PostBoardRequestDto2;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.board.GetBoardListResponseDto;
import com.haeun.board.dto.response.board.GetBoardResponseDto;
import com.haeun.board.entity.BoardEntity;
import com.haeun.board.entity.CommentEntity;
import com.haeun.board.entity.LikyEntity;
import com.haeun.board.entity.UserEntity;
import com.haeun.board.entity.resultSet.BoardListResultSet;
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
        String boardWriterEmail = dto.getBoardWriterEmail();
        PostBoardRequestDto2 dto2 = new PostBoardRequestDto2(dto);

        // 밑에 똑깥은 로직이 있으므로 오버로드한  메서드를 호출해서 사용한 것
        ResponseEntity<ResponseDto> response = postBoard (boardWriterEmail, dto2);

        // TODO : 성공 반환
        return response ; 
    }

    
    //* Board2의 게시물 작성
    @Override
    public ResponseEntity<ResponseDto> postBoard(String userEmail, PostBoardRequestDto2 dto) {

        ResponseDto body = null;


        try {
            // TODO : 존재하지 않는 유저 오류 반환
            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if(!existedUserEmail) {
                ResponseDto  errorBody = new ResponseDto("NU", "Non-Existent User Email"); 
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody); // UNAUTHORIZED : 401에러
            }
            
            BoardEntity boardEntity = new BoardEntity(userEmail, dto);
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

            if (boardNumber == null) return CustomResponse.vaildationFaild(); //이 메서드는 재사용을 하기 위해서 사용하는 방법도 맞지만 저 메서드는 고객과 개발자들과의 약속이므로 인터페이스로 만드는 것이 맞다
            // 인터페이스의 제한 메서드의 선언부만 존재할 수 있다. 구현체를 작성할 수 없으므로 한 줄 변수로 만들어서 정의 하면 된다.


             // TODO : 존재하지 않는 게시물 번호
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            //이런식으로 데이터베이스에 받오는 값이 null인 값을 받아 올 수 있는 것이 개발자가 작성한 쿼리 메서드를 사용해서 가능한 것이다.
            if (boardEntity == null) return CustomResponse.notExistBoardNumber();

            // TODO : 게시물 조회 했을 시 viewCount 증가
            int viewCount = boardEntity.getViewCount();
            boardEntity.setViewCount(viewCount + 1);
            boardRepository.save(boardEntity);
        
            String boardWriterEmail = boardEntity.getWriterEmail();
            UserEntity userEntity = userRepository.findByEmail(boardWriterEmail); // 게시물을 작성한 유저의 정보를 데이터베이스에서 가져오는 거
            //* 매개 변수를 집어 넣을 때 항상 데이터 타입을 잘 검증을 해줘야 함 매개변수 boardNumber은 Integer인데 각 각의 레포지토리에서 boardNumber을 받을 때는 
            //* int형임 Integer가 int형으로 형변환은 되지만 문제가 되는 것은 int는 null값을 받지 못하므로 매겨변수로 들어오는 boardNumber의 null값이 들어올 수 있는 경우를 예외 처리 해줘야 함
            List<CommentEntity>  commentEntities = commentRepository.findByBoardNumber(boardNumber);
            List<LikyEntity> likyEntities = likyRepository.findByBoardNumber(boardNumber);

            body = new GetBoardResponseDto(boardEntity, userEntity, commentEntities, likyEntities);

        } catch (Exception exception) {
            // TODO : 데이터베이스  오류 반환
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }


        return ResponseEntity.status(HttpStatus.OK).body(body);

    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardList() {
        GetBoardListResponseDto body = null;

        try {

            List<BoardListResultSet> resultSet = boardRepository.getList();
            body = new GetBoardListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    @Override
    public ResponseEntity<? super GetBoardListResponseDto> getBoardTop3() {
        GetBoardListResponseDto body = null;

        try {
            
            List<BoardListResultSet> resultSet = boardRepository.getTop3List();
            body = new GetBoardListResponseDto(resultSet);

        } catch (Exception exception) {
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    //* Board  특정 게시물 수정
    @Override
    public ResponseEntity<ResponseDto> patchBoard(PatchBoardRequestDto dto) {
        //같은 서비스 요청에 url가 다를 때 어떻게 처리해야하나 : Controller에서 처리를 해야한다.(그러면 Controller에 연산작업이 있어도 되냐)
        // 서비스에서 작업을 처리하면 매개변수가 여러가지 일 때 불편해진다, 관리해야하는 메서드의 수가 많이 늘어나게 된다. (그래도 규칙을 준수하는 것이 나은 거 같음)
        // 하지만 장점으로는 서비스에서만 연산 처리가 되어야한다는 규칙을 지킬 수 있다.
        String userEmail = dto.getUserEmail();
        PatchBoardRequestDto2 dto2 = new PatchBoardRequestDto2(dto);//서비스에 같은 서비스 요청에 대해 가공을 한 것(서비스에서만 연산 처리가 되어야한다는 규칙을 지킬 수 있다.)
       //* 컨트롤러에서 해당 작업을 하고 싶으면 PatchBoardRequestDto2 dto2 = new PatchBoardRequestDto2(dto);이거만 해당 컨트롤러에 추가를 해주면 됨
        ResponseEntity<ResponseDto> response = patchBoard(userEmail, dto2);

        return response;
    }

    //* Board2 특정 게시물 수정
    @Override
    public ResponseEntity<ResponseDto> patchBoard(String userEmail, PatchBoardRequestDto2 dto) {
        
        int boardNumber = dto.getBoardNumber();
        String boardTitle = dto.getBoardTitle();
        String boardContent = dto.getBoardContent();
        String boardImageUrl = dto.getBoardImageUrl();
        try {

            // TODO: 존재하지 않는 게시물 번호 반환
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return CustomResponse.notExistBoardNumber();

            // TODO: 존재하지 않는 유저 이메일 반환
            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if (!existedUserEmail) return CustomResponse.notExistUserEmail();

            // TODO: 권한 없음
            boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
            if (!equalWriter) return CustomResponse.noPermissions();//같지 않으면 if문에 들어 오게 됨

            //boardEntity에 메서드를 만들어 메서드를 받아오는 식으로 하면 코드가 더욱 깔끔해짐
            boardEntity.setTitle(boardTitle);
            boardEntity.setContent(boardContent);
            boardEntity.setBoardImageUrl(boardImageUrl);

            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            // TODO : 데이터베이스  오류 반환
           exception.printStackTrace();
           return CustomResponse.databaseError();
        }

        return CustomResponse.succes();
    }



    //* Board, Board2 특정 게시물 삭제
    @Override
    public ResponseEntity<ResponseDto> deleteBoard(String userEmail, Integer boardNumber) { 

        try {
            if (boardNumber == null) return CustomResponse.vaildationFaild(); //얘는 사용자로 부터 입력 받은 값이 없는 것이므로 vaildationFaild 오류인 것이다.

            // TODO: 존재하지 않는 게시물 번호 반환
            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return CustomResponse.notExistBoardNumber();

            // TODO: 존재하지 않는 유저 이메일 반환
            boolean existedUserEmail = userRepository.existsByEmail(userEmail);
            if (!existedUserEmail) return CustomResponse.notExistUserEmail(); 

            // TODO: 권환 없음 반환
            boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
            if(!equalWriter) return CustomResponse.noPermissions();

            // comment와 liky가 board를 참조하고 있기 때문에 board을 지울 때 지워지지 않는 것이다.
            // 그래서 comment와 liky를 먼저 지워주고 board를 지워줘야지 정상적으로 지워진다.
            commentRepository.deleteByBoardNumber(boardNumber);
            
            likyRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);

        } catch (Exception exception) {
            // TODO : 데이터베이스 오류 반환
            exception.printStackTrace();
            return CustomResponse.databaseError();
        }

        return CustomResponse.succes();

    }
    
}
