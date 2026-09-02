package com.bank.bank_core.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TokenResponse {
    private String grantType; // Bearer
    private String refreshToken;
    private String accessToken;

    public static TokenResponse of(String accessToken, String refreshToken) {
        return new TokenResponse("Bearer", accessToken, refreshToken);
    }
}
