package com.bank.bank_core.user.dto;

import com.bank.bank_core.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupRequest {
    private String email;
    private String password;
    private String name;

    public UserSignupRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(this.email)
                .password(encodedPassword)
                .name(this.name)
                .role(User.Role.ROLE_USER)
                .build();
    }
}
