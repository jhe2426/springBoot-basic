package com.haeun.board.common.utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.response.ResponseDto;
//* ResponseEntity와 개발자가 만든 code,message,data를 멤버변수로 가지는 클래스 사용하는 이유
// 개발자가 만든 Response클래스를 사용하는 이유는  프론트엔드가 작업하기 편해짐  
// ResponseEntity를 사용하면 에러에 대한 코드가 error 부분에 전부 다 가게 되므로 프론트하는 사람이 다시 error변수를
// if문으로 각 각의 오류 코드를 찾아내서 반환을 해줘야 한다고 함
// 프론트엔드에서 작업을 할 경우 ResponseEntity말고 개발자가 따로 Response클래스를 사용하면 
// 모든 Response가 error 부분으로 들어가는 것이 아닌 sucess부분으로 들어가게 되어 error부분에서 따로 구문을 줘서 찾을 필요가 없어지는 편리함이 생김




//* common 폴더 자주 사용하는 코드를 여기에 작성하면 된다고 함
//* 이 메서드는 재사용을 하기 위해서 사용하는 방법도 맞지만 저 메서드는 고객과 개발자들과의 약속이므로 인터페이스로 만드는 것이 맞다
//* 인터페이스의 제한 메서드의 선언부만 존재할 수 있다. 구현체를 작성할 수 없으므로 한 줄 변수로 만들어서 상수로 정의 하면 된다.
public class CustomResponse {

    // 메서드 정리를 코드 번호 순으로 하면 좋음 400, 401, ...

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

    public static ResponseEntity<ResponseDto> existUserEmail() {
        ResponseDto errorBody = new ResponseDto("EU","Existent User Email" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> existUserNickname() {
        ResponseDto errorBody = new ResponseDto("EN","Existent User Nickname" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> existUserPhoneNumber() {
        ResponseDto errorBody = new ResponseDto("EP","Existent User Phone Number" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoardNumber() {
        ResponseDto errorBody = new ResponseDto("NB","Non-Existent Board Number" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> signInFailed() {
        ResponseDto errorBody = new ResponseDto("SF","Sign In Failed" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody); //인가를 할 수 없다. : 너가 누구인지는 알지만 권한이 없다.
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
