package com.bank.bank_core.user.dto;

import com.bank.bank_core.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLoginResponse {

    private Long id;
    private String email;
    private String name;

    public static UserLoginResponse from(User user) {
        return new UserLoginResponse(
                user.getId(),
                user.getEmail(),
                user.getName()
        );
    }
}