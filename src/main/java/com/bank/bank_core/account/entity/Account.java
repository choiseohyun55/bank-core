package com.bank.bank_core.account.entity;

import com.bank.bank_core.common.entity.BaseEntity;
import com.bank.bank_core.user.entity.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    private Long balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Builder
    public Account(User user, String accountNumber, Long balance) {
        this.user = user;
        this.accountNumber = accountNumber;
        this.balance = balance != null ? balance : 0L;
        this.status = AccountStatus.ACTIVE;
    }
    //입금 메서드
    public void deposit(Long amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("입금 금액은 0원보다 커야합니다.");
        }
        this.balance += amount;
    }

    public void withdraw(Long amount) {
        if(amount <= 0) {
            throw new IllegalArgumentException("출금 금액은 0원보다 커야합니다.");
        }
        if(this.balance < amount) {
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        this.balance -= amount;
    }

    public enum AccountStatus {
        ACTIVE, CLOSED
    }
}
