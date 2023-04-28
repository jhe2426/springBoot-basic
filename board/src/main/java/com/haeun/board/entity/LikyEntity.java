package com.haeun.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.haeun.board.entity.primaryKey.LikyPk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Liky")
@Table(name = "Liky")
@IdClass(LikyPk.class) //복합 키 타입을 알려주는 어노테이션
public class LikyEntity {
    @Id
    private int boardNumber;    
    @Id
    private String userEmail;
    private String userNickname;
    private String userProfileImageUrl;
}
