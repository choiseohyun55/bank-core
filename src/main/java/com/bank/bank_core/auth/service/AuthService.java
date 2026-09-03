package com.bank.bank_core.auth.service;

import com.bank.bank_core.auth.dto.ReissueRequest;
import com.bank.bank_core.auth.dto.TokenResponse;
import com.bank.bank_core.auth.entity.RefreshToken;
import com.bank.bank_core.auth.repository.RefreshTokenRepository;
import com.bank.bank_core.global.jwt.JwtTokenProvider;
import com.bank.bank_core.user.entity.User;
import com.bank.bank_core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse reissue(ReissueRequest request) {
        String requestRefreshToken = request.getRefreshToken();
        // refresh token 유효성 검증
        if(!jwtTokenProvider.validateToken(requestRefreshToken)){
            throw new IllegalArgumentException("만료되거나 유효하지 않은 RefreshToken입니다.");
        }
        // DB 존재 여부 확인
        RefreshToken refreshToken = refreshTokenRepository.findByToken(requestRefreshToken).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 Token입니다."));
        // 유저 정보 조회
        User user = userRepository.findById(refreshToken.getUserId()).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        // 새 Access Token 및 Refresh Token 발급
        String newAccessToken = jwtTokenProvider.createAccessToken(user.getId(), user.getEmail());
        String newRefreshToken = jwtTokenProvider.createRefreshToken(user.getId());
        // DB의 Refresh Token 갱신
        LocalDateTime newExpiredAt = LocalDateTime.now().plusDays(7);
        refreshToken.updateToken(newRefreshToken, newExpiredAt);

        return TokenResponse.of(newAccessToken, newRefreshToken);
    }
}
