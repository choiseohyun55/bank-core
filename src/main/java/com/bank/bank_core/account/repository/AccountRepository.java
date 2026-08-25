package com.bank.bank_core.account.repository;

import com.bank.bank_core.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // 계좌번호로 계좌정보 조회
    Optional<Account> findByAccountNumber(String accountNumber);
    // 회원이 보유한 모든 계좌 목록 조회
    List<Account> findByUserId(Long userId);
    // 이미 존재하는 계좌번호인지 중복 체크
    boolean existsByAccountNumber(String accountNumber);
}
