package com.haeun.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.haeun.board.common.utill.CustomResponse;
import com.haeun.board.dto.request.auth.SignInRequestDto;
import com.haeun.board.dto.request.auth.SignUpRequestDto;
import com.haeun.board.dto.response.ResponseDto;
import com.haeun.board.dto.response.auth.SignInResponseDto;
import com.haeun.board.entity.UserEntity;
import com.haeun.board.repository.UserRepository;
import com.haeun.board.service.AuthService;

@Service
public class AuthServiceImplement implements AuthService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImplement(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();

        try {

            // TODO: 존재하는 유저 이메일 반환
            boolean existedUserEmail = userRepository.existsByEmail(email);
            if (existedUserEmail) return CustomResponse.existUserEmail();

            // TODO: 존재하는 유저 닉네임 반환
            boolean existedUserNickname = userRepository.existsByNickname(nickname);
            if (existedUserNickname) return CustomResponse.existUserNickname();

            // TODO: 존재하는 유저 휴대폰 번호 반환
            boolean existedUserphoneNumber = userRepository.existsByPhoneNumber(phoneNumber);
            if (existedUserphoneNumber) return CustomResponse.existUserPhoneNumber();

            

        } catch (Exception exception) {
             exception.printStackTrace();
             return CustomResponse.databaseError();
        }

        return CustomResponse.succes();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
    }
    
}
