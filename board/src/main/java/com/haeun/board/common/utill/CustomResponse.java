package com.haeun.board.common.utill;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.haeun.board.dto.response.ResponseDto;

//* common 폴더 자주 사용하는 코드를 여기에 작성하면 된다고 함
public class CustomResponse {

    public static ResponseEntity<ResponseDto> databaseError() {
        ResponseDto errorBody = new ResponseDto("DE","Database Error");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> vaildationFaild() {
        ResponseDto errorBody = new ResponseDto("VF", "Request Parameter Validation Failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }

    public static ResponseEntity<ResponseDto> notExistBoardNumber() {
        ResponseDto errorBody = new ResponseDto("NB","Non-Existent Board Number" );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorBody);
    }
}
