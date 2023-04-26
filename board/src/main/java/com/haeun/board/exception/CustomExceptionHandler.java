package com.haeun.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haeun.board.dto.response.ResponseDto;

@RestControllerAdvice // 컨트롤러 유효성 검사 처리에 대한 설정을 할 수 있음
//* @RestControllerAdvice :  모든 @RestController에 대한, 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.
public class CustomExceptionHandler {
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        ResponseDto responseBody = new ResponseDto("VF", "Request Parameter Validation Failed");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
