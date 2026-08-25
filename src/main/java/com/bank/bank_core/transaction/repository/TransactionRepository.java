package com.bank.bank_core.transaction.repository;

import com.bank.bank_core.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // 계좌의 전체 거래 내역 조회 (출금계좌 OR 입금계좌 조건, 최신순 정렬)
    List<Transaction> findByFromAccountIdOrToAccountIdOrderByIdDesc(Long fromAccountId, Long toAccountId);
}