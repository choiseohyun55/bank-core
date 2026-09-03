package com.bank.bank_core.auth.controller;

import com.bank.bank_core.auth.dto.ReissueRequest;
import com.bank.bank_core.auth.dto.TokenResponse;
import com.bank.bank_core.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody ReissueRequest request){
        TokenResponse tokenResponse = authService.reissue(request);
        return ResponseEntity.ok(tokenResponse);
    }

}
