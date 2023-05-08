package com.haeun.board.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.haeun.board.provider.JwtProvider;
import com.haeun.board.repository.UserRepository;
import com.haeun.board.service.AuthService;

@Service
public class AuthServiceImplement implements AuthService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    @Autowired
    public AuthServiceImplement(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        
        String email = dto.getUserEmail();
        String nickname = dto.getUserNickname();
        String phoneNumber = dto.getUserPhoneNumber();
        String password = dto.getUserPassword();
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

            //비밀번호 암호화하는 작업
            String encodedPassword = passwordEncoder.encode(password);
            dto.setUserPassword(encodedPassword);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
             exception.printStackTrace();
             return CustomResponse.databaseError();
        }

        return CustomResponse.succes();

    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        
        SignInResponseDto body = null;

        String email = dto.getUserEmail();
        String password = dto.getUserPassword();

        try {
            // TODO: 로그인 실패 반환 (이메일 X(틀렸을 경우))
            //유저의 정보를 가져오는 거
            UserEntity userEntity = userRepository.findByEmail(email);
            if(userEntity == null) return CustomResponse.signInFailed();

            // TODO: 로그인 실패 반환 (패스워드 X(틀렸을 경우))
            String encordedPassword = userEntity.getPassword();
            //첫 번쨰 인자가 평문의(사용자가 입력한) 패스워드임, 두 번째 인자가 인코딩된  패스워드임
            boolean equaledPassword = passwordEncoder.matches(password, encordedPassword);
            if (!equaledPassword) return CustomResponse.signInFailed();
            
            //*토큰 생성하기
            String jwt = jwtProvider.create(email);
            body = new SignInResponseDto(jwt);

        } catch (Exception exception) {
           exception.printStackTrace();
           return CustomResponse.databaseError();
        }

        return ResponseEntity.status(HttpStatus.OK).body(body);
    }
    
}
