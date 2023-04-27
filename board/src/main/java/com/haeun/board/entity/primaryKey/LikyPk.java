package com.haeun.board.entity.primaryKey;

import java.io.Serializable;

import javax.persistence.Column;

import org.springframework.stereotype.Component;

import lombok.Data;


@Data
public class LikyPk implements Serializable { // 복합 키 타입 생성하는 방법 PK형태의 타입을 만든 것
    
    @Column(name = "board_number")
    private int boardNumber;

    @Column(name =  "user_email")
    private String userEmail;
}
