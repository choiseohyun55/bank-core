package com.bank.bank_core.user.service;


import com.bank.bank_core.auth.dto.TokenResponse;
import com.bank.bank_core.auth.entity.RefreshToken;
import com.bank.bank_core.auth.repository.RefreshTokenRepository;
import com.bank.bank_core.global.jwt.JwtTokenProvider;
import com.bank.bank_core.user.dto.UserLoginRequest;
import com.bank.bank_core.user.dto.UserLoginResponse;
import com.bank.bank_core.user.dto.UserSignupRequest;
import com.bank.bank_core.user.dto.UserSignupResponse;
import com.bank.bank_core.user.entity.User;
import com.bank.bank_core.user.repository.UserRepository;
import jdk.jshell.spi.ExecutionControl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 회원가입 로직
    @Transactional
    public UserSignupResponse signup(UserSignupRequest request){
        // 이메일 중복검증
        if(userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        // 비밀번호 암호화 처리
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 유저 엔티티 생성 및 저장
        User user = request.toEntity(encodedPassword);
        User savedUser = userRepository.save(user);

        // 생성된 회원 아이디 반환
        return UserSignupResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public TokenResponse login(UserLoginRequest request){
        // 이메일 존재 여부 확인
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // Access Token , Refresh Token 생성
        String accessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getId());

        // 만료시간 설정(7일 뒤 만료)
        LocalDateTime expiredAt = LocalDateTime.now().plusDays(7);


        // Refresh Token DB 저장 (기존 토큰이 있으면 업데이트, 없으면 new)
        RefreshToken tokenEntity = refreshTokenRepository.findByUserId(user.getId())
                .map(entity -> {
                    entity.updateToken(refreshToken, expiredAt);
                    return entity;
                })
                .orElseGet(() -> new RefreshToken(user.getId(), refreshToken, expiredAt));

        refreshTokenRepository.save(tokenEntity);

        // Token 응답 반환
        return TokenResponse.of(accessToken, refreshToken);
    }
}
