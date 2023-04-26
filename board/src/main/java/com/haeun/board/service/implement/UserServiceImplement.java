package com.haeun.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.haeun.board.dto.request.user.PostUserRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.entity.UserEntity;
import com.haeun.board.repository.UserRepository;
import com.haeun.board.service.UserService;

@Service
public class UserServiceImplement implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public ResponseEntity<ResponseDto> postUser(PostUserRequestDto dto){

        ResponseDto responseBody = null;
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        try {

            // TODO : 이메일 중복 반환
            boolean hasEmail = userRepository.existsByEmail(email);
            //이메일이 존재한다면
            if(hasEmail) {
                responseBody = new ResponseDto("EU", "Existent User Email");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }

            // TODO : 닉네임 중복 반환
            boolean hasNickname = userRepository.existsByNickname(nickname);
            if(hasNickname) {
                responseBody = new ResponseDto("EN", "Existent User Nickname");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }

            // TODO : 휴대전화번호 중복 반환
            boolean hasPhoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if(hasPhoneNumber) {
                responseBody = new ResponseDto("EP", "Existent User Phone Number");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
            }

            // dto에 받은 내용을 디비에 넣으려면 Entity클래스에 다시 넣어야 하는데 그 코드를 여기에 작성하면 불 필요하게 코드의 길이가  늘어나게 되므로
            // Entity클래스를 생성할 때 dto에 있는 내용을 집어 넣어 데이터 베이스에 넣을 수 있는 방향으로 코드를 작성한 것
            //* 유저 레코드 삽입
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);
            
            responseBody = new ResponseDto("SU", "Success");
            
        }catch (Exception exception) {
            // TODO : 데이터베이스 오류 반환
            exception.printStackTrace();
            responseBody = new ResponseDto("DE", "Database Error");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody); //INTERNAL_SERVER_ERROR : 500코드임
        }

       


        

        // TODO : 성공 반환
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
