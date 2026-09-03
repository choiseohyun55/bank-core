package com.bank.bank_core.user.entity;

import com.bank.bank_core.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String email;

    @Builder
    public User(String name, String password, Role role, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role != null ? role : Role.ROLE_USER;
    }

    public enum Role {
        ROLE_ADMIN,
        ROLE_USER
    }
}
