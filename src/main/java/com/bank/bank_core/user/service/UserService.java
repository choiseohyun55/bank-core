package com.bank.bank_core.user.service;


import com.bank.bank_core.user.dto.UserSignupRequest;
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
    public Long signup(UserSignupRequest request){
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
        return savedUser.getId();
    }
}
