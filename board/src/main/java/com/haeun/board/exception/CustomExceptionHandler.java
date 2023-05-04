package com.haeun.board.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.haeun.board.common.utill.CustomResponse;
import com.haeun.board.dto.response.ResponseDto;

@RestControllerAdvice // 컨트롤러 유효성 검사 처리에 대한 설정을 할 수 있음
//* @RestControllerAdvice :  모든 @RestController에 대한, 전역적으로 발생할 수 있는 예외를 잡아서 처리할 수 있다.
public class CustomExceptionHandler {
    
    //HttpMessageNotReadableException는 POST요청시 body에 아무 값도 넣지 않을 때 발생하는 예외임
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseDto> handlerHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        return CustomResponse.vaildationFaild();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handlerMethodArgumentNotValidException (MethodArgumentNotValidException  exception){
        return CustomResponse.vaildationFaild();
    }

}
