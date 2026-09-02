package com.bank.bank_core.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserLogoutRequest {
    private String refreshToken;
}
