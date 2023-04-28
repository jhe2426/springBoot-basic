package com.haeun.board.common.utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.response.ResponseDto;

//* common 폴더 자주 사용하는 코드를 여기에 작성하면 된다고 함
//* 이 메서드는 재사용을 하기 위해서 사용하는 방법도 맞지만 저 메서드는 고객과 개발자들과의 약속이므로 인터페이스로 만드는 것이 맞다
//* 인터페이스의 제한 메서드의 선언부만 존재할 수 있다. 구현체를 작성할 수 없으므로 한 줄 변수로 만들어서 상수로 정의 하면 된다.
public class CustomResponse {

    public static ResponseEntity<ResponseDto> succes() {
        ResponseDto body = new ResponseDto("SU", "SUCCES");
        return ResponseEntity.status(HttpStatus.OK).body(body);
    }

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto errorBody = new ResponseDto("DE","Database Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
        //아래와 같이 한 줄로 만들 수 있음
        //return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body( new ResponseDto("DE","Database Error"));
    }

    public static ResponseEntity<ResponseDto> vaildationFaild() {
        ResponseDto errorBody = new ResponseDto("VF", "Request Parameter Validation Failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoardNumber() {
        ResponseDto errorBody = new ResponseDto("NB","Non-Existent Board Number" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistUserEmail() {
        ResponseDto errorBody = new ResponseDto("NU","Non-Existent User Email" );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorBody); // 인증을 할 수 없다 : 너가 누구인지 모른다.
    }

    public static ResponseEntity<ResponseDto> noPermissions() {
        ResponseDto errorBody = new ResponseDto("NP","No Permissions" );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorBody); //인가를 할 수 없다. : 너가 누구인지는 알지만 권한이 없다.
    }
}
