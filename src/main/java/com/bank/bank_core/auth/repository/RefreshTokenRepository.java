package com.bank.bank_core.auth.repository;

import com.bank.bank_core.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    // 토큰 값으로 RefreshToken 객체 조회 (토큰 재발급 검증용)
    Optional<RefreshToken> findByToken(String token);

    // 회원의 RefreshToken 삭제 (로그아웃용)
    void deleteByUserId(Long userId);
}