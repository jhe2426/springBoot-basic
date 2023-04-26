package com.haeun.board.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.haeun.board.dto.request.user.PostUserRequestDto;

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
    private boolean consentPersonalInformation; //자바에서는 카멜 케이스로 사용해도 알아서 JPA가 유추해서 데이터베이스에서는 단어의 띄어쓰기를 _로 구분을 해줌
    private String profileImageUrl; // 데이터 베이스에 _바로 작성된 것을 자바에서는 카멜 케이스로 변수명을 작성해주면 된다.!

    public UserEntity(PostUserRequestDto dto) { // 서비스에서 dto에 있는 각 각의 멤버 필드를 다시 Entity클래스에 대입하는 것은 쓸데 없이 코드의 길이를 길게 하므로 Entity를 생성할 때 dto의 멤버 변수가 대입대도록 만든 것
        this. email = dto.getUserEmail();
        this.password = dto.getUserPassword();
        this.nickname = dto.getUserNickname();
        this.phoneNumber = dto.getUserPhoneNumber();
        this.address = dto.getUserAddress();
        this.consentPersonalInformation = true;
        this.profileImageUrl = dto.getUserProfileImageUrl();
    }


}
