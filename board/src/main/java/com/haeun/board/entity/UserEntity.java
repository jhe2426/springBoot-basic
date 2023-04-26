package com.haeun.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "User")
public class UserEntity {
    @Id
    private String email;
    private String password;
    private String nickname;
    private String phoneNumber;
    private String address;
    private String consentPersonalInformation; //자바에서는 카멜 케이스로 사용해도 알아서 JPA가 유추해서 데이터베이스에서는 단어의 띄어쓰기를 _로 구분을 해줌
    private String profileImageUrl; // 데이터 베이스에 _바로 작성된 것을 자바에서는 카멜 케이스로 변수명을 작성해주면 된다.!
}
