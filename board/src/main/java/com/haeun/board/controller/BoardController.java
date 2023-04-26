package com.haeun.board.controller;
//* 클라이언트, 컨트롤, 서비스 각 각의 레이아웃의 데이터 이동은 DTO를 이용한다. 
//* 컨트롤러가 클라이언트와 서비스의 가운데 위치에 존재하므로 컨트롤러 안에 DTO폴더를 넣어서 사용한다.
//* 서비스, 레포지토리, 데이터베이스,각 각의 레이아웃의 데이터 이동은 Entity를 이용한다.
//* 레포지토리가 서비스와 데이터베이스의 가운데 위치에 존재하므로 컨트롤러 안에 Entity폴더를 넣어서 사용한다.

import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    
}
