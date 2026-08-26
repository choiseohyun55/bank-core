package com.bank.bank_core.user.service;


import com.bank.bank_core.user.dto.UserLoginRequest;
import com.bank.bank_core.user.dto.UserLoginResponse;
import com.bank.bank_core.user.dto.UserSignupRequest;
import com.bank.bank_core.user.dto.UserSignupResponse;
import com.bank.bank_core.user.entity.User;
import com.bank.bank_core.user.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    // 회원가입 로직
    @Transactional
    public UserSignupResponse signup(UserSignupRequest request){
        // 이메일 중복검증
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // 비밀번호 암호화 처리
        String encodedPassword = request.getPassword();

        // 유저 엔티티 생성 및 저장
        User user = request.toEntity(encodedPassword);
        User savedUser = userRepository.save(user);

        // 생성된 회원 아이디 반환
        return UserSignupResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public UserLoginResponse login(UserLoginRequest request){
        // 이메일 존재 여부 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        // 비밀번호 일치 검증
        if(!user.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // DTO 변환 후 반환
        return UserLoginResponse.from(user);
    }
}
