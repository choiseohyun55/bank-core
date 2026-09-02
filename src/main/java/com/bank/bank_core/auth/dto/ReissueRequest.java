package com.bank.bank_core.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReissueRequest {
    private String refreshToken;
    public ReissueRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
